package com.example.circling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.circling.entity.User_info;

public interface User_infoRepository extends JpaRepository<User_info, Integer> {
	public User_info findByUser_id(Integer user_id);
}
