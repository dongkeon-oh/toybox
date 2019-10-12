package com.dongkeonoh.toybox.service;

import java.util.HashMap;
import java.util.List;

import com.dongkeonoh.toybox.vo.CommonVo;

public interface CommonService {
	// 공통 코드 그룹
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
	

	// 공통 코드 
	// 공통코드 리스트
	public List<CommonVo> listCommonCode(String cgr_group);

	// 공통코드 조회
	public CommonVo getCommonCode(String ccd_code);
	
	// 공통코드 추가
	public int putCommonCode(CommonVo commonVo);

	// 공통코드 수정
	public int modifyCommonCode(CommonVo commonVo);	
	
	// 공통 코드 순번 확인
	public CommonVo dupCommonOrderAndName(CommonVo commonVo);
	
	// 공통코드 삭제
	public int deleteCommonCode(CommonVo commonVo);	
}
