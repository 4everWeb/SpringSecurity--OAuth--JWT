package com.won.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.won.security1.model.User;
import com.won.security1.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class IndexController {
	
	private final UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"","/"})
	public String index() {
		//머스테치 기본폴더 src/main/resources
		//view : templates (prefix) , .mustache (suffix) 
		return "index";
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public String manager() {
		return "manager";
	}
	
	//스프링 시큐리티가 기본적으로 인터셉트
	@GetMapping("/login")
	public String login() {
		return "loginForm";
	}
	
	@PostMapping("/join")
	public @ResponseBody String join(User user) {
		System.out.println(user);
		
		user.setRole("ROLE_USER");
		userRepository.save(user);
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		return "redirect:/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@GetMapping("/joinProc")
	public String joinProc() {
		return "회원가입 완료됨!";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	
	// 하나걸땐 Secured 여러개는 preAuthorize 
	@preAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//	@PostAuthorize  method 실행 전후에 권한 체크
	@GetMapping("/data")
	public @ResponseBody String /data() {
		return "데이터정보";
	}
}
