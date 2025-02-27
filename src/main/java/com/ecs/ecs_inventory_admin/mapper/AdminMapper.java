package com.ecs.ecs_inventory_admin.mapper;

import com.ecs.ecs_inventory_admin.dto.AdminDto;
import com.ecs.ecs_inventory_admin.entity.Admin;

public class AdminMapper {
    public static AdminDto mapToAdminDto(Admin admin) {
        return new AdminDto(admin.getId(),
                admin.getAdminUsername(),
                admin.getAdminName(),
                admin.getAdminPassword(),
                admin.getAdminRole());
    }

    public static Admin mapToAdmin(AdminDto adminDto) {
        return new Admin(
                adminDto.getId(),
                adminDto.getAdminUsername(),
                adminDto.getAdminName(),
                adminDto.getAdminPassword(),
                adminDto.getAdminRole()
        );
    }
}
