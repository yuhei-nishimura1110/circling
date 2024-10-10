package com.example.circling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	public Role findByName(String name); 
}
