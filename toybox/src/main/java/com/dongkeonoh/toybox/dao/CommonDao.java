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
		

	
	
	public int putCommonCode(CommonVo commonVo) {
		int result = sqlSession.insert("commonCodeSql.putCommonCode", commonVo);		
		return result;
	}
	
	public List<CommonVo> listCommonCode(CommonVo commonVo) {
		List<CommonVo> result = sqlSession.selectList("commonCodeSql.listCommonCode", commonVo);
		
		return result;
	} 
	
	public String dupCommonCode(CommonVo commonVo) {
		String result = sqlSession.selectOne("commonCodeSql.dupCommonCode", commonVo);		
		return result;
	}
	
	public CommonVo dupCommonOrder(CommonVo commonVo) {
		CommonVo result = sqlSession.selectOne("commonCodeSql.dupCommonOrder", commonVo);		
		return result;
	}
	
	public CommonVo getCommonCodeDetail(CommonVo commonVo) {
		CommonVo result = sqlSession.selectOne("commonCodeSql.getCommonCodeDetail", commonVo);		
		return result;
	}

	public int modifyCommonCode(CommonVo commonVo) {
		int result = sqlSession.update("commonCodeSql.modifyCommonCode", commonVo);		
		return result;
	}
	
	public int deleteCommonCode(CommonVo commonVo) {
		int result = sqlSession.update("commonCodeSql.deleteCommonCode", commonVo);	
		return result;
	}	
}
