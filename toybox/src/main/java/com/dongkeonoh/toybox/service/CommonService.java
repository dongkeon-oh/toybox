package com.dongkeonoh.toybox.service;

import java.util.HashMap;
import java.util.List;

import com.dongkeonoh.toybox.vo.CommonVo;

public interface CommonService {
	// 공통코드 그룹 리스트 출력
	public List<CommonVo> listCommonGroup(HashMap<String, String> map);
	
	// 공통코드 그룹 조회
	public CommonVo getCommonGroup(String cgr_group);
	
	// 공통코드 그룹 추가
	public int putCommonGroup(CommonVo commonVo);
	
	// 공통코드 그룹 수정
	public int modifyCommonGroup(CommonVo commonVo);
	
	// 공통코드 그룹명 중복확인
	public int dupCommonGroup(CommonVo commonVo);
	
	// 공통코드 그룹 삭제
	public int deleteCommonGroup(CommonVo commonVo);
	


	public int putCommonCode(CommonVo commonVo);
	// 공통코드 리스트
	public List<CommonVo> listCommonCode(CommonVo commonVo);
	// 공통코드 삭제
	public String dupCommonCode(CommonVo commonVo);
	// 공통코드 삭제
	public CommonVo dupCommonOrder(CommonVo commonVo);
	// 공통코드 삭제
	public CommonVo getCommonCodeDetail(CommonVo commonVo);
	// 공통코드 수정
	public int modifyCommonCode(CommonVo commonVo);	
	// 공통코드 삭제
	public int deleteCommonCode(CommonVo commonVo);	
}
