package com.dongkeonoh.toybox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.vo.UserVo;

@Repository("UserAdminDao")
public class UserAdminDao {

	@Autowired
	private SqlSession sqlSession;

	// 유저 관리메뉴	
	// 유저 목록 조회
	public List<UserDto> listUser(HashMap<String, String> map) {
		List<UserDto> result = sqlSession.selectList("userAdminSql.listUser", map);
		return result;
	}
	
	// 유저 조회
	public UserDto getUser(String userId) {
		UserDto result = sqlSession.selectOne("userAdminSql.getUser", userId);
		return result;
	}
	
	// 유저 수정
	public int modifyUserAdmin(UserDto userDto) {
		int result = sqlSession.update("userAdminSql.modifyUserAdmin", userDto);
		return result; 
	}
}
