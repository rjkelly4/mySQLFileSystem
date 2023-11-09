package com.mysqlfsbackend.repository;

import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
