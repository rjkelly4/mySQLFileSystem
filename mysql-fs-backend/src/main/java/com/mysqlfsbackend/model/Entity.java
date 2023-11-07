package com.mysqlfsbackend.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * An abstract class to represent entity objects, in this case either
 * files or directories. 
 * @see com.mysqlfsbackend.model.EntityDirectory
 */
@Data
public abstract class Entity {
    @Id
    UUID id;
    String name;
    UUID parentDirId;
    Integer permission;
    Integer ownerUserId;
    Integer ownerGroupId;
    Integer size;
    Connection databaseCxn;

    abstract void get();
    
    abstract void delete();

    abstract ResultSet read();

    abstract UUID post();

    abstract void put() throws SQLException;
}
