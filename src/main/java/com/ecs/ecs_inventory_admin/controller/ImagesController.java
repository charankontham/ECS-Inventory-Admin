package com.ecs.ecs_inventory_admin.controller;

import com.ecs.ecs_inventory_admin.dto.ImageDocDto;
import com.ecs.ecs_inventory_admin.service.interfaces.IImageService;
import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/public/images")
public class ImagesController {
    @Autowired
    private IImageService imageService;

    @GetMapping("/getByImageId/{imageId}")
    public ResponseEntity<ImageDocDto> getByImageId(@PathVariable("imageId") String imageId ) {
        ImageDocDto image = imageService.getImageById(imageId);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/view/getImageById/{imageId}")
    public ResponseEntity<byte[]> viewImageById(@PathVariable("imageId") String imageId ) {
        ImageDocDto image = imageService.getImageById(imageId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getContentType()));
        return new ResponseEntity<>(image.getImage(), headers, HttpStatus.OK);
    }

    @GetMapping("/getAllImagesByPagination")
    public ResponseEntity<Page<ImageDocDto>> getAllImagesByPagination(
            @RequestParam(defaultValue = "0", name = "currentPage") Integer currentPage,
            @RequestParam(defaultValue = "10", name = "offset") Integer offset,
            @RequestParam(required = false, name="image_size") Integer imageSize,
            @RequestParam(required = false, name="dimensions") String dimensions,
            @RequestParam(required = false, name="content_type") String contentType,
            @RequestParam(required = false, name="search_value") String searchValue){
        Pageable pageable = PageRequest.of(currentPage, offset);
        Page<ImageDocDto> images = imageService.getAllImagesByPagination(pageable, imageSize, dimensions, contentType, searchValue);
        return ResponseEntity.ok(images);
    }

    @GetMapping("/getByImageName/{name}")
    public ResponseEntity<ImageDocDto> getImageByName(@PathVariable("name") String imageName) {
        ImageDocDto image = imageService.getImageByName(imageName);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ImageDocDto> saveImage(@RequestBody ImageDocDto imageDocDto) {
        ImageDocDto image = imageService.saveImage(imageDocDto);
        return new ResponseEntity<>(image, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ImageDocDto> updateImage(@RequestBody ImageDocDto imageDocDto) {
        ImageDocDto image = imageService.updateImage(imageDocDto);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ImageDocDto> updateImageField(
            @PathVariable("id") String imageId,
            @RequestParam(required = false) String imageName
    ) {
        ImageDocDto image = imageService.patchImage(imageId, imageName);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable("id") String imageId) {
        imageService.deleteImageById(imageId);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }


}
