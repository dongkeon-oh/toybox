package com.dongkeonoh.toybox.service;

import java.util.HashMap;
import java.util.List;

import com.dongkeonoh.toybox.vo.ItemVo;
import com.dongkeonoh.toybox.vo.UserVo;

public interface ItemService {
	// 아이템 리스트
	public List<ItemVo> listItem(HashMap<String, String> search);
	// 아이템 대여전 리스트
	public List<ItemVo> listItemBeforeApply(List<String> rent_item_list);
	// 아이템 대여전 리스트
	public List<UserVo> listItemReturnPoint();
	// 아이템 대여 직전 상태 리스트
	public List<ItemVo> listItemBeforeRent(HashMap<String, Object> search);
	// 아이템 대여 신청
	public int rentItem(List<ItemVo> rent_item_list);
	

	// 요청 리스트
	public List<ItemVo> requestItem(HashMap<String, String> search);
	
	// 요청 내역 확인
	public List<ItemVo> responseItem(String user_id);
}
