package com.ecs.ecs_inventory_admin.repository;
import com.ecs.ecs_inventory_admin.entity.ImageDoc;
import com.ecs.ecs_inventory_admin.entity.OrderInvoice;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderInvoiceRepository extends MongoRepository<OrderInvoice, ObjectId> {
    Optional<OrderInvoice> findByInvoiceId(Integer invoiceId);
}
