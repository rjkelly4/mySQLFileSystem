package com.mysqlfsbackend.model;

import jakarta.persistence.Entity;
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
@Table(name = "Directory")
public class DirectoryEntity {
    @Id
    private UUID id;
    private String name;
    private UUID parentDirId;
    private Integer permission;
    private Integer ownerUserId;
    private Integer ownerGroupId;
    private Integer size;
}
