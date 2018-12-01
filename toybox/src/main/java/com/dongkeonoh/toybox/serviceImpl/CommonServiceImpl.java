package com.dongkeonoh.toybox.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.CategoryDao;
import com.dongkeonoh.toybox.dao.CommonDao;
import com.dongkeonoh.toybox.service.CommonService;
import com.dongkeonoh.toybox.vo.CommonVo;

@Service("CommonServiceImpl")
public class CommonServiceImpl implements CommonService{
	@Resource(name="CommonDao")
	private CommonDao commonDao;

	@Resource(name="CategoryDao")
	private CategoryDao categoryDao;

	@Override
	public int putCommonGroup(CommonVo commonVo) {
		// checksum -1 : 실패
		int checksum = 1;
		checksum = commonDao.putCommonGroup(commonVo);
		return checksum;
	}

	@Override
	public int modifyCommonGroup(CommonVo commonVo) {
		// checksum -1 : 실패
		int checksum = 1;
		checksum = commonDao.modifyCommonGroup(commonVo);
		return checksum;
	}

	@Override
	public int deleteCommonGroup(CommonVo commonVo) {
		// checksum -1 : 실패
		int checksum = 1;
		checksum = commonDao.deleteCommonGroup(commonVo);
		return checksum;
	}

	@Override
	public int dupCommonGroup(CommonVo commonVo) {
		int result = commonDao.dupeteCommonGroup(commonVo);
		return result;
	}

	@Override
	public List<CommonVo> listCommonGroup(HashMap<String, String> map) {
		List<CommonVo> result = commonDao.listCommonGroup(map);		
		return result;
	}
	


	@Override
	public List<CommonVo> listCommonCode(CommonVo commonVo) {
		List<CommonVo> result = commonDao.listCommonCode(commonVo);		
		return result;
	}
	
	@Override
	public int putCommonCode(CommonVo commonVo) {
		// checksum -1 : 실패
		int checksum = 1;
		checksum = commonDao.putCommonCode(commonVo);
		return checksum;
	}

	@Override
	public int dupCommonCode(CommonVo commonVo) {
		int result = commonDao.dupCommonCode(commonVo);
		return result;
	}

	@Override
	public int deleteCommonCode(CommonVo commonVo) {
		// checksum -1 : 실패
		int checksum = 1;
		checksum = commonDao.deleteCommonCode(commonVo);
		return checksum;
	}
}
