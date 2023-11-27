package com.mysqlfsbackend.repository;

import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectoryDao extends JpaRepository<DirectoryEntity, String> {
    @Query(value = "SELECT * FROM Directory AS d WHERE d.parentDirId IS NULL AND d.name = 'root'",
            nativeQuery = true)
    DirectoryEntity getRoot();

    @Query(value = "SELECT * FROM Directory AS d WHERE d.parentDirId = :parentDirId AND d.name = :name",
            nativeQuery = true)
    DirectoryEntity getChildByName(@Param("parentDirId") String parentDirId, @Param("name") String name);

    @Query(value = "SELECT * FROM Directory AS d WHERE d.parentDirId IN :parentDirIds ORDER BY d.parentDirId",
            nativeQuery = true)
    List<DirectoryEntity> getOrderedChildLayer(@Param("parentDirIds") List<String> parentDirIds);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Directory (name, parentDirId, permission, ownerUserId, ownerGroupId, size) "
            + "VALUES (:name, :parentDirId, :permission, :ownerUserId, :ownerGroupId, :size);",
            nativeQuery = true)
    void customInsert(@Param("name") String name, @Param("parentDirId") String parentDirId,
                        @Param("permission") Integer permission, @Param("ownerUserId") String ownerUserId,
                        @Param("ownerGroupId") String ownerGroupId, @Param("size") int size);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Directory" +
            " SET parentDirId = :newParentDirId" +
            " WHERE id = :id",
            nativeQuery = true)
    void putParentDir(@Param("newParentDirId") String newParentDirId,
                      @Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Directory" +
            " SET name = :newName" +
            " WHERE id = :id",
            nativeQuery = true)
    void putName(@Param("newName") String newName,
                      @Param("id") String id);
}
