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
	public int putUser(UserVo userVo) {
		int result = sqlSession.insert("userSql.putUser", userVo);
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

	// 유저 관리메뉴	
	// 유저 목록 조회
	public List<UserDto> listUser(HashMap<String, String> map) {
		List<UserDto> result = sqlSession.selectList("userSql.listUser", map);
		return result;
	}
	
	// 유저 조회
	public UserDto getUser(String userId) {
		UserDto result = sqlSession.selectOne("userSql.getUser", userId);
		return result;
	}
	
	// 유저 수정
	public int modifyUserAdmin(UserDto userDto) {
		int result = sqlSession.update("userSql.modifyUserAdmin", userDto);
		return result; 
	}
}
