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

	public int putCommonGroup(CommonVo commonVo) {
		int result = sqlSession.insert("commonSql.putCommonGroup", commonVo);		
		return result;
	}

	public int modifyCommonGroup(CommonVo commonVo) {
		int result = sqlSession.update("commonSql.modifyCommonGroup", commonVo);		
		return result;
	}
	
	public int deleteCommonGroup(CommonVo commonVo) {
		int result = sqlSession.update("commonSql.deleteCommonGroup", commonVo);		
		return result;
	}
	
	public int dupeteCommonGroup(CommonVo commonVo) {
		int result = sqlSession.selectOne("commonSql.dupCommonGroup", commonVo);		
		return result;
	}	
	
	public List<CommonVo> listCommonGroup(HashMap<String, String> map) {
		List<CommonVo> result = sqlSession.selectList("commonSql.listCommonGroup", map);
		
		return result;
	} 
	
	

	
	public List<CommonVo> listCommonCode(CommonVo commonVo) {
		List<CommonVo> result = sqlSession.selectList("commonSql.listCommonCode", commonVo);
		
		return result;
	} 
}
