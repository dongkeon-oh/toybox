package com.dongkeonoh.toybox.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.CommonDao;
import com.dongkeonoh.toybox.service.CommonService;
import com.dongkeonoh.toybox.vo.CommonDetailVo;
import com.dongkeonoh.toybox.vo.CommonVo;

@Service("CommonServiceImpl")
public class CommonServiceImpl implements CommonService{

	@Resource(name="CommonDao")
	private CommonDao commonDao;
	
	@Override
	public int addCommon(CommonVo commonVo) {
		int checksum = commonDao.addCommon(commonVo);
		return checksum;
	}

	@Override
	public List<CommonDetailVo> getCategoryCommon(CommonDetailVo commonDetailVo) {
		List<CommonDetailVo> result = commonDao.getCategoryCommon(commonDetailVo);
		
		return result;
	}
	
	@Override
	public List<CommonDetailVo> addCommonDetail(CommonDetailVo commonDetailVo) {
		int checksum = commonDao.addCommonDetail(commonDetailVo);
		List<CommonDetailVo> result = new ArrayList<CommonDetailVo>();
		if(checksum > 0) {
			result = commonDao.getCategoryCommon(commonDetailVo);
		}else {
			// error control
		}
		
		return result;
	}
}
