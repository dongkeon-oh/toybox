package com.dongkeonoh.toybox.service;

import com.dongkeonoh.toybox.vo.UserVo;

public interface UserService {
	
	// 유저 추가
	public int putUser(UserVo userVo);
	
	// 유저 수정
	public int modifyUser(UserVo userVo);
	
	// 유저 삭제
	public int deleteUser(UserVo userVo);
}
