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

	// 유저 관리메뉴
	// 유저 목록 조회
	@Override
	public List<UserDto> listUser(HashMap<String, String> map) {		
		List<UserDto> userList = userDao.listUser(map);
		return userList;
	}

	// 유저 조회
	@Override
	public HashMap<String, Object> getUser(HashMap<String, String> map) {
		UserDto user_info = userDao.getUser(map.get("userId"));
		List<CommonCodeDto> user_type = utilityDao.getCommonCode(map.get("userType"));
		List<CommonCodeDto> user_active = utilityDao.getCommonCode(map.get("userActive"));
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("user_info", user_info);
		result.put("user_type", user_type);
		result.put("user_active", user_active);
		return result;
	}	
		
	// 유저 활성화 (active)
	@Override
	public int modifyUserAdmin(UserDto userDto) {
		int checksum = userDao.modifyUserAdmin(userDto);
		return checksum;
	}	
}
