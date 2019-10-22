package com.dongkeonoh.toybox.service;

import java.util.List;

import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.vo.UserVo;

public interface UserService {

	// 유저 추가
	public List<CommonCodeDto> joinUser();
	
	// 유저 추가
	public int putUser(UserDto userDto);
	
	// 유저 수정
	public int modifyUser(UserVo userVo);
	
	// 유저 삭제
	public int deleteUser(UserVo userVo);
}
