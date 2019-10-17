package com.dongkeonoh.toybox.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.UserDao;
import com.dongkeonoh.toybox.dao.UtilityDao;
import com.dongkeonoh.toybox.service.UserService;
import com.dongkeonoh.toybox.vo.UserVo;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{

	@Resource(name="UserDao")
	private UserDao userDao;

	@Resource(name="UtilityDao")
	private UtilityDao utilityDao;
	
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
}
