package com.mysqlfsbackend.repository;

import com.mysqlfsbackend.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileDao extends JpaRepository<FileEntity, UUID> {
    @Query(value = "SELECT * FROM File AS f WHERE f.parentDirId = :parentDirId AND f.name = :name",
            nativeQuery = true)
    FileEntity getChildByName(@Param("parentDirId") UUID parentDirId, @Param("name") String name);
}
