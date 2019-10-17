package com.dongkeonoh.toybox.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.CommonDao;
import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.service.CommonService;

@Service("CommonServiceImpl")
public class CommonServiceImpl implements CommonService{
	@Resource(name="CommonDao")
	private CommonDao commonDao;

	@Override	// 공통코드 그룹 리스트 출력
	public List<CommonCodeDto> listCommonGroup(HashMap<String, String> map) {
		List<CommonCodeDto> result = commonDao.listCommonGroup(map);		
		return result;
	}

	@Override	// 공통코드 그룹 조회
	public CommonCodeDto getCommonGroup(String cgr_group) {
		CommonCodeDto result = commonDao.getCommonGroup(cgr_group);
		return result;
	}

	@Override	// 공통코드 그룹 추가
	public int putCommonGroup(CommonCodeDto commonCodeDto) {
		int checksum = commonDao.putCommonGroup(commonCodeDto);
		return checksum;
	}

	@Override	// 공통코드 그룹 수정
	public int modifyCommonGroup(CommonCodeDto commonCodeDto) {
		int checksum = commonDao.modifyCommonGroup(commonCodeDto);
		return checksum;
	}

	@Override	// 공통코드 그룹명 중복확인
	public int dupCommonGroup(CommonCodeDto commonCodeDto) {
		int result = commonDao.dupeteCommonGroup(commonCodeDto);
		return result;
	}

	@Override	// 공통코드 그룹 삭제
	public int deleteCommonGroup(CommonCodeDto commonCodeDto) {
		int checksum = commonDao.deleteCommonGroup(commonCodeDto);
		return checksum;
	}
	


	@Override	// 공통코드 리스트
	public List<CommonCodeDto> listCommonCode(String cgr_group) {
		List<CommonCodeDto> result = commonDao.listCommonCode(cgr_group);		
		return result;
	}

	@Override	// 공통코드 조회
	public CommonCodeDto getCommonCode(String ccd_code) {
		CommonCodeDto result = commonDao.getCommonCode(ccd_code);
		return result;
	}
	
	@Override	// 공통코드 추가
	public int putCommonCode(CommonCodeDto commonCodeDto) {
		int checksum = commonDao.putCommonCode(commonCodeDto);
		return checksum;
	}

	@Override	// 공통코드 수정
	public int modifyCommonCode(CommonCodeDto commonCodeDto) {
		int checksum = commonDao.modifyCommonCode(commonCodeDto);
		return checksum;
	}

	@Override	// 공통코드 순번 확인
	public CommonCodeDto dupCommonOrderAndName(CommonCodeDto commonCodeDto) {
		CommonCodeDto result = commonDao.dupCommonOrderAndName(commonCodeDto);
		return result;
	}
	
	@Override
	public int deleteCommonCode(CommonCodeDto commonCodeDto) {
		int checksum = commonDao.deleteCommonCode(commonCodeDto);
		return checksum;
	}
}
