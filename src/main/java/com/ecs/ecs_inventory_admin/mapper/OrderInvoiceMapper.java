package com.ecs.ecs_inventory_admin.mapper;

import com.ecs.ecs_inventory_admin.dto.OrderInvoiceDto;
import com.ecs.ecs_inventory_admin.entity.OrderInvoice;
import org.bson.types.ObjectId;

import java.util.Objects;

public class OrderInvoiceMapper {

    public static OrderInvoice maptoOrderInvoice(OrderInvoiceDto orderInvoiceDto) {
        return new OrderInvoice(
                Objects.equals(orderInvoiceDto.getId(), "") || orderInvoiceDto.getId() == null ? null : new ObjectId(orderInvoiceDto.getId()),
                orderInvoiceDto.getInvoiceId(),
                orderInvoiceDto.getInvoiceFile()
        );
    }

    public static OrderInvoiceDto maptoOrderInvoiceDto(OrderInvoice orderInvoice) {
        return new OrderInvoiceDto(
                orderInvoice.getId().toHexString(),
                orderInvoice.getInvoiceId(),
                orderInvoice.getInvoiceFile()
        );
    }
}

