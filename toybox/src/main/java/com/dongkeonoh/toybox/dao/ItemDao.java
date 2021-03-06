package com.dongkeonoh.toybox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.dto.ItemDto;
import com.dongkeonoh.toybox.vo.ItemVo;

@Repository("ItemDao")
public class ItemDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<ItemDto> listItem(HashMap<String, String> search) {
		List<ItemDto> result = sqlSession.selectList("itemSql.listItem", search);
		return result;
	}
	
	public List<ItemDto> listItemBeforeApply(List<String> rent_item_list) {
		List<ItemDto> result = sqlSession.selectList("itemSql.listItemBeforeApply", rent_item_list);
		return result;
	}

	public List<ItemDto> requestItem(HashMap<String, String> search) {
		List<ItemDto> result = sqlSession.selectList("itemSql.requestItem", search);
		return result;
	}

	// 요청 내역 확인
	public List<ItemVo> responseItem(String user_id) {
		List<ItemVo> result = sqlSession.selectList("itemSql.responseItem", user_id);
		return result;
	}
	
	public int putItem(ItemVo item) {
		int result = sqlSession.insert("itemSql.putItem", item);
		return result;
	}
	
	public List<ItemVo> adminItem(HashMap<String, String> search) {
		List<ItemVo> result = sqlSession.selectList("itemSql.adminItem", search);
		return result;
	}
	
	public ItemVo getItem(String itm_id) {
		ItemVo result = sqlSession.selectOne("itemSql.getItem", itm_id);
		return result;
	}
	
	public List<ItemVo> mainItemList(HashMap<String, String> search) {
		List<ItemVo> result = sqlSession.selectList("itemSql.mainItemList", search);
		return result;
	}
}
