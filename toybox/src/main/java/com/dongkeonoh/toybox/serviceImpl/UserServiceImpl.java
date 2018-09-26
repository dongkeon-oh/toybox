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
	public int addUser(UserVo userVo) {
		
		int resultRes = userDao.addUser(userVo);
		return resultRes;
	}

	@Override
	public String deleteUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modifyUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVo viewUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserVo> searchUser(UserVo userVo) {
		
		List<UserVo> userList = userDao.searchUser(userVo);
		return userList;
	}	
}
