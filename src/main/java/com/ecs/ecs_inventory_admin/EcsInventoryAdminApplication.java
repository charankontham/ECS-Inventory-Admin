package com.ecs.ecs_inventory_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcsInventoryAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcsInventoryAdminApplication.class, args);
	}

}
