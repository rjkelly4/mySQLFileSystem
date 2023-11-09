package com.mysqlfsbackend.model.filesystem;
public interface FileSystemObject {
    String getId();
    void setId(String id);
    String getName();
    void setName(String name);
    String getParentDirId();
    void setParentDirId(String parentDirId);
    Integer getPermission();
    void setPermission(Integer permission);
    Integer getOwnerUserId();
    void setOwnerUserId(Integer ownerUserId);
    Integer getOwnerGroupId();
    void setOwnerGroupId(Integer ownerGroupId);
    Integer getSize();
    void setSize(Integer size);
}
