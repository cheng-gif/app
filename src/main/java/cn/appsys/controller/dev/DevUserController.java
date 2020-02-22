package cn.appsys.controller.dev;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.appsys.pojo.DevUser;
import cn.appsys.result.Result;
import cn.appsys.service.dev.DevUserService;
import cn.appsys.tools.Constants;

@Controller
@RequestMapping("/dev")
public class DevUserController {
	@Autowired
	private DevUserService devUserService;
	
	
	@RequestMapping(value="/dologin")
	@ResponseBody
	public Object dologin(String devCode,String devPassword,HttpSession session){
		DevUser devUser = devUserService.login(devCode, devPassword);
		Map<String, Object> map = new HashMap<String, Object>();
		if(devUser != null){
			session.setAttribute(Constants.DEV_USER_SESSION, devUser);
			map.put("data", Result.ok(devUser).getData());
			map.put("status", Result.ok().getStatus());
		}else{
			map.put("msg", Result.error("登录失败！").getMsg());
		}
		return map;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpSession session){
		session.invalidate();
		return "1";
	}
	
}
