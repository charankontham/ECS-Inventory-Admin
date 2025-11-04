package com.ecs.ecs_inventory_admin.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order-invoice")
public class OrderInvoice {
    @Id
    private ObjectId id;
    @Field("invoice_id")
    private Integer invoiceId;
    @Field("invoice_file")
    private byte[] invoiceFile;
}
