package com.mysqlfsbackend.repository;

import com.mysqlfsbackend.model.DirectoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DirectoryDao extends JpaRepository<DirectoryEntity, UUID> {
}
