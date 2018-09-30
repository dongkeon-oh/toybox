package com.dongkeonoh.toybox.service;

import java.util.List;

import com.dongkeonoh.toybox.vo.ItemVo;

public interface ItemService {
	// 아이템 추가
	public int addItem(ItemVo itemVo);
	// 아이템 리스트
	public List<ItemVo> listItem(ItemVo itemVo);
	// 아이템 보기
	public ItemVo viewItem(ItemVo itemVo);
	// 아이템 수정/삭제 
	// 아이템 useYn, itemCondition 컬럼이 필요 할 수 있음
	public ItemVo modifyItem(ItemVo itemVo);
}
