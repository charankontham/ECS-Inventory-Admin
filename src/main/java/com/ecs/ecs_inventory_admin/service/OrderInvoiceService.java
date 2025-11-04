package com.ecs.ecs_inventory_admin.service;

import com.ecs.ecs_inventory_admin.dto.OrderInvoiceDto;
import com.ecs.ecs_inventory_admin.entity.ImageDoc;
import com.ecs.ecs_inventory_admin.entity.OrderInvoice;
import com.ecs.ecs_inventory_admin.exception.ResourceNotFoundException;
import com.ecs.ecs_inventory_admin.mapper.ImageMapper;
import com.ecs.ecs_inventory_admin.mapper.OrderInvoiceMapper;
import com.ecs.ecs_inventory_admin.repository.OrderInvoiceRepository;
import com.ecs.ecs_inventory_admin.service.interfaces.IOrderInvoiceService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class OrderInvoiceService implements IOrderInvoiceService {
    @Autowired
    OrderInvoiceRepository orderInvoiceRepository;

    @Override
    public OrderInvoiceDto getOrderInvoiceById(String id) {
        OrderInvoice orderInvoice = orderInvoiceRepository.findById(new ObjectId(id)).orElseThrow(
                () -> new ResourceNotFoundException("Invoice not found!"));
        return OrderInvoiceMapper.maptoOrderInvoiceDto(orderInvoice);
    }

    @Override
    public OrderInvoiceDto getOrderInvoiceByInvoiceId(Integer invoiceId) {
        OrderInvoice orderInvoice = orderInvoiceRepository.findByInvoiceId(invoiceId).orElseThrow(
                () -> new ResourceNotFoundException("Invoice not found!"));
        return OrderInvoiceMapper.maptoOrderInvoiceDto(orderInvoice);
    }

    @Override
    public OrderInvoiceDto createOrderInvoice(OrderInvoiceDto orderInvoiceDto) {
        OrderInvoice orderInvoice = OrderInvoiceMapper.maptoOrderInvoice(orderInvoiceDto);
        return OrderInvoiceMapper.maptoOrderInvoiceDto(orderInvoiceRepository.save(orderInvoice));
    }

    @Override
    public OrderInvoiceDto updateOrderInvoice(OrderInvoiceDto orderInvoiceDto) {
        OrderInvoice orderInvoice = OrderInvoiceMapper.maptoOrderInvoice(orderInvoiceDto);
        if (orderInvoiceRepository.findById(orderInvoice.getId()).isPresent()) {
            return OrderInvoiceMapper.maptoOrderInvoiceDto(orderInvoiceRepository.save(orderInvoice));
        } else {
            throw new ResourceNotFoundException("Invoice not found!");
        }
    }

    @Override
    public void deleteOrderInvoice(String id) {
        orderInvoiceRepository.deleteById(new ObjectId(id));
    }
}
