package cn.appsys.controller.dev;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import cn.appsys.pojo.AppInfo;
import cn.appsys.result.Result;
import cn.appsys.service.dev.AppInfoService;
import cn.appsys.service.dev.AppVersionService;
import cn.appsys.tools.Constants;
import cn.appsys.tools.PageSupport;

@Controller
@RequestMapping("/dev")
public class AppInfoController {
	@Resource
	private AppInfoService appInfoService;
	@Resource
	private AppVersionService appVersionService;
	
	@RequestMapping("/index")
	@ResponseBody
	public Object getList(String softwareName,String status,String flatformId,
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
		
		Integer queryStatus = null;
		if(status != null && !status.equals("")){
			queryStatus = Integer.parseInt(status);
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
		PageSupport<AppInfo> pages = appInfoService.getList(softwareName, queryStatus, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, 1, (pageNum-1)*pageSize, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", pages.getList());
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pages.getTotalCount());
		return map;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object addInfo(AppInfo appInfo,@RequestParam(value="logoPicPath1",required=false)MultipartFile a_path,HttpServletRequest request,HttpSession session){
		System.out.println(appInfo.getAPKName());
		Map<String, Object> map = new HashMap<String, Object>();
		if (!a_path.isEmpty()) {
			String path = "D://test";
			String oldName = a_path.getOriginalFilename();
			String newName = oldName.substring(oldName.lastIndexOf("."));
			newName = UUID.randomUUID()+newName;
			File file = new File(path,newName);
			try {
				a_path.transferTo(file);
				appInfo.setLogoPicPath(newName);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			appInfo.setCreatedBy(appInfo.getDevId());
			appInfo.setCreationDate(new Date());
			int count = appInfoService.addInfo(appInfo);
//			map.put("code", "0");
//			map.put("msg", "");
			map.put("data", Result.ok(count));
		}
		return map;
	}
	
	@RequestMapping("/view")
	@ResponseBody
	public Object viewAppInfo(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AppInfo appInfo = appInfoService.getAppInfo(id, null);
			map.put("data", Result.ok(appInfo).getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Object del(Integer appId){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = 0;
		try {
			int count = appVersionService.findCountByAppId(appId);
			if (count>0) {
				int result1 = appVersionService.deleteVersionByAppId(appId);
				int result2 = appInfoService.deleteById(appId);
				if (result1>0 && result2>0) {
					result = 1;
				}else{
					result = -1;
				}
			}else {
				int result1 = appInfoService.deleteById(appId); 
				if (result1>0) {
					result = 1;
				}else {
					result = -1;
				}
			}
			map.put("data", Result.ok(result).getData());
			map.put("msg", Result.error("删除失败!").getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object updateInfo(AppInfo appInfo){
		
		Map<String, Object> map = new HashMap<String, Object>();
		appInfo.setModifyDate(new Date());
		int count = appInfoService.modify(appInfo);
		if (count>0) {
			map.put("data", Result.ok(count).getData());
		}else {
			map.put("msg", Result.error("****修改失败！").getMsg());
		}
		return map;
	}
	
	@RequestMapping("/updateById")
	@ResponseBody
	public Object updateById(AppInfo appInfo,MultipartFile logoPicPath1){
		Map<String, Object> map = new HashMap<String, Object>();
		if (!logoPicPath1.isEmpty()) {
			String path = "D://test";
			String oldName = logoPicPath1.getOriginalFilename();
			String newName = oldName.substring(oldName.lastIndexOf("."));
			newName = UUID.randomUUID()+newName;
			File file = new File(path,newName);
			try {
				logoPicPath1.transferTo(file);
				appInfo.setLogoPicPath(newName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			map.put("data", Result.ok(appInfoService.modify(appInfo)).getData());
		}
		return map;
	}
	
	@RequestMapping("/updateLogo")
	@ResponseBody
	public Object updateLogo(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int count = appInfoService.deleteAppLogo(id);
			if (count>0) {
				map.put("data", Result.ok(count).getData());
			}else {
				map.put("msg", Result.error("修改失败！").getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 上下架
	 * @param status
	 * @param id
	 * @param versionId
	 * @return
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public Object updateStatus(Integer status,Integer id,Integer versionId){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = 0;
		if(status==2 || status==5){
			try {
				int count1 = appInfoService.updateSatus(4, id);
				int count2 = appVersionService.updateSaleStatusByAppId(versionId,2);
				if (count1>0 && count2>0) {
					result = 1;
				}else{
					result = -1;
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(status==4){
			try {
				int count1 = appInfoService.updateSatus(5, id);
				int count2 = appVersionService.updateSaleStatusByAppId(versionId, 1);
				if (count1>0 && count2>0) {
					result = 1;
				}else{
					result = -1;
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("data", Result.ok(result).getData());
		return map;
	}
}
