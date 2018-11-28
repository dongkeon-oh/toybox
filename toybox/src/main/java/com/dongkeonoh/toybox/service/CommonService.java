package com.dongkeonoh.toybox.service;

import java.util.HashMap;
import java.util.List;

import com.dongkeonoh.toybox.vo.CommonVo;

public interface CommonService {
	// 공통코드 추가
	public int putCommonGroup(CommonVo commonVo);
	// 공통코드 수정
	public int modifyCommonGroup(CommonVo commonVo);
	// 공통코드 삭제
	public int deleteCommonGroup(CommonVo commonVo);
	// 공통코드 삭제
	public int dupCommonGroup(CommonVo commonVo);
	// 공통코드 추가시 번호 확인
	public List<CommonVo> listCommonGroup(HashMap<String, String> map);
	

	// 공통코드 리스트
	public List<CommonVo> listCommonCode(CommonVo commonVo);

	public int putCommonCode(CommonVo commonVo);	
}
