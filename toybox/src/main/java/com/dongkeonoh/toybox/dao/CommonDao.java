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
	
	public List<CommonDetailVo> getCat1Common() {
		List<CommonDetailVo> result = sqlSession.selectList("commonSql.getCat1Common");
		return result;
	}
	
	public List<CommonVo> getCat2Common(CommonVo commonVo) {
		List<CommonVo> result = sqlSession.selectList("commonSql.getCat2Common", commonVo);
		return result;
	}
	
	public List<CommonVo> getCat3Common(CommonVo commonVo) {
		List<CommonVo> result = sqlSession.selectList("commonSql.getCat3Common", commonVo);
		return result;
	}
	
	public int addCommonDetail(CommonDetailVo commonDetailVo) {
		int result = sqlSession.insert("commonSql.addCommonDetail", commonDetailVo);
		return result;
	}
}
