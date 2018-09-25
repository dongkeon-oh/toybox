package com.dongkeonoh.toybox.serviceImpl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.TestDao;
import com.dongkeonoh.toybox.service.TestService;
import com.dongkeonoh.toybox.vo.CommonVo;

@Service("TestServiceImpl")
public class TestServiceImpl implements TestService{

	@Resource(name="TestDao")
	private TestDao testDao;
	
	@Override
	public List<CommonVo> testList() {
		List<CommonVo> tList = testDao.testList();
		
		return tList;
	}
}
