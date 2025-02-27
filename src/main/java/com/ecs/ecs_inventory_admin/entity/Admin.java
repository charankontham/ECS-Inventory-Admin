package com.ecs.ecs_inventory_admin.entity;

import com.ecs.ecs_inventory_admin.dto.Role;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "admin-data")
public class Admin{
    @Id
    private String id;
    @Field("admin_username")
    private String adminUsername;
    @Field("admin_name")
    private String adminName;
    @Field("admin_password")
    private String adminPassword;
    @Field("role")
    private Role adminRole;

    @Override
    public String toString() {
        return "AdminDto{" +
                "adminUsername='" + adminUsername + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminRole=" + adminRole +
                '}';
    }
}

