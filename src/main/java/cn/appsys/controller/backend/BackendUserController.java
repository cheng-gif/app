package cn.appsys.controller.backend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appsys.pojo.BackendUser;
import cn.appsys.result.Result;
import cn.appsys.service.backend.BackendUserService;

@Controller
@RequestMapping("/backend")
public class BackendUserController {
	@Autowired
	private BackendUserService backendUserService;
	
	
	@RequestMapping("/dologin")
	@ResponseBody
	public Object dologin(String userCode,String userPassword,HttpSession session){
		BackendUser backendUser = backendUserService.login(userCode, userPassword);
		Map<String, Object> map = new HashMap<String, Object>();
		if (backendUser!=null) {
			session.setAttribute("bacUser", backendUser);
			map.put("data", Result.ok(backendUser).getData());
			map.put("status", Result.ok().getStatus());
		}else {
			map.put("msg", Result.error("账号密码不正确！").getMsg());
		}
		return map;
	}
	
	@RequestMapping("/loginout")
	@ResponseBody
	public String exit(HttpSession session){
		session.invalidate();
		return "1";
	}
}
