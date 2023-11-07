package com.mysqlfsbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "File")
public class FileEntity extends com.mysqlfsbackend.model.Entity{

    @Enumerated(EnumType.STRING)
    private FileTypes fileType;

    private String content;

    @Override
    void get() {
        String query = "SELECT * FROM directories WHERE id = ?";

        ResultSet rs = null;

        try (PreparedStatement stmt = this.getDatabaseCxn().prepareStatement(query)) {
            stmt.setObject(1, this.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                this.setName(rs.getString("name"));
                this.setParentDirId((UUID) rs.getObject("parent_dir"));
                this.setPermission(rs.getInt("permission"));
                this.setOwnerUserId(rs.getInt("owner_user_id"));
                this.setOwnerGroupId(rs.getInt("owner_group_id"));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());;
        }
    }

    @Override
    void delete() {
        if (this.getId() == null) {
            return;
        }

        //Execute DELETE statement with this.id
        String deleteThisRow =
                "DELETE FROM directories WHERE id = ?";

        try (PreparedStatement deleteStatement = this.getDatabaseCxn().prepareStatement(deleteThisRow)) {
            //Execute UPDATE statement
            deleteStatement.setObject(1, this.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set all fields for this object to null, except ID - this won't be reused by the table, so no need
        this.setName(null);
        this.setParentDirId(null);
        this.setPermission(null);
        this.setOwnerUserId(null);
        this.setOwnerGroupId(null);
    }

    @Override
    ResultSet read() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    UUID post() {
        //TODO use PreparedStatement to return generated primary key
        // Parse current fields into string that can be read as INSERT statement
        String createRow =
            "INSERT INTO directories (name, parent_dir, permission, owner_user_id, owner_group_id)" 
            + "VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement createRowStatement = this.getDatabaseCxn().prepareStatement(
                    createRow, Statement.RETURN_GENERATED_KEYS)) {
            this.databaseCxn.setAutoCommit(false);
            createRowStatement.setString(1, this.getName());
            if (this.getParentDirId() != null) {
                createRowStatement.setObject(2, this.getParentDirId());
            } else createRowStatement.setNull(2, 4);
            if (this.getPermission() != null) {
                createRowStatement.setInt(3, this.getPermission());
            } else createRowStatement.setNull(3, 4);
            if (this.getOwnerUserId() != null) {
                createRowStatement.setInt(4, this.getOwnerUserId());
            } else createRowStatement.setNull (4, 4);
            if (this.getOwnerGroupId() != null) {
                createRowStatement.setInt(5, this.getOwnerGroupId());
            } else createRowStatement.setNull(5, 4);
            
            //Execute statement
            createRowStatement.executeUpdate();

            //Sends the update to the server
            this.databaseCxn.commit();

            ResultSet generatedKey = createRowStatement.getGeneratedKeys();

            while (generatedKey.next()) {
                this.id = (UUID) generatedKey.getObject(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.out.println(e);
            System.out.println("Error: id is not in UUID parseable format");
        }

        //TODO change this to use primary key to ensure everything worked correctly
        String query = 
        "SELECT * FROM directories WHERE name = '?' AND parent_dir = '?' "
        + "AND permission = '?' AND owner_user_id= '?' AND owner_group_id= '?'";

        ResultSet rs = null;

        try (PreparedStatement stmt = this.getDatabaseCxn().prepareStatement(query)) {
            stmt.setString(1, this.getName());
            stmt.setObject(2, this.getParentDirId());
            stmt.setInt(3, this.getPermission());
            stmt.setInt(4, this.getOwnerUserId());
            stmt.setInt(5, this.getOwnerUserId());

            rs = stmt.executeQuery(query);
            //NOTE: This will currently set the object to represent the last directory with this name & parent dir - 
            //  watch for copies in the same directory!
            while (rs.next()) {
                this.setId((UUID) rs.getObject("id"));
                this.setName(rs.getString("name"));
                this.setParentDirId((UUID) rs.getObject("parent_directory"));
                this.setPermission(rs.getInt("permission"));
                this.setOwnerUserId(rs.getInt("owner_user_id"));
                this.setOwnerGroupId(rs.getInt("owner_group_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());;
        }

        // Note: this is a new row in the SQL table


        // Retrieve primary key (assigned by SQL table) and return it
        return this.getId();
    }

    @Override
    void put() throws SQLException {
        String updateName =
        "UPDATE directories SET name = ? where id = ?";
        String updateParentDir =
        "UPDATE directories SET parent_dir = ? where id = ?";

        try (PreparedStatement updateNameStatement = this.getDatabaseCxn().prepareStatement(updateName);
            PreparedStatement updateParentDirStatement = this.getDatabaseCxn().prepareStatement(updateParentDir)) {
                /*TODO add in the rest of the fields for this object, split these into different 'if' checks */
                //Execute UPDATE statement
                if (this.getName() != null || this.getParentDirId() != null) {
                    updateNameStatement.setString(1, this.getName());
                    updateNameStatement.setObject(2, this.getId());
                    updateNameStatement.executeUpdate();

                    updateParentDirStatement.setObject(1, this.getParentDirId());
                    updateParentDirStatement.setObject(2, this.getId());
                    updateParentDirStatement.executeUpdate();
                } else {
                    //Do nothing - if all fields are null, the object has been deleted
                }

                //Sends the update to the server
                
                this.databaseCxn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    
        //TODO add code to check if the update worked
        //Call read() with primary key

        //Use ResultSet return from read() to validate that the update was properly executed;
        //  if the ResultSet does not match the current fields, something went wrong in updating -
        //  throw SQLException
    }
}
