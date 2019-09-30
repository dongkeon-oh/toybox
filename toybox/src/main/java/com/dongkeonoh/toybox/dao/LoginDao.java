package com.dongkeonoh.toybox.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.vo.UserVo;

@Repository("LoginDao")
public class LoginDao {

	@Autowired
	private SqlSession sqlSession;

	// 유저 조회
	public UserVo getLogin(UserVo userVo) {
		UserVo result = sqlSession.selectOne("loginSql.getLogin", userVo);
		return result;
	}
}
