//TestDao.java
package com.dongkeonoh.toybox.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.vo.CommonVo;

@Repository("TestDao")
public class TestDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CommonVo> testList() {
		List<CommonVo> testList = sqlSession.selectList("testSql.testList");
		
		return testList;
	}
}
