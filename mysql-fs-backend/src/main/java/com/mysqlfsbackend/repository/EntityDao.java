package com.mysqlfsbackend.repository;

import java.util.List;
import java.util.UUID;
import com.mysqlfsbackend.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface EntityDao extends JpaRepository<Entity, UUID> {
    Entity getRoot();

    Entity getChildByName();

    List<Entity> getAll();
}
