package com.ecs.ecs_inventory_admin.repository;

import com.ecs.ecs_inventory_admin.dto.ImageDocDto;
import com.ecs.ecs_inventory_admin.entity.ImageDoc;
import com.ecs.ecs_inventory_admin.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ImageRepositoryImpl implements ImageRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<ImageDocDto> getAllImagesByPagination(Pageable pageable,
                                                      Integer imageSize,
                                                      String dimensions,
                                                      String contentType,
                                                      String searchValue) {

        List<Criteria> criteriaList = new ArrayList<>();

        if (imageSize != null) {
            criteriaList.add(Criteria.where("size").is(imageSize));
        }

        if (dimensions != null && !dimensions.isEmpty()) {
            criteriaList.add(Criteria.where("dimensions").is(dimensions));
        }

        if (contentType != null && !contentType.isEmpty()) {
            criteriaList.add(Criteria.where("image_contentType").is(contentType));
        }

        if (searchValue != null && !searchValue.isEmpty()) {
            criteriaList.add(Criteria.where("image_name").regex(searchValue, "i"));
        }

        Query query = new Query();
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        long total = mongoTemplate.count(query, ImageDoc.class);
        query.with(pageable).with(Sort.by(Sort.Direction.DESC, "_id"));
        System.out.println("Query : "+ query.toString());
        List<ImageDoc> results = mongoTemplate.find(query, ImageDoc.class);
        System.out.println("Query Results 0 : "+ results.get(0).getName());

        List<ImageDocDto> dtoList = results.stream()
                .map(ImageMapper::mapToImageDocDto)
                .toList();

        return new PageImpl<>(dtoList, pageable, total);
    }
}
