package com.dongkeonoh.toybox.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.CommonDao;
import com.dongkeonoh.toybox.service.CommonService;
import com.dongkeonoh.toybox.vo.CommonVo;

@Service("CommonServiceImpl")
public class CommonServiceImpl implements CommonService{
	@Resource(name="CommonDao")
	private CommonDao commonDao;

	@Override	// 공통코드 그룹 리스트 출력
	public List<CommonVo> listCommonGroup(HashMap<String, String> map) {
		List<CommonVo> result = commonDao.listCommonGroup(map);		
		return result;
	}

	@Override	// 공통코드 그룹 조회
	public CommonVo getCommonGroup(String cgr_group) {
		CommonVo result = commonDao.getCommonGroup(cgr_group);
		return result;
	}

	@Override	// 공통코드 그룹 추가
	public int putCommonGroup(CommonVo commonVo) {
		int checksum = commonDao.putCommonGroup(commonVo);
		return checksum;
	}

	@Override	// 공통코드 그룹 수정
	public int modifyCommonGroup(CommonVo commonVo) {
		int checksum = commonDao.modifyCommonGroup(commonVo);
		return checksum;
	}

	@Override	// 공통코드 그룹명 중복확인
	public int dupCommonGroup(CommonVo commonVo) {
		int result = commonDao.dupeteCommonGroup(commonVo);
		return result;
	}

	@Override	// 공통코드 그룹 삭제
	public int deleteCommonGroup(CommonVo commonVo) {
		int checksum = commonDao.deleteCommonGroup(commonVo);
		return checksum;
	}
	


	@Override	// 공통코드 리스트
	public List<CommonVo> listCommonCode(String cgr_group) {
		List<CommonVo> result = commonDao.listCommonCode(cgr_group);		
		return result;
	}

	@Override	// 공통코드 조회
	public CommonVo getCommonCode(String ccd_code) {
		CommonVo result = commonDao.getCommonCode(ccd_code);
		return result;
	}
	
	@Override	// 공통코드 추가
	public int putCommonCode(CommonVo commonVo) {
		int checksum = commonDao.putCommonCode(commonVo);
		return checksum;
	}

	@Override	// 공통코드 수정
	public int modifyCommonCode(CommonVo commonVo) {
		int checksum = commonDao.modifyCommonCode(commonVo);
		return checksum;
	}

	@Override	// 공통코드 순번 확인
	public CommonVo dupCommonOrderAndName(CommonVo commonVo) {
		CommonVo result = commonDao.dupCommonOrderAndName(commonVo);
		return result;
	}
	
	@Override
	public int deleteCommonCode(CommonVo commonVo) {
		int checksum = commonDao.deleteCommonCode(commonVo);
		return checksum;
	}
}
