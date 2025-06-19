package com.ecs.ecs_inventory_admin.controller;

import com.ecs.ecs_inventory_admin.dto.AdminDto;
import com.ecs.ecs_inventory_admin.service.interfaces.IAdminService;
import com.ecs.ecs_inventory_admin.util.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminDataController {
    @Autowired
    private IAdminService adminService;

    @GetMapping("/")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable("id") String adminId) {
        AdminDto response = adminService.getAdminById(adminId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<AdminDto> getAdminByUsername(@PathVariable("username") String adminUsername) {
        AdminDto response = adminService.getAdminByUsername(adminUsername);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody AdminDto adminDto) {
        Object response = adminService.adminLogin(adminDto);
        if (response.equals(HttpStatus.UNAUTHORIZED)) {
            return new ResponseEntity<>("Wrong credentials!", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> addAdmin(@RequestBody AdminDto adminDto) {
        Object newAdmin = adminService.addNewAdmin(adminDto);
        ResponseEntity<?> response =  HelperFunctions.getResponseEntity(newAdmin);
        if(response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
        }else{
            return response;
        }
    }

    @PutMapping
    public ResponseEntity<?> updateAdmin(@RequestBody AdminDto adminDto) {
        Object updatedAdmin = adminService.updateAdmin(adminDto);
        return HelperFunctions.getResponseEntity(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") String adminId) {
        adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
