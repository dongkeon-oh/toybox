package com.dongkeonoh.toybox.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dongkeonoh.toybox.vo.CategoryVo;

@Repository("CategoryDao")
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 상위 카테고리 기반 카테고리 리스트
	public List<CategoryVo> getCategory(CategoryVo CategoryVo) {
		List<CategoryVo> result = sqlSession.selectList("categorySql.getCategory", CategoryVo);
		return result;
	}

	// 카테고리 중복체크
	public int getCategoryDuplication(CategoryVo CategoryVo) {
		int result = sqlSession.selectOne("categorySql.getCategoryDuplication", CategoryVo);
		return result;
	}
	
	// 카테고리 추가
	public int putCategory(CategoryVo CategoryVo) {
		int result = sqlSession.insert("categorySql.putCategory", CategoryVo);
		return result;
	}
	
	// 카테고리 아이디로 카테고리 코드 조회
	public String getCategoryCode(String cde_id) {
		String result = sqlSession.selectOne("categorySql.getCategoryCode", cde_id);
		return result;
	}
}
