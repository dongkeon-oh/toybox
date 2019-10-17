package com.dongkeonoh.toybox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.dto.CommonCodeDto;

@Repository("CommonDao")
public class CommonDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 공통코드 그룹 목록 조회
	public List<CommonCodeDto> listCommonGroup(HashMap<String, String> map) {
		List<CommonCodeDto> result = sqlSession.selectList("commonCodeSql.listCommonGroup", map);		
		return result;
	} 

	// 공통코드 그룹 조회
	public CommonCodeDto getCommonGroup(String cgr_group) {
		CommonCodeDto result = sqlSession.selectOne("commonCodeSql.getCommonGroup", cgr_group);		
		return result;
	} 
	
	// 공통코드 그룹 추가
	public int putCommonGroup(CommonCodeDto commonCodeDto) {
		int result = sqlSession.insert("commonCodeSql.putCommonGroup", commonCodeDto);		
		return result;
	}

	// 공통코드 그룹 수정
	public int modifyCommonGroup(CommonCodeDto commonCodeDto) {
		int result = sqlSession.update("commonCodeSql.modifyCommonGroup", commonCodeDto);		
		return result;
	}

	// 공통코드 그룹 중복확인
	public int dupeteCommonGroup(CommonCodeDto commonCodeDto) {
		int result = sqlSession.selectOne("commonCodeSql.dupCommonGroup", commonCodeDto);		
		return result;
	}
	
	// 공통코드 그룹 삭제
	public int deleteCommonGroup(CommonCodeDto commonCodeDto) {
		int result = sqlSession.update("commonCodeSql.deleteCommonGroup", commonCodeDto);		
		return result;
	}
		

	

	// 공통코드 리스트
	public List<CommonCodeDto> listCommonCode(String cgr_group) {
		List<CommonCodeDto> result = sqlSession.selectList("commonCodeSql.listCommonCode", cgr_group);
		return result;
	} 
	
	// 공통 코드 조회
	public CommonCodeDto getCommonCode(String ccd_code) {
		CommonCodeDto result = sqlSession.selectOne("commonCodeSql.getCommonCode", ccd_code);		
		return result;
	}

	// 공통 코드 추가
	public int putCommonCode(CommonCodeDto commonCodeDto) {
		int result = sqlSession.insert("commonCodeSql.putCommonCode", commonCodeDto);		
		return result;
	}

	// 공통 코드 수정
	public int modifyCommonCode(CommonCodeDto commonCodeDto) {
		int result = sqlSession.update("commonCodeSql.modifyCommonCode", commonCodeDto);		
		return result;
	}

	// 공통 코드 순번 확인
	public CommonCodeDto dupCommonOrderAndName(CommonCodeDto commonCodeDto) {
		CommonCodeDto result = sqlSession.selectOne("commonCodeSql.dupCommonOrderAndName", commonCodeDto);		
		return result;
	}
	
	public int deleteCommonCode(CommonCodeDto commonCodeDto) {
		int result = sqlSession.delete("commonCodeSql.deleteCommonCode", commonCodeDto);	
		return result;
	}	
}
