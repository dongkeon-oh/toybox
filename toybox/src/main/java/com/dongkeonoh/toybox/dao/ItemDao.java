package com.dongkeonoh.toybox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.vo.ItemVo;
import com.dongkeonoh.toybox.vo.UserVo;

@Repository("ItemDao")
public class ItemDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<ItemVo> listItem(HashMap<String, String> search) {
		List<ItemVo> result = sqlSession.selectList("itemSql.listItem", search);
		return result;
	}
	
	public List<ItemVo> listItemBeforeApply(List<String> rent_item_list) {
		List<ItemVo> result = sqlSession.selectList("itemSql.listItemBeforeApply", rent_item_list);
		return result;
	}
	
	public List<UserVo> listItemReturnPoint() {
		List<UserVo> result = sqlSession.selectList("itemSql.listItemReturnPoint");
		return result;
	}
	
	public List<ItemVo> listItemBeforeRent(HashMap<String, Object> search) {
		List<ItemVo> result = sqlSession.selectList("itemSql.listItemBeforeRent");
		return result;
	}
	
	public int rentItem(List<ItemVo> rent_item_list) {
		int result = sqlSession.insert("itemSql.rentItem", rent_item_list);
		return result;
	}
	
	public List<ItemVo> requestItem(HashMap<String, String> search) {
		List<ItemVo> result = sqlSession.selectList("itemSql.requestItem", search);
		return result;
	}

	// 요청 내역 확인
	public List<ItemVo> responseItem(String user_id) {
		List<ItemVo> result = sqlSession.selectList("itemSql.responseItem", user_id);
		return result;
	}
}
