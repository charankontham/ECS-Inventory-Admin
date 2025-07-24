package com.ecs.ecs_inventory_admin.service.interfaces;

import com.ecs.ecs_inventory_admin.dto.ImageDocDto;
import com.ecs.ecs_inventory_admin.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IImageService {
    ImageDocDto getImageById(String id);
    Page<ImageDocDto> getAllImagesByPagination(
            Pageable pageable,
            Integer imageSize,
            String dimensions,
            String contentType,
            String searchValue);
    ImageDocDto getImageByName(String imageName);
    ImageDocDto saveImage(ImageDocDto imageDocDto);
    ImageDocDto updateImage(ImageDocDto imageDocDto);
    ImageDocDto patchImage(String id, String imageName);
    void deleteImageById(String id);
}
