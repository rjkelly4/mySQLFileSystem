package com.mysqlfsbackend.model;

import java.sql.ResultSet;
import java.util.UUID;

import jakarta.persistence.Id;

/**
 * An abstract class to represent entity objects, in this case either
 * files or directories. 
 * @see com.mysqlfsbackend.model.EntityDirectory
 */
public abstract class Entity {
    @Id
    UUID id;
    String name;
    UUID parentDirId;
    Integer permission;
    Integer ownerUserId;
    Integer ownerGroupId;
    Integer size;

    abstract void get();
    
    abstract void delete();

    abstract ResultSet read();

    abstract UUID post();

    abstract UUID put();
}
