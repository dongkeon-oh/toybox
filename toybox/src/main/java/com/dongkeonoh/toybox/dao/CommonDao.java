package com.dongkeonoh.toybox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.vo.CommonVo;

@Repository("CommonDao")
public class CommonDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 공통코드 그룹 목록 조회
	public List<CommonVo> listCommonGroup(HashMap<String, String> map) {
		List<CommonVo> result = sqlSession.selectList("commonCodeSql.listCommonGroup", map);		
		return result;
	} 

	// 공통코드 그룹 조회
	public CommonVo getCommonGroup(String cgr_group) {
		CommonVo result = sqlSession.selectOne("commonCodeSql.getCommonGroup", cgr_group);		
		return result;
	} 
	
	// 공통코드 그룹 추가
	public int putCommonGroup(CommonVo commonVo) {
		int result = sqlSession.insert("commonCodeSql.putCommonGroup", commonVo);		
		return result;
	}

	// 공통코드 그룹 수정
	public int modifyCommonGroup(CommonVo commonVo) {
		int result = sqlSession.update("commonCodeSql.modifyCommonGroup", commonVo);		
		return result;
	}

	// 공통코드 그룹 중복확인
	public int dupeteCommonGroup(CommonVo commonVo) {
		int result = sqlSession.selectOne("commonCodeSql.dupCommonGroup", commonVo);		
		return result;
	}
	
	// 공통코드 그룹 삭제
	public int deleteCommonGroup(CommonVo commonVo) {
		int result = sqlSession.update("commonCodeSql.deleteCommonGroup", commonVo);		
		return result;
	}
		

	

	// 공통코드 리스트
	public List<CommonVo> listCommonCode(String cgr_group) {
		List<CommonVo> result = sqlSession.selectList("commonCodeSql.listCommonCode", cgr_group);
		return result;
	} 
	
	// 공통 코드 조회
	public CommonVo getCommonCode(String ccd_code) {
		CommonVo result = sqlSession.selectOne("commonCodeSql.getCommonCode", ccd_code);		
		return result;
	}

	// 공통 코드 추가
	public int putCommonCode(CommonVo commonVo) {
		int result = sqlSession.insert("commonCodeSql.putCommonCode", commonVo);		
		return result;
	}

	// 공통 코드 수정
	public int modifyCommonCode(CommonVo commonVo) {
		int result = sqlSession.update("commonCodeSql.modifyCommonCode", commonVo);		
		return result;
	}

	// 공통 코드 순번 확인
	public CommonVo dupCommonOrderAndName(CommonVo commonVo) {
		CommonVo result = sqlSession.selectOne("commonCodeSql.dupCommonOrderAndName", commonVo);		
		return result;
	}
	
	public int deleteCommonCode(CommonVo commonVo) {
		int result = sqlSession.delete("commonCodeSql.deleteCommonCode", commonVo);	
		return result;
	}	
}
