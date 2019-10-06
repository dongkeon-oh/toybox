package com.dongkeonoh.toybox.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.ItemDao;
import com.dongkeonoh.toybox.service.ItemService;
import com.dongkeonoh.toybox.vo.ItemVo;
import com.dongkeonoh.toybox.vo.UserVo;

@Service("ItemServiceImpl")
public class ItemServiceImpl implements ItemService{


	@Resource(name="ItemDao")
	private ItemDao itemDao;

	@Override
	public List<ItemVo> listItem(HashMap<String, String> search) {
		List<ItemVo> result = itemDao.listItem(search);
		return result;
	}
	
	@Override
	public List<ItemVo> listItemBeforeApply(List<String> rent_item_list){
		List<ItemVo> result = itemDao.listItemBeforeApply(rent_item_list);
		return result;		
	}

	@Override
	public List<UserVo> listItemReturnPoint(){
		List<UserVo> result = itemDao.listItemReturnPoint();
		return result;
	}
	
	@Override
	public List<ItemVo> listItemBeforeRent(HashMap<String, Object> search){
		List<ItemVo> result = itemDao.listItemBeforeRent(search);
		return result;		
	}
	
	@Override
	public int rentItem(List<ItemVo> rent_item_list) {
		int result = itemDao.rentItem(rent_item_list);
		return result;
	}

	@Override
	public List<ItemVo> requestItem(HashMap<String, String> search) {
		List<ItemVo> result = itemDao.requestItem(search);
		return result;
	}

	// 요청 내역 확인
	@Override
	public List<ItemVo> responseItem(String user_id){
		List<ItemVo> result = itemDao.responseItem(user_id);
		return result;		
	}
	
	public int putItem(ItemVo item) {
		int result = itemDao.putItem(item);
		return result;
	}
}
