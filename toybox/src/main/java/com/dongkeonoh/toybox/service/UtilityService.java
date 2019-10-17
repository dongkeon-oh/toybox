package com.dongkeonoh.toybox.service;

import java.util.List;

import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.dto.UserDto;

public interface UtilityService {
	// 유저에 따른 메뉴 조회
	public List<CommonCodeDto> getMenu(UserDto userDto);
}
