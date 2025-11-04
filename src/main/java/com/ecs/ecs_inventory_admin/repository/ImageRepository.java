package com.ecs.ecs_inventory_admin.repository;

import com.ecs.ecs_inventory_admin.entity.ImageDoc;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImageRepository extends MongoRepository<ImageDoc, ObjectId>, ImageRepositoryCustom {
    Optional<ImageDoc> findByName(String imageName);
}
