package com.dongkeonoh.toybox.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.UserDao;
import com.dongkeonoh.toybox.service.UserService;
import com.dongkeonoh.toybox.vo.UserVo;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{

	@Resource(name="UserDao")
	private UserDao userDao;
	
	// 유저 추가
	@Override
	public int putUser(UserVo userVo) {
		int checksum = userDao.putUser(userVo);
		return checksum;
	}
	
	// 유저 수정
	@Override
	public int modifyUser(UserVo userVo) {
		int checksum = userDao.modifyUser(userVo);
		return checksum;
	}

	// 유저 삭제
	@Override
	public int deleteUser(UserVo userVo) {
		int checksum = userDao.deleteUser(userVo);
		return checksum;
	}

	// 유저 활성화 (active)
	@Override
	public int activeUser(UserVo userVo) {
		int checksum = userDao.activeUser(userVo);
		return checksum;
	}

	// 유저 조회
	@Override
	public UserVo getUser(String userId) {
		UserVo result = userDao.getUser(userId);
		return result;
	}

	// 유저 목록 조회
	@Override
	public List<UserVo> listUser(HashMap<String, String> map) {		
		List<UserVo> userList = userDao.listUser(map);
		return userList;
	}		
}
