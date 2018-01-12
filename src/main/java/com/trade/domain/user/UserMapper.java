package com.trade.domain.user;

import com.trade.web.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "password",
	         expression = "java(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(dto.getPassword()))")
	User map(UserDto dto);

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}
}
