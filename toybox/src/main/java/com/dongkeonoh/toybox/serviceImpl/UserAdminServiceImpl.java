package com.dongkeonoh.toybox.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.UserAdminDao;
import com.dongkeonoh.toybox.dao.UtilityDao;
import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.service.UserAdminService;

@Service("UserAdminServiceImpl")
public class UserAdminServiceImpl implements UserAdminService{

	@Resource(name="UserAdminDao")
	private UserAdminDao userAdminDao;

	@Resource(name="UtilityDao")
	private UtilityDao utilityDao;

	// 유저 관리메뉴
	// 유저 목록 조회
	@Override
	public List<UserDto> listUser(HashMap<String, String> map) {		
		List<UserDto> userList = userAdminDao.listUser(map);
		return userList;
	}

	// 유저 조회
	@Override
	public HashMap<String, Object> getUser(String user_id) {
		UserDto user_info = userAdminDao.getUser(user_id);
		
		// 유저 등급
		CommonCodeDto ccd = new CommonCodeDto();
		ccd.setCcd_codename("");
		ccd.setCcd_group("userType");
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("search_type", "_SIMPLE_");
		param.put("ccd", ccd);
		
		List<CommonCodeDto> user_type = utilityDao.getCommonCodeSearch(param);
		
		// 유저 상태 
		ccd.setCcd_group("userActive");
		param.put("ccd", ccd);
		
		List<CommonCodeDto> user_active = utilityDao.getCommonCodeSearch(param);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("user_info", user_info);
		result.put("user_type", user_type);
		result.put("user_active", user_active);
		return result;
	}	
		
	// 유저 활성화 (active)
	@Override
	public int modifyUserAdmin(UserDto userDto) {
		int checksum = userAdminDao.modifyUserAdmin(userDto);
		return checksum;
	}	
}
