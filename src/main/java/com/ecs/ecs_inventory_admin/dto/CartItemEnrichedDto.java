package com.ecs.ecs_inventory_admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemEnrichedDto {
    private Integer cartItemId;
    private ProductFinalDto productDetails;
    private Integer orderQuantity;
}
