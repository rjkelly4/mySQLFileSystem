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

    @Query(value = "UPDATE File "
            + "SET f.name = :name, f.parentDir = :parentDirId, f.permission = :permission, "
            + "f.ownerUserId = :ownerUserId, f.ownerGroupId = :ownerGroupId "
            + "WHERE f.id = :id",
            nativeQuery = true)
    String customUpdateById(@Param("id") String id, @Param("name") String name, @Param("parentDirId") String parentDirId,
                      @Param("permission") int permission, @Param("ownerUserId") int ownerUserId,
                      @Param("ownerGroupId") int ownerGroupId);

    @Query(value = "INSERT INTO File (id, name, parentDir, permission, ownerUserId, ownerGroupId) "
            + "VALUES (UUID(), :name, :parentDirId, :permission, :ownerUserId, :ownerGroupId)",
            nativeQuery = true)
    String customInsert(@Param("name") String name, @Param("parentDirId") String parentDirId,
                  @Param("permission") int permission, @Param("ownerUserId") int ownerUserId,
                  @Param("ownerGroupId") int ownerGroupId);
}
