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
	
	public int addUser(UserVo userVo) {
		int result = sqlSession.insert("userSql.addUser", userVo);
		return result;
	}
	
	public UserVo viewUser(String userId) {
		UserVo result = sqlSession.selectOne("userSql.viewUser", userId);
		return result;
	}
	
	public int modifyUser(UserVo userVo) {
		int result = sqlSession.update("userSql.modifyUser", userVo);
		return result; 
	}
	
	public int deleteUser(UserVo userVo) {
		int result = sqlSession.update("userSql.deleteUser", userVo);
		return result;
	}
	
	public int activeUser(UserVo userVo) {
		int result = sqlSession.update("userSql.activeUser", userVo);
		return result;
	}
	
	public List<UserVo> searchUser(UserVo userVo) {
		List<UserVo> result = sqlSession.selectList("userSql.searchUser", userVo);
		return result;
	}
}
