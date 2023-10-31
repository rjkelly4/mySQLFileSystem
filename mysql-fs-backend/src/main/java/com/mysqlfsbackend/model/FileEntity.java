package com.mysqlfsbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileEntity {
    private final String id;
    private String name;
    private String parentDirId;
    private Integer permission;
    private String ownerUserId;
    private String ownerGroupId;
    private Integer size;
    private FileTypes fileType;
    private String content;

    public FileEntity(String id, String name, String parentDirId, Integer permission,
                      String ownerUserId, String ownerGroupId, Integer size, FileTypes fileType, String content) {
        this.id = id;
        this.name = name;
        this.parentDirId = parentDirId;
        this.permission = permission;
        this.ownerUserId = ownerUserId;
        this.ownerGroupId = ownerGroupId;
        this.size = size;
        this.fileType = fileType;
        this.content = content;
    }
}
