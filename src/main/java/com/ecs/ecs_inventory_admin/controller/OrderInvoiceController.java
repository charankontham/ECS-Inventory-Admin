package com.ecs.ecs_inventory_admin.controller;

import com.ecs.ecs_inventory_admin.dto.ImageDocDto;
import com.ecs.ecs_inventory_admin.dto.OrderInvoiceDto;
import com.ecs.ecs_inventory_admin.service.OrderInvoiceService;
import com.ecs.ecs_inventory_admin.service.interfaces.IOrderInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderInvoice")
public class OrderInvoiceController {
    @Autowired
    IOrderInvoiceService orderInvoiceService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderInvoiceDto> getInvoiceById(@PathVariable("id") String id ) {
        OrderInvoiceDto dto = orderInvoiceService.getOrderInvoiceById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getByInvoiceId/{id}")
    public ResponseEntity<OrderInvoiceDto> getInvoiceByInvoiceId(@PathVariable("id") Integer invoiceId ) {
        OrderInvoiceDto dto = orderInvoiceService.getOrderInvoiceByInvoiceId(invoiceId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderInvoiceDto> createOrderInvoice(@RequestBody OrderInvoiceDto orderInvoiceDto) {
        OrderInvoiceDto dto = orderInvoiceService.createOrderInvoice(orderInvoiceDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OrderInvoiceDto> updateOrderInvoice(@RequestBody OrderInvoiceDto orderInvoiceDto) {
        OrderInvoiceDto dto = orderInvoiceService.updateOrderInvoice(orderInvoiceDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderInvoice(@PathVariable("id") String id) {
        orderInvoiceService.deleteOrderInvoice(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
