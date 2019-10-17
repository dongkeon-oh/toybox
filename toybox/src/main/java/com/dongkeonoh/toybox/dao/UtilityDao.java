package com.dongkeonoh.toybox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.dto.ItemDto;
import com.dongkeonoh.toybox.dto.UserDto;

@Repository("UtilityDao")
public class UtilityDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 유저에 따른 메뉴 조회
	public List<CommonCodeDto> getMenu(UserDto userDto) {
		List<CommonCodeDto> result = sqlSession.selectList("utilitySql.getMenu", userDto);
		return result;
	}
	
	// 공통코드 출력 기본
	public List<CommonCodeDto> getCommonCodeSearch(HashMap<String, Object> map) {
		List<CommonCodeDto> result = sqlSession.selectList("utilitySql.getCommonCodeSearch", map);
		return result;
	}
	
	// 유저 검색
	public List<UserDto> getUserSearch(HashMap<String, String> map) {
		List<UserDto> result = sqlSession.selectList("utilitySql.getUserSearch", map);
		return result;
	}
	
	// 아이템 검색
	public List<ItemDto> getItemSearch(HashMap<String, String> map) {
		List<ItemDto> result = sqlSession.selectList("utilitySql.getItemSearch", map);
		return result;
	}
}
