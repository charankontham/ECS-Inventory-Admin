package com.ecs.ecs_inventory_admin.service;

import com.ecs.ecs_inventory_admin.dto.AdminDto;
import com.ecs.ecs_inventory_admin.entity.Admin;
import com.ecs.ecs_inventory_admin.exception.ResourceNotFoundException;
import com.ecs.ecs_inventory_admin.mapper.AdminMapper;
import com.ecs.ecs_inventory_admin.repository.AdminDataRepository;
import com.ecs.ecs_inventory_admin.service.interfaces.IAdminService;
import com.ecs.ecs_inventory_admin.util.Constants;
import com.ecs.ecs_inventory_admin.validations.AdminValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminDataRepository adminDataRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    @Override
    public AdminDto getAdminByUsername(String username) {
        Admin admin = adminDataRepository.findByAdminUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("Admin not found!"));
        return AdminMapper.mapToAdminDto(admin);
    }

    @Override
    public AdminDto getAdminById(String adminId) {
        Admin admin = adminDataRepository.findById(adminId).
                orElseThrow(() -> new ResourceNotFoundException("Admin not found!"));
        return AdminMapper.mapToAdminDto(admin);
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> admins = adminDataRepository.findAll();
        return admins.stream().map(AdminMapper::mapToAdminDto).toList();
    }

    @Override
    public Object adminLogin(AdminDto adminDto) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            adminDto.getAdminUsername(),
                            adminDto.getAdminPassword())
                    );
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(adminDto.getAdminUsername());
            }
            return HttpStatus.UNAUTHORIZED;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return HttpStatus.UNAUTHORIZED;
        }
    }

    @Override
    public Object addNewAdmin(AdminDto adminDto) {
        if (!AdminValidation.validateAdmin(adminDto)) {
            return HttpStatus.BAD_REQUEST;
        } else if (adminDataRepository.existsByAdminUsername(adminDto.getAdminUsername())
        ) {
            return HttpStatus.CONFLICT;
        } else {
            adminDto.setAdminPassword(bCryptPasswordEncoder.encode(adminDto.getAdminPassword()));
            Admin admin = AdminMapper.mapToAdmin(adminDto);
            AdminDto savedAdmin = AdminMapper.mapToAdminDto(adminDataRepository.save(admin));
            return jwtService.generateToken(savedAdmin.getAdminUsername());
        }
    }

    @Override
    public Object updateAdmin(AdminDto adminDto) {
        if (!adminDataRepository.existsById(adminDto.getId()) || !adminDataRepository.existsByAdminUsername(adminDto.getAdminUsername())) {
            return Constants.AdminNotFound;
        } else if (!AdminValidation.validateAdmin(adminDto)) {
            return HttpStatus.BAD_REQUEST;
        } else {
            Admin admin = adminDataRepository.findById(adminDto.getId()).
                    orElseThrow(() -> new ResourceNotFoundException("Admin not found!"));
            if (!admin.getAdminPassword().equals(adminDto.getAdminPassword())) {
                adminDto.setAdminPassword(bCryptPasswordEncoder.encode(adminDto.getAdminPassword()));
            }
            return AdminMapper.mapToAdminDto(adminDataRepository.save(AdminMapper.mapToAdmin(adminDto)));
        }
    }

    @Override
    public void deleteAdmin(String adminId) {
        adminDataRepository.deleteById(adminId);
    }
}
