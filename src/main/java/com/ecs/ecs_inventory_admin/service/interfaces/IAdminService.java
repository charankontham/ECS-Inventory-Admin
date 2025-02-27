package com.ecs.ecs_inventory_admin.service.interfaces;


import com.ecs.ecs_inventory_admin.dto.AdminDto;

import java.util.List;

public interface IAdminService {
    AdminDto getAdminByUsername(String username);
    AdminDto getAdminById(String adminId);
    List<AdminDto> getAllAdmins();
    Object adminLogin(AdminDto adminDto);
    Object addNewAdmin(AdminDto adminDto);
    Object updateAdmin(AdminDto adminDto);
    void deleteAdmin(String username);
}
