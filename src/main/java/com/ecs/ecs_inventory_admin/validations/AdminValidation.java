package com.ecs.ecs_inventory_admin.validations;

import com.ecs.ecs_inventory_admin.dto.AdminDto;
import com.ecs.ecs_inventory_admin.dto.Role;

public class AdminValidation {
    public static boolean validateAdmin(AdminDto adminDto) {
        return BasicValidation.stringValidation(adminDto.getAdminName()) ||
                BasicValidation.stringValidation(adminDto.getAdminUsername()) ||
                validateAdminRole(adminDto.getAdminRole()) ||
                BasicValidation.stringValidation(adminDto.getAdminPassword());
    }

    public static boolean validateAdminRole(Role role) {
        return BasicValidation.stringValidation(role.getRoleName()) ||
                BasicValidation.stringValidation(role.getSubRole());
    }
}
