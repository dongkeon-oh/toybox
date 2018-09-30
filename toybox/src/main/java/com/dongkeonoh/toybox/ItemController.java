package com.dongkeonoh.toybox;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dongkeonoh.toybox.service.ItemService;
import com.dongkeonoh.toybox.vo.ItemVo;

@Controller
public class ItemController {

	@Resource(name="ItemServiceImpl")
	private ItemService itemService;

	// 아이템 추가 get
	@RequestMapping(value = "/add_item", method = RequestMethod.GET)
	public ModelAndView get_addItem(HttpServletRequest httpServletRequest) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("item/add_item_get");
		
		return modelAndView;
	}

	// 아이템 추가 post
	@RequestMapping(value = "/add_item", method = RequestMethod.POST)
	public ModelAndView post_addItem(HttpServletRequest httpServletRequest, Model model, ItemVo itemVo) {
		
		int checksum = itemService.addItem(itemVo);
		if(checksum > 0) {
			//성공
		}else {
			//실패
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/add_item_post");
		modelAndView.addObject("checksum", checksum);
		
		return modelAndView;
	}	
}
