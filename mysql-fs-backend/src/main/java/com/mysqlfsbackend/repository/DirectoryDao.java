package com.mysqlfsbackend.repository;

import com.mysqlfsbackend.model.DirectoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DirectoryDao extends JpaRepository<DirectoryEntity, UUID> {
    @Query(value = "SELECT * FROM Directory AS d WHERE d.parentDirId IS NULL AND d.name = 'root'",
            nativeQuery = true)
    DirectoryEntity getRoot();

    @Query(value = "SELECT * FROM Directory AS d WHERE d.parentDirId = :parentDirId AND d.name = :name",
            nativeQuery = true)
    DirectoryEntity getChildByName(@Param("parentDirId") UUID parentDirId, @Param("name") String name);

    @Query(value = "DELETE FROM Directory WHERE d.id = :id AND d.name = :name")
    void delete(@Param("id") UUID id, @Param("name") String name);

    @Query(value = "UPDATE directories "
     + "SET d.name = :name, d.parent_dir = :parentDirId, d.permission = :permission, d.owner_user_id = :ownerUserId, "
     + "d.owner_group_id = ownerGroupId "
     + "where d.id = ?")
    UUID put(@Param("name") String name, @Param("parentDirId") UUID parentDirId, @Param("permission") int permission,
                @Param("ownerUserID") int ownerUserId, @Param("ownerGroupID") int ownerGroupId);

    @Query(value = "INSERT INTO directories (name, parent_dir, permission, owner_user_id, owner_group_id) " + 
        "VALUES (:name, :parentDirId, :permission, :ownerUserId, :ownerGroupId);")
    UUID post(@Param("name") String name, @Param("parentDirId") UUID parentDirId, @Param("permission") int permission,
                @Param("ownerUserID") int ownerUserId, @Param("ownerGroupID") int ownerGroupId);
}
