package com.dongkeonoh.toybox.service;

import java.util.List;

import com.dongkeonoh.toybox.vo.CommonVo;
import com.dongkeonoh.toybox.vo.UserVo;

public interface UtilityService {
	// 유저에 따른 메뉴 조회
	public List<CommonVo> getMenu(UserVo userVo);
}
