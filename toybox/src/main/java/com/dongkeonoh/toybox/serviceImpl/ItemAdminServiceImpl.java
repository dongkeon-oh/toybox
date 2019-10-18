package com.dongkeonoh.toybox.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.ConditionDao;
import com.dongkeonoh.toybox.dao.ItemAdminDao;
import com.dongkeonoh.toybox.dao.UtilityDao;
import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.dto.ItemDto;
import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.service.ItemAdminService;

@Service("ItemAdminServiceImpl")
public class ItemAdminServiceImpl implements ItemAdminService{

	@Resource(name="ItemAdminDao")
	private ItemAdminDao itemAdminDao;

	@Resource(name="UtilityDao")
	private UtilityDao utilityDao;

	@Resource(name="ConditionDao")
	private ConditionDao conditionDao;
	
	// 공통코드 조회
	@Override
	public List<CommonCodeDto> getCommonCodeSearch(String search_type, CommonCodeDto commonCodeDto){
		HashMap<String , Object> param = new HashMap<String, Object>();
		param.put("search_type", search_type);
		param.put("ccd", commonCodeDto);
		
		List<CommonCodeDto> result = utilityDao.getCommonCodeSearch(param);
		return result;
	}

	// 소유자 조회
	@Override
	public List<UserDto> getUserSearch(String search_type, String usr_name){
		HashMap<String , String> param = new HashMap<String, String>();
		param.put("search_type", search_type);
		param.put("usr_name", usr_name);
		
		List<UserDto> result = utilityDao.getUserSearch(param);
		return result;
	}
	
	// 상위아이템 조회
	@Override
	public List<ItemDto> getItemSearch(String search_type, String itm_name){
		HashMap<String , String> param = new HashMap<String, String>();
		param.put("search_type", search_type);
		param.put("itm_name", itm_name);
		
		List<ItemDto> result = utilityDao.getItemSearch(param);
		return result;
	}
	
	// 아이템 추가
	@Override
	public int putItem(ItemDto item) {
		int itemResult = itemAdminDao.putItem(item);
		int conditionResult = conditionDao.putCondition(item);	// 아이템 상태까지 저장되어야 성공 (임시)
		return itemResult + conditionResult;
	}

	// 아이템 조회
	public List<ItemDto> listItem(HashMap<String, String> map){
		List<ItemDto> result = itemAdminDao.listItem(map);
		return result;
	}
	
	// 아이템 상세 조회
	public ItemDto detailItem(String itm_id) {
		ItemDto result = itemAdminDao.detailItem(itm_id);
		return result;
	}	

	// 아이템 수정
	@Override
	public int updateItem(ItemDto item) {
		int result = itemAdminDao.updateItem(item);
		return result;
	}
}
