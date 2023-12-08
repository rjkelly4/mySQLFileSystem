package com.mysqlfsbackend.model.dto.http;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostBody {
    private UUID id;
    private String name;
    private String parentId;
    private String permission;
    private String ownerUserId;
    private String ownerGroupId;
    private String size;
    private String fileType;
    private String content;

    public PostBody() {
        this.id = UUID.randomUUID();
    }
}
