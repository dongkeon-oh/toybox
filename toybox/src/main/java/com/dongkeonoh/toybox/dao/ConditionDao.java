package com.dongkeonoh.toybox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.dto.ItemDto;

@Repository("ConditionDao")
public class ConditionDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 컨디션 생성
	public int putCondition(ItemDto itemDto) {
		int result = sqlSession.insert("ConditionSql.putCondition", itemDto);
		return result;
	}
	
	// 최근 상태 확인
	public ItemDto getCurrentCondition(String item_id) {
		ItemDto result = sqlSession.selectOne("ConditionSql.getCurrentCondition", item_id);
		return result;
	}
}
