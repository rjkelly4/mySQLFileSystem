package com.mysqlfsbackend.model.filesystem;
public interface FileSystemObject {
    String getId();
    void setId(String id);
    String getName();
    void setName(String name);
    String getParentDirId();
    void setParentDirId(String parentDirId);
    String getPermission();
    void setPermission(String permission);
    String getOwnerUserId();
    void setOwnerUserId(String ownerUserId);
    String getOwnerGroupId();
    void setOwnerGroupId(String ownerGroupId);
    Integer getSize();
    void setSize(Integer size);
}
