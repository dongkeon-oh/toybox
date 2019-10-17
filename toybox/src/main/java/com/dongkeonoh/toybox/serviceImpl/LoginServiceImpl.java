package com.dongkeonoh.toybox.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.LoginDao;
import com.dongkeonoh.toybox.service.LoginService;
import com.dongkeonoh.toybox.dto.UserDto;

@Service("LoginServiceImpl")
public class LoginServiceImpl implements LoginService{

	@Resource(name="LoginDao")
	private LoginDao loginDao;

	// 유저 조회
	@Override
	public UserDto getLogin(UserDto userDto) {
		UserDto result = loginDao.getLogin(userDto);
		return result;
	}
}
