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

	public int putCommon(CommonVo commonVo) {
		int result = sqlSession.insert("commonSql.putCommon", commonVo);
		
		return result;
	}

	public String getCommonNumber(CommonVo commonVo) {
		String result = sqlSession.selectOne("commonSql.getCommonNumber", commonVo);
		
		return result;
	}
	
	public List<CommonVo> getCommonList(HashMap<String, String> map) {
		List<CommonVo> result = sqlSession.selectList("commonSql.getCommonList", map);
		
		return result;
	}
}
