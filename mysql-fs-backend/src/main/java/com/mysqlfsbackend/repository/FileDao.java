package com.mysqlfsbackend.repository;

import com.mysqlfsbackend.model.filesystem.FileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO File (id, name, parentDirId, permission, ownerUserId, ownerGroupId, size, fileType, content) "
            + "VALUES (:id, :name, :parentDirId, :permission, :ownerUserId, :ownerGroupId, :size, :fileType, :content)",
            nativeQuery = true)
    void customInsert(@Param("id") String id, @Param("name") String name, @Param("parentDirId") String parentDirId,
                  @Param("permission") String permission, @Param("ownerUserId") String ownerUserId,
                  @Param("ownerGroupId") String ownerGroupId, @Param("size") String size,
                  @Param("fileType") String fileType, @Param("content") String content);

    @Modifying
    @Transactional
    @Query(value = "UPDATE File" +
            " SET parentDirId = :newParentDirId" +
            " WHERE id = :id",
            nativeQuery = true)
    void patchParentDir(@Param("newParentDirId") String newParentDirId,
                      @Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE File" +
            " SET name = :newName" +
            " WHERE id = :id",
            nativeQuery = true)
    void patchName(@Param("newName") String newName,
                 @Param("id") String id);
}
