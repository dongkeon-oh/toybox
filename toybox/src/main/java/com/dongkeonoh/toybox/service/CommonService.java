package com.dongkeonoh.toybox.service;

import java.util.HashMap;
import java.util.List;

import com.dongkeonoh.toybox.dto.CommonCodeDto;

public interface CommonService {
	// 공통 코드 그룹
	// 공통코드 그룹 리스트 출력
	public List<CommonCodeDto> listCommonGroup(HashMap<String, String> map);
	
	// 공통코드 그룹 조회
	public CommonCodeDto getCommonGroup(String cgr_group);
	
	// 공통코드 그룹 추가
	public int putCommonGroup(CommonCodeDto commonCodeDto);
	
	// 공통코드 그룹 수정
	public int modifyCommonGroup(CommonCodeDto commonCodeDto);
	
	// 공통코드 그룹명 중복확인
	public int dupCommonGroup(CommonCodeDto commonCodeDto);
	
	// 공통코드 그룹 삭제
	public int deleteCommonGroup(CommonCodeDto commonCodeDto);
	

	// 공통 코드 
	// 공통코드 리스트
	public List<CommonCodeDto> listCommonCode(String cgr_group);

	// 공통코드 조회
	public CommonCodeDto getCommonCode(String ccd_code);
	
	// 공통코드 추가
	public int putCommonCode(CommonCodeDto commonCodeDto);

	// 공통코드 수정
	public int modifyCommonCode(CommonCodeDto commonCodeDto);	
	
	// 공통 코드 순번 확인
	public CommonCodeDto dupCommonOrderAndName(CommonCodeDto commonCodeDto);
	
	// 공통코드 삭제
	public int deleteCommonCode(CommonCodeDto commonCodeDto);	
}
