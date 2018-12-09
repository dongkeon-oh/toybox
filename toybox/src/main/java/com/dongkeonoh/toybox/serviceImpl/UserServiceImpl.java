package com.dongkeonoh.toybox.serviceImpl;

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
	
	@Override
	public int putUser(UserVo userVo) {
		int checksum = userDao.putUser(userVo);
		return checksum;
	}
	
	@Override
	public int addUser(UserVo userVo) {
		int checksum = userDao.addUser(userVo);
		return checksum;
	}

	@Override
	public UserVo viewUser(String userId) {
		UserVo result = userDao.viewUser(userId);
		return result;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		int checksum = userDao.modifyUser(userVo);
		return checksum;
	}

	@Override
	public int deleteUser(UserVo userVo) {
		int checksum = userDao.deleteUser(userVo);
		return checksum;
	}

	@Override
	public int activeUser(UserVo userVo) {
		int checksum = userDao.activeUser(userVo);
		return checksum;
	}

	@Override
	public List<UserVo> searchUser(UserVo userVo) {
		
		List<UserVo> userList = userDao.searchUser(userVo);
		return userList;
	}	
}
