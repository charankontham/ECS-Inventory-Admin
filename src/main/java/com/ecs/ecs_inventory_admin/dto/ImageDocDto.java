package com.ecs.ecs_inventory_admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDocDto {
    private String id;
    private String imageName;
    private String contentType;
    private LocalDateTime uploadedDate;
    private Integer size;
    private String dimensions;
    private String comments;
    private byte[] image;
}
