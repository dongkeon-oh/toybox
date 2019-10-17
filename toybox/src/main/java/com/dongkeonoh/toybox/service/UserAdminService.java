package com.dongkeonoh.toybox.service;

import java.util.HashMap;
import java.util.List;

import com.dongkeonoh.toybox.dto.UserDto;

public interface UserAdminService {

	// 유저 관리메뉴
	// 유저 목록 조회
	public List<UserDto> listUser(HashMap<String, String> map);
	
	// 유저 조회
	public HashMap<String, Object> getUser(String user_id);

	// 유저 수정 
	public int modifyUserAdmin(UserDto userDto);
}
