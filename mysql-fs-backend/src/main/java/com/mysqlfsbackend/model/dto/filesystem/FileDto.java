package com.mysqlfsbackend.model.dto.filesystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto extends FileSystemObjectDto {
    private String fileType;
    private String content;

    public FileDto(String id, String name, Integer size, String permission, String user, String group,
                   String fileType, String content) {
        super(id, name, size, permission, user, group, false);
        this.fileType = fileType;
        this.content = content;
    }
}
