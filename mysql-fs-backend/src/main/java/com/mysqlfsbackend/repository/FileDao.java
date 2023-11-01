package com.mysqlfsbackend.repository;

import com.mysqlfsbackend.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileDao extends JpaRepository<FileEntity, UUID> {
}
