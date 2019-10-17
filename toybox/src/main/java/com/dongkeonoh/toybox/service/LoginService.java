package com.dongkeonoh.toybox.service;

import com.dongkeonoh.toybox.dto.UserDto;

public interface LoginService {
	// 유저 조회
	public UserDto getLogin(UserDto userDto);
}
