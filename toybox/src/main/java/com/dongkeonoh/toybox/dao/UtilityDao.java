package com.dongkeonoh.toybox.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.vo.CommonVo;
import com.dongkeonoh.toybox.vo.UserVo;

@Repository("UtilityDao")
public class UtilityDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 유저에 따른 메뉴 조회
	public List<CommonVo> getMenu(UserVo userVo) {
		List<CommonVo> result = sqlSession.selectList("utilitySql.getMenu", userVo);
		return result;
	}
}
