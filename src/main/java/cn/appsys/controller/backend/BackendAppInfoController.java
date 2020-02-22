package cn.appsys.controller.backend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appsys.pojo.AppInfo;
import cn.appsys.result.Result;
import cn.appsys.service.backend.AppService;
import cn.appsys.service.dev.AppInfoService;
import cn.appsys.tools.Constants;
import cn.appsys.tools.PageSupport;

@Controller
@RequestMapping("/backend")
public class BackendAppInfoController {
	@Autowired
	private AppService appService;
	
	@Autowired 
	private AppInfoService appInfoService;
	
	@RequestMapping("/index")
	@ResponseBody
	public Object getList(String softwareName,String flatformId,
						  String categoryLevel1,String categoryLevel2,String categoryLevel3,
						  String page,Model model,HttpSession session,String limit){
		//获得登录时的开发者ID
		/*Integer devId = ((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId();*/
		/*List<AppInfo> appInfoList = null;
		List<DataDictionary> statusList = null;
		List<DataDictionary> flatFormList = null;
		List<AppCategory> categoryLevel1List = null;//列出一级分类列表，注：二级和三级分类列表通过异步ajax获取
		List<AppCategory> categoryLevel2List = null;
		List<AppCategory> categoryLevel3List = null;*/
		
		//页码大小
		int pageSize = Constants.pageSize;
		//当前页码
		int pageNum = 1;
		if (page!=null && !page.equals("")) {
			pageNum = Integer.valueOf(page);
		}
		
		Integer queryCategoryLevel1 = null;
		if(categoryLevel1 != null && !categoryLevel1.equals("")){
			queryCategoryLevel1 = Integer.parseInt(categoryLevel1);
		}
		Integer queryCategoryLevel2 = null;
		if(categoryLevel2 != null && !categoryLevel2.equals("")){
			queryCategoryLevel2 = Integer.parseInt(categoryLevel2);
		}
		Integer queryCategoryLevel3 = null;
		if(categoryLevel3 != null && !categoryLevel3.equals("")){
			queryCategoryLevel3 = Integer.parseInt(categoryLevel3);
		}
		Integer queryFlatformId = null;
		if(flatformId != null && !flatformId.equals("")){
			queryFlatformId = Integer.parseInt(flatformId);
		}
		PageSupport<AppInfo> pages = appService.getList(softwareName, 1, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, 1, (pageNum-1)*pageSize, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", pages.getList());
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pages.getTotalCount());
		return map;
	}
	
	/**
	 * 审核通过、不通过
	 * @param status
	 * @param appId
	 * @return
	 */
	@RequestMapping("/backStatus")
	@ResponseBody
	public Object updateStatusById(Integer status,Integer appId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int count = appInfoService.updateSatus(status, appId);
			map.put("data", Result.ok(count).getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
