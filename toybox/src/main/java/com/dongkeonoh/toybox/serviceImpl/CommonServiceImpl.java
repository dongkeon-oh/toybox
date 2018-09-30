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
	public List<CommonDetailVo> getCat1Common() {
		List<CommonDetailVo> result = commonDao.getCat1Common();
		return result;
	}

	@Override
	public List<CommonVo> getCat2Common(CommonVo commonVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommonVo> getCat3Common(CommonVo commonVo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CommonDetailVo> addCommonDetail(CommonDetailVo commonDetailVo) {
		int checksum = commonDao.addCommonDetail(commonDetailVo);
		List<CommonDetailVo> result = new ArrayList<CommonDetailVo>();
		if(checksum > 0) {
			result = commonDao.getCat1Common();
		}
		
		return result;
	}
}
