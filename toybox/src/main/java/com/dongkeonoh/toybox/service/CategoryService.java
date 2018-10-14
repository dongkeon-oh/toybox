package com.dongkeonoh.toybox.service;

import java.util.List;

import com.dongkeonoh.toybox.vo.CategoryVo;

public interface CategoryService {
	// 카테고리 보기 
	public List<CategoryVo> getCategory(CategoryVo categoryVo);
	// 카테고리 중복 확인 
	public int getCategoryDuplication(CategoryVo categoryVo);
	// 카테고리 추가
	public List<CategoryVo> putCategory(CategoryVo categoryVo);
	// 카테고리 아이디로 카테고리 코드 조회
	public String getCategoryCode(String cde_id);
}
