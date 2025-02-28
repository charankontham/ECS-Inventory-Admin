package com.ecs.ecs_inventory_admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Integer customerId;
    private List<CartItemDto> cartItems;
}
