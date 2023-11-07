package com.mysqlfsbackend.repository;

import java.util.List;
import java.util.UUID;
import com.mysqlfsbackend.model.EntityAbstract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface EntityDao extends JpaRepository<EntityAbstract, UUID> {
    EntityAbstract getRoot();

    EntityAbstract getChildById(UUID id);

    Iterable<EntityAbstract> getAll();

    long count();

    void delete (EntityAbstract entity);

    boolean existsById(UUID id);
}
