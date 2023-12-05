package com.mysqlfsbackend.model.dto.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBody {
    private String id;
    private String name;
    private String parentId;
    private String permission;
    private String ownerUserId;
    private String ownerGroupId;
    private String size;
    private String fileType;
    private String content;

    /**
     * A constructor with auto-size calculation.
     * @param name: the name of the file
     * @param parentId: the primary key of the parent directory
     * @param permission: the permission level of the file
     *                  //TODO usage is unclear
     * @param ownerUserId: The primary key of the user
     * @param ownerGroupId: The primary key of the group this belongs to, if any
     * @param fileType: The type of file
     * @param content: The string contents of the file
     */
    public PostBody(String name, String parentId, String permission, String ownerUserId, String ownerGroupId, String fileType, String content) {
        this.name = name;
        this.parentId = parentId;
        this.permission = permission;
        this.ownerUserId = ownerUserId;
        this.ownerGroupId = ownerGroupId;
        this.fileType = fileType;
        this.content = content;
        this.setSize();
    }

    /**
     * Auto-calculates the size of the file in bytes based on the number of chars
     */
    private void setSize() {
        if (this.content == null) {
            this.size = "0";
            return;
        }

        int fileSize = content.length();
        this.size = String.valueOf(fileSize);
    }
}
