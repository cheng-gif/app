package cn.appsys.controller.dev;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.appsys.pojo.DataDictionary;
import cn.appsys.result.Result;
import cn.appsys.service.dev.DataDictionaryService;

@Controller
@RequestMapping("/dev")
public class DataDictionaryController {
	@Resource
	private DataDictionaryService dataDictionaryService;
	
	@RequestMapping("/dictionList")
	@ResponseBody
	public Object getDictionaryList(String typeCode){
		List<DataDictionary> list = dataDictionaryService.getDataDictionaryList(typeCode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", Result.ok(list).getData());
		return map;
	}
}
