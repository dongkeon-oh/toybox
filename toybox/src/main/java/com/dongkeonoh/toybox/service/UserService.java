package com.dongkeonoh.toybox.service;

import java.util.HashMap;
import java.util.List;

import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.vo.UserVo;

public interface UserService {
	// 유저 추가
	public int putUser(UserVo userVo);
	// 유저 수정
	public int modifyUser(UserVo userVo);
	// 유저 삭제
	public int deleteUser(UserVo userVo);
	

	// 유저 관리메뉴
	// 유저 목록 조회
	public List<UserDto> listUser(HashMap<String, String> map);
	
	// 유저 조회
	public HashMap<String, Object> getUser(HashMap<String, String> map);

	// 유저 수정 
	public int modifyUserAdmin(UserDto userDto);
}
