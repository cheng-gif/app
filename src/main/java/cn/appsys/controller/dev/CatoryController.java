package cn.appsys.controller.dev;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appsys.dao.appcategory.AppCategoryMapper;
import cn.appsys.pojo.AppCategory;
import cn.appsys.result.Result;

@Controller()
@RequestMapping("/dev")
public class CatoryController {
	@Resource
	private AppCategoryMapper appCategoryMapper;
	
	@RequestMapping("/catoryList")
	@ResponseBody
	public Object getCatoryList(Integer parentId){
		Map<String, Object> map = new HashMap<String, Object>();
		List<AppCategory> list = appCategoryMapper.getAppCategoryListByParentId(parentId);
		map.put("data", Result.ok(list).getData());
		map.put("status", Result.ok().getStatus());
		return map;
	}
}
