package com.dongkeonoh.toybox.service;

import java.util.List;

import com.dongkeonoh.toybox.vo.UserVo;

public interface UserService {
	// 유저 추가
	public int addUser(UserVo userVo);
	// 유저 삭제
	public String deleteUser(UserVo userVo);
	// 유저 정보 수정
	public String modifyUser(UserVo userVo);

	// 유저 보기
	public UserVo viewUser(UserVo userVo);
	// 유저 목록 보기
	public List<UserVo> searchUser(UserVo userVo);
}
