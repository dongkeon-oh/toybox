package com.dongkeonoh.toybox.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.vo.CommonDetailVo;
import com.dongkeonoh.toybox.vo.CommonVo;

@Repository("CommonDao")
public class CommonDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int addCommon(CommonVo commonVo) {
		int result = sqlSession.insert("commonSql.addCommon", commonVo);
		return result;
	}

	public String getCommonNumber(CommonVo commonVo) {
		String result = sqlSession.selectOne("commonSql.getCommonNumber", commonVo);
		return result;
	}

	public String getCategoryName(String code) {
		String result = sqlSession.selectOne("commonSql.getCategoryName", code);
		return result;
	}
	
	public int dupCategoryCommon(CommonDetailVo commonDetailVo) {
		int result = sqlSession.selectOne("commonSql.dupCategoryCommon", commonDetailVo);
		return result;
	}
	
	public int addCommonDetail(CommonDetailVo commonDetailVo) {
		int result = sqlSession.insert("commonSql.addCommonDetail", commonDetailVo);
		return result;
	}
	
	public List<CommonDetailVo> getCategoryCommon(CommonDetailVo commonDetailVo) {
		List<CommonDetailVo> result = sqlSession.selectList("commonSql.getCategoryCommon", commonDetailVo);
		return result;
	}
}
