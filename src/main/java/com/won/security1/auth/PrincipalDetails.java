package com.won.security1.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.won.security1.model.User;

// 시큐리티가 /login 주소 요청이 오면 인터셉트 로그인 진행
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다.(Security ContextHolder)
// 오브젝트 = > Authentication 타입 객체
// Authentication 안에 User 정보가 있어 함.
// User 오브젝트 타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails (PrincipalDetails)


public class PrincipalDetails implements UserDetails{
	
	private User user;

	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	//해당 User의 권한을 리턴 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				
				return user.getRole();
			}
			
		});
		
		user.getRole();
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
