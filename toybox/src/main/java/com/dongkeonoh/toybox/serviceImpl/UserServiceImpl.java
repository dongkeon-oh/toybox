package com.dongkeonoh.toybox.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.UserDao;
import com.dongkeonoh.toybox.dao.UtilityDao;
import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.service.UserService;
import com.dongkeonoh.toybox.vo.UserVo;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{

	@Resource(name="UserDao")
	private UserDao userDao;

	@Resource(name="UtilityDao")
	private UtilityDao utilityDao;
	

	// 사용자 가입
	@Override
	public List<CommonCodeDto> joinUser(){
		HashMap<String , Object> param = new HashMap<String, Object>();
		param.put("search_type", "_SIMPLE_");
		
		CommonCodeDto ccd = new CommonCodeDto();
		ccd.setCcd_group("user_question");
		ccd.setCcd_codename("");
		param.put("ccd", ccd);
		
		List<CommonCodeDto> result = utilityDao.getCommonCodeSearch(param);
		
		return result;
	}
	
	// 유저 추가
	@Override
	public int putUser(UserDto userDto) {
		int checksum = userDao.putUser(userDto);
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
