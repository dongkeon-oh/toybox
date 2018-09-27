package com.dongkeonoh.toybox.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.vo.ItemVo;

@Repository("ItemDao")
public class ItemDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int addItem(ItemVo itemVo) {
		int result = sqlSession.insert("itemSql.addItem", itemVo);
		return result;
	}
}
