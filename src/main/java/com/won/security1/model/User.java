package com.won.security1.model;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Entity
@Data
public class User {
	@Id //primaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;  
	private Timestamp loginDate;
	@CreationTimestamp
	private Timestamp createDate;  
}
