package com.dongkeonoh.toybox.service;

import java.util.HashMap;
import java.util.List;

import com.dongkeonoh.toybox.dto.ItemDto;
import com.dongkeonoh.toybox.vo.ItemVo;

public interface ItemService {
	// 아이템 리스트
	public List<ItemDto> listItem(HashMap<String, String> search);
	
	// 아이템 대여전 리스트
	public HashMap<String, Object> listItemBeforeApply(List<String> rent_item_list);

	// 아이템 대여 신청
	public int rentItem(List<ItemDto> rent_item_list);
	
	// 요청 리스트
	public List<ItemDto> requestItem(HashMap<String, String> search);
	
	// 요청 내역 확인
	public List<ItemVo> responseItem(String user_id);
}
