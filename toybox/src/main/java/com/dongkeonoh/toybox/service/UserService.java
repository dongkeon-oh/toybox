package com.dongkeonoh.toybox.service;

import java.util.List;

import com.dongkeonoh.toybox.vo.UserVo;

public interface UserService {
	// 유저 추가
	public int putUser(UserVo userVo);
	// 유저 수정
	public int modifyUser(UserVo userVo);
	// 유저 활성화 (active)
	public int activeUser(UserVo userVo);
	// 유저 삭제
	public int deleteUser(UserVo userVo);
	// 유저 조회
	public UserVo getUser(String userId);
	// 유저 목록 조회
	public List<UserVo> listUser(UserVo userVo);
}
