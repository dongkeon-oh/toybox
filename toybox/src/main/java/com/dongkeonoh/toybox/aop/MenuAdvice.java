package com.dongkeonoh.toybox.aop;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dongkeonoh.toybox.ToyboxGlobalNameSpace;
import com.dongkeonoh.toybox.service.UtilityService;
import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.dto.UserDto;

@Aspect
public class MenuAdvice implements ToyboxGlobalNameSpace{

	@Resource(name="UtilityServiceImpl")
	private UtilityService utilityService;

	@Before("execution(* com.dongkeonoh.toybox.*Controller.*(..)) && args(modelAndView,..)")
	public void aopMenuList(JoinPoint joinPoint, ModelAndView modelAndView){
		try {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			UserDto sessionUser = (UserDto)request.getSession().getAttribute(USER);
			
			Object[] object = joinPoint.getArgs();
			for(Object search : object) {
				if(search instanceof ModelAndView) {
					UserDto userDto = new UserDto();
					userDto.setUsr_id(sessionUser.getUsr_id());
					List<CommonCodeDto> result = utilityService.getMenu(userDto);
					((ModelAndView) search).addObject("menu_list",result);
				}
			}
        	
		}catch (Exception e) {
			System.out.println(e.getMessage());// error page redirection
		}
	}
}
