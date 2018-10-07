package com.dongkeonoh.toybox.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
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
		int checksum = -1;
		String code_name = commonDao.getCategoryName(commonVo.getCom_category1()) + "_";	
		
		if(commonVo.getCom_category2().equals("deselected")) {
			commonVo.setCom_category2("null2");
		}
		code_name =  code_name + commonDao.getCategoryName(commonVo.getCom_category2()) + "_";		
		
		if(commonVo.getCom_category3().equals("deselected")) {
			commonVo.setCom_category3("null3");		
		}
		code_name = code_name + commonDao.getCategoryName(commonVo.getCom_category3()) + "_";	
		
		String newNumber = "01";
		String codeNumber = commonDao.getCommonNumber(commonVo);		
		if(codeNumber != null) {
			newNumber = codeNumber.substring(14);
		}			
		code_name = code_name + newNumber;
		commonVo.setCom_id(code_name);
		
		checksum = commonDao.addCommon(commonVo);

		return checksum;
	}

	@Override
	public List<CommonVo> getCommonList(HashMap<String, String> map) {
		List<CommonVo> result = commonDao.getCommonList(map);
		return result;
	}

	@Override
	public List<CommonDetailVo> getCategoryCommon(CommonDetailVo commonDetailVo) {
		List<CommonDetailVo> result = commonDao.getCategoryCommon(commonDetailVo);
		
		return result;
	}
	
	@Override
	public List<CommonDetailVo> addCommonDetail(CommonDetailVo commonDetailVo) {
		List<CommonDetailVo> result = new ArrayList<CommonDetailVo>();
		
		int dupChecksum = commonDao.dupCategoryCommon(commonDetailVo);
		if(dupChecksum == 0) {
			int successChecksum = commonDao.addCommonDetail(commonDetailVo);
			if(successChecksum > 0) {
				result = commonDao.getCategoryCommon(commonDetailVo);
			}else {
				// error control
			}
		}
		
		return result;
	}
}
