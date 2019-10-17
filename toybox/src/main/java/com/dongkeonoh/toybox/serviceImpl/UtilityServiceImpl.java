package com.dongkeonoh.toybox.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.UtilityDao;
import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.service.UtilityService;

@Service("UtilityServiceImpl")
public class UtilityServiceImpl implements UtilityService{

	@Resource(name="UtilityDao")
	private UtilityDao utilityDao;

	// 유저에 따른 메뉴 조회
	@Override
	public List<CommonCodeDto> getMenu(UserDto userDto){
		List<CommonCodeDto> result = utilityDao.getMenu(userDto);
		return result;
	}
}
