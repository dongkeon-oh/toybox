package com.dongkeonoh.toybox.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.vo.UserVo;

@Repository("UserDao")
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 유저 추가
	public int putUser(UserVo userVo) {
		int result = sqlSession.insert("userSql.putUser", userVo);
		return result;
	}

	// 유저 수정
	public int modifyUser(UserVo userVo) {
		int result = sqlSession.insert("userSql.modifyUser", userVo);
		return result;
	}

	// 유저 활성화 (active)
	public int activeUser(UserVo userVo) {
		int result = sqlSession.update("userSql.activeUser", userVo);
		return result; 
	}

	// 유저 삭제
	public int deleteUser(UserVo userVo) {
		int result = sqlSession.update("userSql.deleteUser", userVo);
		return result;
	}
	
	// 유저 조회
	public UserVo getUser(String userId) {
		UserVo result = sqlSession.selectOne("userSql.getUser", userId);
		return result;
	}
	
	// 유저 목록 조회
	public List<UserVo> listUser(UserVo userVo) {
		List<UserVo> result = sqlSession.selectList("userSql.listUser", userVo);
		return result;
	}
}
