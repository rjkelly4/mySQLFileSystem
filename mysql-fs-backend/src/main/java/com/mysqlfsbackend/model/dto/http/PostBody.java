package com.mysqlfsbackend.model.dto.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBody {
    private String name;
    private String parentId;
    private String permission;
    private String ownerUserId;
    private String ownerGroupId;
    private String size;
    private String fileType;
    private String content;
}
