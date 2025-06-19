package com.ecs.ecs_inventory_admin.repository;


import com.ecs.ecs_inventory_admin.dto.ImageDocDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ImageRepositoryCustom {
    Page<ImageDocDto> getAllImagesByPagination(Pageable pageable,
                                               Integer imageSize,
                                               String dimensions,
                                               String contentType,
                                               String searchValue);
}
