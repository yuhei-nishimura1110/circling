package com.example.circling.circling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.circling.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByName(String name);
}
