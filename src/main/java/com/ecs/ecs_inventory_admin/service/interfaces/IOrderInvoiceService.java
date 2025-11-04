package com.ecs.ecs_inventory_admin.service.interfaces;

import com.ecs.ecs_inventory_admin.dto.OrderInvoiceDto;

public interface IOrderInvoiceService {
    OrderInvoiceDto getOrderInvoiceById(String id);
    OrderInvoiceDto getOrderInvoiceByInvoiceId(Integer invoiceId);
    OrderInvoiceDto createOrderInvoice(OrderInvoiceDto orderInvoiceDto);
    OrderInvoiceDto updateOrderInvoice(OrderInvoiceDto orderInvoiceDto);
    void deleteOrderInvoice(String id);
}
