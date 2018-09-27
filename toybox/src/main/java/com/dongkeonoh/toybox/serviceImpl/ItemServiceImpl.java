package com.dongkeonoh.toybox.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.ItemDao;
import com.dongkeonoh.toybox.service.ItemService;
import com.dongkeonoh.toybox.vo.ItemVo;

@Service("ItemServiceImpl")
public class ItemServiceImpl implements ItemService{


	@Resource(name="ItemDao")
	private ItemDao itemDao;
	
	@Override
	public int addItem(ItemVo itemVo) {
		int checksum = itemDao.addItem(itemVo);
		return checksum;
	}
}
