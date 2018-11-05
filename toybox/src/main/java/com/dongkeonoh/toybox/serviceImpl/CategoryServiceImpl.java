package com.dongkeonoh.toybox.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.CategoryDao;
import com.dongkeonoh.toybox.service.CategoryService;
import com.dongkeonoh.toybox.vo.CategoryVo;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl implements CategoryService{
	@Resource(name="CategoryDao")
	private CategoryDao categoryDao;
	
	@Override
	public List<CategoryVo> getCategory(CategoryVo categoryVo) {
		List<CategoryVo> result = categoryDao.getCategory(categoryVo);
		
		return result;
	}
	
	@Override
	public int getCategoryDuplication(CategoryVo categoryVo) {
		int result = categoryDao.getCategoryDuplication(categoryVo);
		
		return result;
	}	
	
	@Override
	public List<CategoryVo> putCategory(CategoryVo categoryVo) {
		List<CategoryVo> catList = null;
		int checksum = categoryDao.putCategory(categoryVo);
		if(checksum > 0) {
			catList = categoryDao.getCategory(categoryVo);
		}
		
		return catList;
	}

	@Override
	public String getCategoryCode(String cde_id) {
		String result = categoryDao.getCategoryCode(cde_id);
		
		return result;
	}

	@Override
	public int delCategory(CategoryVo categoryVo) {
		int checksum = categoryDao.delCategory(categoryVo);
		return checksum;
	}
}
