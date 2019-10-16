package com.dongkeonoh.toybox.service;

import java.util.HashMap;
import java.util.List;

import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.dto.ItemDto;
import com.dongkeonoh.toybox.dto.UserDto;

public interface ItemAdminService {
	
	// 공통코드 조회
	public List<CommonCodeDto> getCommonCodeSearch(String search_type, CommonCodeDto commonCodeDto);

	// 소유자 조회
	public List<UserDto> getUserSearch(String search_type, String usr_name);
	
	// 상위아이템 조회
	public List<ItemDto> getItemSearch(String search_type, String itm_name);

	// 아이템 추가
	public int putItem(ItemDto item);
	
	// 아이템 조회
	public List<ItemDto> listItem(HashMap<String, String> map);
	
	// 아이템 상세 조회
	public ItemDto detailItem(String itm_id);
	
	// 아이템 수정
	public int updateItem(ItemDto item);
}
