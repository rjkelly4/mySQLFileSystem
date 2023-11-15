package com.mysqlfsbackend.model.filesystem;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "File")
public class FileEntity implements FileSystemObject {
    @Id
    private String id;
    private String name;
    private String parentDirId;
    private String permission;
    private String ownerUserId;
    private String ownerGroupId;
    private Integer size;

    @Enumerated(EnumType.STRING)
    private FileTypes fileType;

    private String content;
}
