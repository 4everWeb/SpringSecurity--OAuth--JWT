package com.won.security1.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.won.security1.model.User;
import com.won.security1.repository.UserRepository;

// 시큐리티 config에서 loginProcessingUrl("/login");
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행 (규칙 )
@Service
public  class PrincipalDetailsService implements UserDetailsService{
	
	private UserRepository userRepository;
	
	//시큐리티 session <= Authentication <= UserDetails
	//loadUserByUsername 의 파라미터 username === 로그인 폼의 name 과 같아야함 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		
		return null;
	}

}
