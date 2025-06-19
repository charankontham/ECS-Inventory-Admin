package com.ecs.ecs_inventory_admin.mapper;

import com.ecs.ecs_inventory_admin.dto.ImageDocDto;
import com.ecs.ecs_inventory_admin.entity.ImageDoc;
import org.bson.types.ObjectId;

import java.awt.*;
import java.util.Objects;

public class ImageMapper {

    public static ImageDocDto mapToImageDocDto(ImageDoc imageDoc) {
        return new ImageDocDto(
                imageDoc.getId().toHexString(),
                imageDoc.getName(),
                imageDoc.getContentType(),
                imageDoc.getUploadedDate(),
                imageDoc.getSize(),
                imageDoc.getDimensions(),
                imageDoc.getComments(),
                imageDoc.getImage()
        );
    }

    public static ImageDoc mapToImageDoc(ImageDocDto imageDocDto) {
        ObjectId objectId = null;
        return new ImageDoc(
                Objects.equals(imageDocDto.getId(), "") || imageDocDto.getId()==null ? null : new ObjectId(imageDocDto.getId()),
                imageDocDto.getImageName(),
                imageDocDto.getContentType(),
                imageDocDto.getUploadedDate(),
                imageDocDto.getSize(),
                imageDocDto.getDimensions(),
                imageDocDto.getComments(),
                imageDocDto.getImage()
        );
    }
}
