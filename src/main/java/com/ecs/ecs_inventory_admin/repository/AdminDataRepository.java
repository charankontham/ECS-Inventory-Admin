package com.ecs.ecs_inventory_admin.repository;

import com.ecs.ecs_inventory_admin.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminDataRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByAdminUsername(String username);
    boolean existsByAdminUsername(String username);

}
