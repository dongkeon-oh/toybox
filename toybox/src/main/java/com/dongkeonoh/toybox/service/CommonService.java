package com.dongkeonoh.toybox.service;

import java.util.List;

import com.dongkeonoh.toybox.vo.CommonDetailVo;
import com.dongkeonoh.toybox.vo.CommonVo;

public interface CommonService {
	// 공통코드 추가
	public int addCommon(CommonVo commonVo);
	// 공통코드 카테고리 보기 
	public List<CommonDetailVo> getCategoryCommon(CommonDetailVo commonDetailVo);
	// 공통코드 카테고리 추가
	public List<CommonDetailVo> addCommonDetail(CommonDetailVo commonDetailVo);
}
