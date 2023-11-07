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

    @Query (value = "SELECT * FROM File AS f WHERE f.id = :id")
    FileEntity getChildById(@Param("id") UUID id);


    @Query (value = "DELETE FROM File WHERE f.id = :id AND f.name = :name")
    void delete(@Param("id") UUID id, @Param("name") String name);

    @Query(value = "UPDATE File "
            + "SET f.name = :name, f.parent_dir = :parentDirId, f.permission = :permission, f.owner_user_id = :ownerUserId, "
            + "f.owner_group_id = ownerGroupId "
            + "where f.id = ?")
    UUID put(@Param("name") String name, @Param("parentDirId") UUID parentDirId, @Param("permission") int permission,
                @Param("ownerUserID") int ownerUserId, @Param("ownerGroupID") int ownerGroupId);

    @Query(value = "INSERT INTO File (name, parent_dir, permission, owner_user_id, owner_group_id) " + 
        "VALUES (:name, :parentDirId, :permission, :ownerUserId, :ownerGroupId);")
    UUID post(@Param("name") String name, @Param("parentDirId") UUID parentDirId, @Param("permission") int permission,
                @Param("ownerUserID") int ownerUserId, @Param("ownerGroupID") int ownerGroupId);
}
