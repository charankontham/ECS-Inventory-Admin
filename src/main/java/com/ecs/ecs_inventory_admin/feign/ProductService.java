package com.ecs.ecs_inventory_admin.feign;

import com.ecs.ecs_inventory_admin.config.FeignClientConfig;
import com.ecs.ecs_inventory_admin.dto.ProductFinalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ECS-PRODUCT", configuration = FeignClientConfig.class)
public interface ProductService {
    @GetMapping("/api/product/{id}")
    ResponseEntity<ProductFinalDto> getProductById(@PathVariable("id") Integer productId);
}
