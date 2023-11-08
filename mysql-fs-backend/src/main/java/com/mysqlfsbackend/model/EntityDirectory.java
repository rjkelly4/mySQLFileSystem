package com.mysqlfsbackend.model;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.Data;
import jakarta.persistence.Entity;

//Replace CRUD names with Restful names
@Data
@Entity
public class EntityDirectory extends EntityAbstract{

    //TODO the inclusion of both of these may be redundant
    private JDBCConnectionMaker jdbcConnectionMaker;
    private Connection          databaseCxn;

    /**
     * Creates a new EntityDirectory object that corresponds to a row in the directories SQL table.
     * This constructor is intended for adding new rows to the directories table that have yet to be assigned
     * primary keys.
     */
    public EntityDirectory() {
        // Set all fields to null.

        // Create and set a new JDBCConnectionMaker

        //**Note that the primary key will be defined when the save() method creates the
        //  new row in the database.
    }

    /**
     * Constructs an object modeled after an existing row in the directories SQL table.
     *
     * @param id The primary key to locate within the database
     * @throws NoSuchElementException if there is no matching entry found for the primary key.
     */
    public EntityDirectory(UUID id) throws NoSuchElementException {
        // Sets the object's primary key
        this.setId(id);

        // TODO Create and set a new JDBCConnectionMaker
        openNewConnection("MezTestServ",
                        "0.0.0.0", "13306",
                        "alex", "test_pw");

        // calls load(), passes on exceptions to caller
        get();
    }

    /**
     * Returns the Integer that correlates to the primary key for this object
     *
     * Note: This value may be different from what is contained
     * within the database.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Sets the primary key. CAUTION: this can corrupt the data structure if used incorrectly.
     * @param id: the new primary key.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the String that correlates to 'name' for this object
     * <br>
     * Note: This value may be different from what is contained
     * within the database.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new 'name' field for the directory.
     * <br>
     * Note: This will not be updated until save() is called.
     * @param name: The new name for the directory.
     * @see this.save()
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the Integer that correlates to the parent directory foreign key for this object
     * <br>
     * Note: This value may be different from what is contained
     * within the database.
     */
    public UUID getParentDirId() {
        return parentDirId;
    }

    /**
     * Sets a new parent directory foreign key for this directory.
     * <br>
     * Note: This will not be updated until save() is called.
     * @param parentDir: The foreign key of the new parent directory.
     * @see this.save()
     */
    public void setParentDirId(UUID parentDir) {
        this.parentDirId = parentDir;
    }

    /**
     * Returns the Integer that correlates to the permission for this object
     *
     * Note: This value may be different from what is contained
     * within the database.
     */
    public Integer getPermission() {
        return permission;
    }

    /**
     * Sets the permission value for this directory
     * @param permission: the new permission value.
     */
    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    /**
     * Returns the Integer that correlates to the ownerUserId for this object
     *
     * Note: This value may be different from what is contained
     * within the database.
     */
    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    /**
     * Sets the ownerUserId value for this directory
     * @param ownerUserId: the new ownerUserId value.
     */
    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    /**
     * Returns the Integer that correlates to the ownerGroupId for this object
     *
     * Note: This value may be different from what is contained
     * within the database.
     */
    public Integer getOwnerGroupId() {
        return ownerUserId;
    }

    /**
     * Sets the ownerGroupId value for this directory
     * @param ownerGroupId: the new ownerGroupId value.
     */
    public void setOwnerGroupId(Integer ownerGroupId) {
        this.ownerGroupId = ownerGroupId;
    }


    public Connection getDatabaseCxn() {
        return databaseCxn;
    }

    public void setDatabaseCxn(Connection databaseCxn) {
        this.databaseCxn = databaseCxn;
    }

    /**
     * Queries the database to try to find a row with the same primary key as this object.
     * If found, updates the EntityDirectory object's fields with the information held in the
     * corresponding row.
     *
     * @see this.read()
     *
     * @throws NoSuchElementException if there is no matching entry found for the primary key.
     * @throws NullPointerException If this object's primary key is null.
     */
    public void get() throws NoSuchElementException, NullPointerException {
        //TODO split this method into load() and read()

        // Throw NullPointerException if there is no primary key for the object to create
        if (this.getId() == null) throw new NullPointerException();

        // Call read() with this object's primary key

        //TODO Change this to get owner_user_id is gotten from the context of the user session
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

    /**
     * Removes the corresponding row in the SQL table via primary key, and clears this EntityDirectory
     * object.
     */
    public void delete() {
        // If this.id != null:
        //      Call delete()
        if (this.getId() != null) {
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
        } else { 
            return;
        }

        // Set all fields for this object to null
        this.setId(null);
        this.setName(null);
        this.setParentDirId((UUID) null);
        this.setPermission(null);
        this.setOwnerUserId(null);
        this.setOwnerGroupId(null);

        this.setDatabaseCxn(null);
        this.setJdbcConnectionMaker(null);
    }

    /**
     * Creates a new row in the directories table using the information in this EntityDirectory object.
     */
    public UUID post() {
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

    /**
     * Uses the primary key for this object to return the information currently held in the
     * corresponding row.
     *
     * @return a ResultSet containing the data from the database.
     * @throws NullPointerException if this object's primary key is null.
     */
    public ResultSet read() throws NullPointerException{
        // Assert that the primary key to search for is not NULL
        //      If so, throws a NullPointerException
        if (this.getId() == null) throw new NullPointerException();

        // Uses JDBC to execute an SQL query via SELECT via primary key


        // Return ResultSet object
        return null;
    }

    /**
     * Updates the row in the directories table with a matching primary key with the information
     * held within this object.
     *
     * @throws SQLException if the database fails to update with this EntityDirectory's fields
     */
    public void put() throws SQLException {
        //Parse fields into String for SQL UPDATE statement
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

    public void setJdbcConnectionMaker(JDBCConnectionMaker jdbcConnectionMaker) {
        this.jdbcConnectionMaker = jdbcConnectionMaker;
    }

    private void openNewConnection(String dbName, String ip, String port, String username, String passwd) {
        JDBCConnectionMaker localDB = new JDBCConnectionMaker(dbName, ip, port, username, passwd);

        Connection thisCxn = localDB.getConnection();

        databaseCxn = thisCxn;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();

        returnString.append(this.getId()).append(") ").append(this.getName()).append(", \n\tParent Directory PK# ")
                .append(this.getParentDirId());

        return returnString.toString();
    }

/*    public static final Scanner in = new Scanner(System.in);

    
     * This is a main method for console-based debugging only. 
    
    public static void main(String[] args) {

        EntityDirectory testDir = new EntityDirectory();

        //Creates a new JDBC - for testing, this will connect to the MezTestServ on the team CS506 vm
        //Make sure you have the Connection/J .jar in the classpath!
        JDBCConnectionMaker localDB = new JDBCConnectionMaker("MezTestServ",
             "localhost", "13306",
            "alex", "test_pw");


        if (localDB != null) {
            System.out.println("Connection established");
        }

        testDir.setDatabaseCxn(localDB.getConnection());
        testDir.setJdbcConnectionMaker(localDB);

        int userInput = -1;

        while (userInput != 0) {
            System.out.println(testDir);
            System.out.println();
            System.out.println("""
            What would you like to test?
                1. Create new (null) object
                2. Create new object with params
                3. Load based on primary key
                4. Delete based on primary key
                5. Modify contents
                7. Get new DB Connection
                8. PUT object in database
                9. POST object in database
                0. Exit
                """);

            userInput = Integer.valueOf(in.nextLine());

            switch (userInput) {
                case 1:
                    testDir = new EntityDirectory();
                    break;
                case 2:
                    testDir = new EntityDirectory();
                    //TODO add in interface to set params
                    break;
                case 3:
                    System.out.println("Please enter the primary key of the object that you'd like to load: ");
                    int pk = Integer.valueOf(in.nextLine());
                    testDir = new EntityDirectory(pk);
                    break;
                case 4:
                    testDir.delete();
                    break;
                case 5:
                    System.out.println("Please enter new name:");
                    String newName = in.nextLine();

                    System.out.println("Please enter new parent dir:");
                    int newParentDir = Integer.valueOf(in.nextLine());

                    testDir.setName(newName);
                    testDir.setParentDir(newParentDir);

                    System.out.println("The new name and parent dir is: " + testDir.getName() + ", " + testDir.getParentDir());
                    break;
                case 7:
                    testDir.openNewConnection("MezTestServ",
                        "0.0.0.0", "13306",
                        "alex", "test_pw");
                    break;
                case 8:
                    try{
                        testDir.getDatabaseCxn().setAutoCommit(false);
                        testDir.put();
                    } catch (SQLException e) {
                        System.out.println("SQLException in update()");
                    }
                    break;
                case 9: 
                    try{
                        testDir.getDatabaseCxn().setAutoCommit(false);
                        testDir.post();
                    } catch (SQLException e) {
                        System.out.println("SQLException in update()");
                    }
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("The entered input is not supported. Please try again.");
                    break;
            }
        }

        System.exit(1);
    }
    */
}