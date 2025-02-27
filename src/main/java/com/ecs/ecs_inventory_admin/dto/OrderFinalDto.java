package com.ecs.ecs_inventory_admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFinalDto {
    private Integer orderId;
    private CustomerDto customer;
    private AddressDto shippingAddress;
    private List<ProductFinalDto> orderItems;
    private Float itemsSubTotal;
    private Float shippingFee;
    private Float totalTax;
    private Float totalOrderValue;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String shippingStatus;
    private String paymentType;
    private String paymentStatus;
}
