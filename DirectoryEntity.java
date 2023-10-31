package com.example.mysqlfsbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectoryEntity {
    private final String id;
    private String name;
    private String parentDirId;
    private Integer permission;
    private String ownerUserId;
    private String ownerGroupId;
    private Integer size;

    public DirectoryEntity(String id, String name, String parentDirId, Integer permission,
                           String ownerUserId, String ownerGroupId, Integer size) {
        this.id = id;
        this.name = name;
        this.parentDirId = parentDirId;
        this.permission = permission;
        this.ownerUserId = ownerUserId;
        this.ownerGroupId = ownerGroupId;
        this.size = size;
    }
}
