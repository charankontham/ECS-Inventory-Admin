package com.ecs.ecs_inventory_admin.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "images")
public class ImageDoc {
    @Id
    private ObjectId id;
    @Field("image_name")
    private String name;
    @Field("image_contentType")
    private String contentType;
    @Field("uploadedDate")
    private LocalDateTime uploadedDate;
    @Field("size")
    private Integer size;
    @Field("dimensions")
    private String dimensions;
    @Field("comments")
    private String comments;
    @Field("image_data")
    private byte[] image;
}
