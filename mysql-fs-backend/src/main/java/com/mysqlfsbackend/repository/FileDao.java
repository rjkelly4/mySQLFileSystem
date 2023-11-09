package com.mysqlfsbackend.repository;

import com.mysqlfsbackend.model.filesystem.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDao extends JpaRepository<FileEntity, String> {
    @Query(value = "SELECT * FROM File AS f WHERE f.parentDirId = :parentDirId AND f.name = :name",
            nativeQuery = true)
    FileEntity getChildByName(@Param("parentDirId") String parentDirId, @Param("name") String name);

    @Query(value = "SELECT * FROM File AS f WHERE f.parentDirId IN :parentDirIds ORDER BY f.parentDirId",
            nativeQuery = true)
    List<FileEntity> getOrderedChildLayer(@Param("parentDirIds") List<String> parentDirIds);
}
