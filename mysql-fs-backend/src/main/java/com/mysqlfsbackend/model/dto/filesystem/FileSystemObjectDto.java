package com.mysqlfsbackend.model.dto.filesystem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileSystemObjectDto {
    private String id;
    private String name;
    private Integer size;
    private String permission;
    private String user;
    private String group;
    private boolean isDirectory;

    @JsonProperty("isDirectory")
    public boolean isDirectory() { return isDirectory; }
}
