package com.mysqlfsbackend.model.dto.filesystem;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DirectoryDto extends FileSystemObjectDto {
    private List<FileSystemObjectDto> content;

    public DirectoryDto(String id, String name, Integer size, String permission, String user, String group,
                        List<FileSystemObjectDto> content) {
        super(id, name, size, permission, user, group, true);
        this.content = content;
    }
}
