package com.dongkeonoh.toybox.service;

import java.util.HashMap;
import java.util.List;

import com.dongkeonoh.toybox.vo.CommonVo;

public interface CommonService {
	// 공통코드 추가
	public int putCommon(CommonVo commonVo);
	// 공통코드 추가시 번호 확인
	public List<CommonVo> getCommonList(HashMap<String, String> map);
}
