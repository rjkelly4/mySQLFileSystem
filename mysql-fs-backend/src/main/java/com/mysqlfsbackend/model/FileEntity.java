package com.mysqlfsbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "File")
public class FileEntity {
    @Id
    private UUID id;
    private String name;
    private UUID parentDirId;
    private Integer permission;
    private Integer ownerUserId;
    private Integer ownerGroupId;
    private Integer size;

    @Enumerated(EnumType.STRING)
    private FileTypes fileType;

    private String content;
}
