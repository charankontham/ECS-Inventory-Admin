package com.ecs.ecs_inventory_admin.service;

import com.ecs.ecs_inventory_admin.dto.AdminDto;
import com.ecs.ecs_inventory_admin.dto.UserPrincipal;
import com.ecs.ecs_inventory_admin.entity.Admin;
import com.ecs.ecs_inventory_admin.exception.ResourceNotFoundException;
import com.ecs.ecs_inventory_admin.mapper.AdminMapper;
import com.ecs.ecs_inventory_admin.repository.AdminDataRepository;
import com.ecs.ecs_inventory_admin.service.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserAuthenticationDetails implements UserDetailsService {

    @Autowired
    private AdminDataRepository adminDataRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try
        {
            Admin admin = adminDataRepository.findByAdminUsername(username).
                    orElseThrow(() -> new ResourceNotFoundException("Admin not found!"));
            if (Objects.isNull(admin)) {
                throw new ResourceNotFoundException("Admin not found!");
            } else {
                return new UserPrincipal(admin);
            }
        } catch(ResourceNotFoundException error){
            throw new UsernameNotFoundException(error.getMessage());
        }
    }
}
