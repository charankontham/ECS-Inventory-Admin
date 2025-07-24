package com.ecs.ecs_inventory_admin.service;

import com.ecs.ecs_inventory_admin.dto.ImageDocDto;
import com.ecs.ecs_inventory_admin.entity.ImageDoc;
import com.ecs.ecs_inventory_admin.exception.ResourceNotFoundException;
import com.ecs.ecs_inventory_admin.mapper.ImageMapper;
import com.ecs.ecs_inventory_admin.repository.ImageRepository;
import com.ecs.ecs_inventory_admin.service.interfaces.IImageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ImageDocDto getImageById(String id) {
        ImageDoc imageDoc =  imageRepository.findById(new ObjectId(id)).orElseThrow(
                () -> new ResourceNotFoundException("Image not found!"));
        return ImageMapper.mapToImageDocDto(imageDoc);
    }

    @Override
    public Page<ImageDocDto> getAllImagesByPagination(Pageable pageable,
                                          Integer imageSize,
                                          String dimensions,
                                          String contentType,
                                          String searchValue) {
         return imageRepository.getAllImagesByPagination(pageable, imageSize, dimensions, contentType, searchValue);
    }

    @Override
    public ImageDocDto getImageByName(String imageName) {
        ImageDoc imageDoc =  imageRepository.findByName(imageName).orElseThrow(
                () -> new ResourceNotFoundException("Image not found!"));
        return ImageMapper.mapToImageDocDto(imageDoc);
    }

    @Override
    public ImageDocDto saveImage(ImageDocDto imageDocDto) {
        ImageDoc imageDocObj = ImageMapper.mapToImageDoc(imageDocDto);
        return  ImageMapper.mapToImageDocDto(imageRepository.save(imageDocObj));
    }

    @Override
    public ImageDocDto updateImage(ImageDocDto imageDocDto) {
        ImageDoc imageDocObj = ImageMapper.mapToImageDoc(imageDocDto);
        imageDocObj.setComments("Updated Image contents");
        if(imageRepository.findById(imageDocObj.getId()).isPresent()){
            return ImageMapper.mapToImageDocDto(imageRepository.save(imageDocObj));
        }else{
            throw new ResourceNotFoundException("ImageId not found!");
        }
    }

    @Override
    public ImageDocDto patchImage(String id, String imageName) {
        ImageDoc imageDoc = imageRepository.findById(new ObjectId(id)).orElseThrow(
                () -> new ResourceNotFoundException("Image not found!"));
        imageDoc.setName(imageName);
        imageDoc.setComments("Updated Image Name");
        return ImageMapper.mapToImageDocDto(imageRepository.save(imageDoc));
    }

    @Override
    public void deleteImageById(String id) {
        imageRepository.deleteById(new ObjectId(id));
    }
}
