package com.dongkeonoh.toybox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.vo.UserVo;

@Repository("UserDao")
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 유저 추가
	public int putUser(UserDto userDto) {
		int result = sqlSession.insert("userSql.putUser", userDto);
		return result;
	}

	// 유저 수정
	public int modifyUser(UserVo userVo) {
		int result = sqlSession.insert("userSql.modifyUser", userVo);
		return result;
	}

	// 유저 삭제
	public int deleteUser(UserVo userVo) {
		int result = sqlSession.update("userSql.deleteUser", userVo);
		return result;
	}
}
