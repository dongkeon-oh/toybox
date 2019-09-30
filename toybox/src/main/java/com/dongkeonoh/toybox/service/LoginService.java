package com.dongkeonoh.toybox.service;

import com.dongkeonoh.toybox.vo.UserVo;

public interface LoginService {
	// 유저 조회
	public UserVo getLogin(UserVo userVo);
}
