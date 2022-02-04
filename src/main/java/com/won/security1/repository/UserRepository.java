package com.won.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.won.security1.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	//findBy규칙 -> Username 문법
	// select * from user where username =1?
	public User findByUsername(String username); // Jpa 

// select * from user where email = ?
//	public User findByEmail();
}
