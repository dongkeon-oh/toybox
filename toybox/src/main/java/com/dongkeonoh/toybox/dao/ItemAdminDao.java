package com.dongkeonoh.toybox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.dto.ItemDto;

@Repository("ItemAdminDao")
public class ItemAdminDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 아이템 저장
	public int putItem(ItemDto item) {
		int result = sqlSession.insert("itemAdminSql.putItem", item);
		String id = item.getItm_id();
		return result;
	}

	// 아이템 조회
	public List<ItemDto> listItem(HashMap<String, String> map) {
		List<ItemDto> result = sqlSession.selectList("itemAdminSql.listItem", map);
		return result;
	}

	// 아이템 상세 조회
	public ItemDto detailItem(String itm_id) {
		ItemDto result = sqlSession.selectOne("itemAdminSql.detailItem", itm_id);
		return result;
	}
	
	// 아이템 저장
	public int updateItem(ItemDto item) {
		int result = sqlSession.insert("itemAdminSql.updateItem", item);
		return result;
	}
}
