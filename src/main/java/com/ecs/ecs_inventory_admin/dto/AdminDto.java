package com.ecs.ecs_inventory_admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto{
    private String id;
    private String adminUsername;
    private String adminName;
    private String adminPassword;
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
