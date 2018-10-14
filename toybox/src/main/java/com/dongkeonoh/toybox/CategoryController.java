package com.dongkeonoh.toybox;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongkeonoh.toybox.service.CategoryService;
import com.dongkeonoh.toybox.vo.CategoryVo;

@Controller
public class CategoryController {

	@Resource(name="CategoryServiceImpl")
	private CategoryService categoryService;

	// 카테고리 리스트
	@RequestMapping(value = "/ajax_get_category", method = RequestMethod.POST)
	@ResponseBody
	public List<CategoryVo> ajaxGetCategory(HttpServletRequest httpServletRequest, CategoryVo categoryVo) {
		List<CategoryVo> result = categoryService.getCategory(categoryVo);
		
		return result;
	}

	// 카테고리 중복확인
	// 0 < result : 중복
	@RequestMapping(value = "/ajax_get_category_duplication", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxGetCategoryDuplication(HttpServletRequest httpServletRequest, CategoryVo categoryVo) {
		int result = categoryService.getCategoryDuplication(categoryVo);
		
		return result;
	}
	
	// 공통코드 카테고리 post
	@RequestMapping(value = "/ajax_put_category", method = RequestMethod.POST)
	@ResponseBody
	public List<CategoryVo> ajaxPutCategory(HttpServletRequest httpServletRequest, CategoryVo categoryVo) {
		List<CategoryVo> result = categoryService.putCategory(categoryVo);
		
		return result;
	}
}
