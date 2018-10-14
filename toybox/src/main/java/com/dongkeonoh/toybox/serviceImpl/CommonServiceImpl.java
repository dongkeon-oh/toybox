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
	public int putCommon(CommonVo commonVo) {
		// checksum -1 : 실패
		int checksum = -1;
		
		// code1 조회
		String code_name = categoryDao.getCategoryCode(commonVo.getCom_category1()) + "_";	

		// code2 조회. 빈값 반환시 null로 세팅
		if(commonVo.getCom_category2().equals("deselected")) {
			commonVo.setCom_category2("null2");
			code_name = code_name + "null_";
		}else {
			code_name =  code_name + categoryDao.getCategoryCode(commonVo.getCom_category2()) + "_";	
		}

		// code3 조회. 빈값 반환시 nul로 세팅
		if(commonVo.getCom_category3().equals("deselected")) {
			commonVo.setCom_category3("null3");		
			code_name = code_name + "nul_";
		}else {
			code_name = code_name + categoryDao.getCategoryCode(commonVo.getCom_category3()) + "_";	
		}
		
		// 뒷자리 세팅
		int newNumber = 1;
		String codeNumber = commonDao.getCommonNumber(commonVo);		
		if(codeNumber != null) {
			newNumber = Integer.parseInt(codeNumber.substring(14));
			newNumber = newNumber + 1;
		}
		
		if(newNumber > 99) {
			checksum = newNumber;
		}else {
			code_name = code_name + String.format("%02d", newNumber);
			commonVo.setCom_id(code_name);
			
			checksum = commonDao.putCommon(commonVo);
		}

		return checksum;
	}

	@Override
	public List<CommonVo> getCommonList(HashMap<String, String> map) {
		List<CommonVo> result = commonDao.getCommonList(map);
		
		return result;
	}
}
