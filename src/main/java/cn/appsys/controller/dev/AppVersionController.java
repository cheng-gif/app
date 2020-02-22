package cn.appsys.controller.dev;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.appsys.pojo.AppVersion;
import cn.appsys.result.Result;
import cn.appsys.service.dev.AppInfoService;
import cn.appsys.service.dev.AppVersionService;

@Controller
@RequestMapping("/dev")
public class AppVersionController {
	@Autowired
	private AppVersionService appVersionService;
	@Autowired 
	private AppInfoService appInfoService;
	
	@RequestMapping("/viewVersion")
	@ResponseBody
	public Object viewVersion(@RequestParam(value="id",required=false) Integer appId){
		Map<String, Object> map = new HashMap<String, Object>();
		List<AppVersion> list;
		try {
			list = appVersionService.getAppVersionList(appId);
			map.put("data", Result.ok(list).getData());
			map.put("code", "0");
			map.put("msg", "");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return map;
	}
	
	@RequestMapping("/addVersion")
	@ResponseBody
	public Object add(AppVersion appVersion,MultipartFile a_apkLocPath,HttpSession session){
		if (!a_apkLocPath.isEmpty()) {
			String pathName = "D://test";
			String oldName = a_apkLocPath.getOriginalFilename();
			String newName = oldName.substring(oldName.lastIndexOf("."));
			newName = UUID.randomUUID()+newName;
			File file = new File(pathName,newName);
			try {
				a_apkLocPath.transferTo(file);
				appVersion.setApkFileName(newName);
				appVersion.setCreationDate(new Date());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Integer versionId = null;
		try {
			int count = appVersionService.add(appVersion);
			int result = 0;
			if (count>0) {
				versionId = appVersion.getId();
				result = appInfoService.updateVersionId(versionId, appVersion.getAppId());
			}
			map.put("data", Result.ok(result));
			map.put("msg", Result.error("添加失败!").getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/viewById")
	@ResponseBody
	public Object viewById(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AppVersion appVersion = appVersionService.getAppVersionById(id);
			map.put("data", Result.ok(appVersion).getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/delAPKName")
	@ResponseBody
	public Object delAPK(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int count = appVersionService.deleteApkFile(id);
			map.put("data", Result.ok(count).getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/updateVersion")
	@ResponseBody
	public Object updateVersion(AppVersion appVersion){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			appVersion.setModifyDate(new Date());
			int count = appVersionService.modify(appVersion);
			map.put("data", Result.ok(count).getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/updateFile")
	@ResponseBody
	public Object updateFile(AppVersion appVersion,MultipartFile a_apkName){
		Map<String, Object> map = new HashMap<String, Object>();
		if (!a_apkName.isEmpty()) {
			String path = "D://test";
			String oldName = a_apkName.getOriginalFilename();
			String newName = oldName.substring(oldName.lastIndexOf("."));
			newName = UUID.randomUUID()+newName;
			File file = new File(path,newName);
			try {
				a_apkName.transferTo(file);
				appVersion.setApkFileName(newName);
				map.put("data", Result.ok(appVersionService.modify(appVersion)).getData());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return map;
	}
	
	@RequestMapping("/viewByIdAndInfoId")
	@ResponseBody
	public Object viewById(Integer appId,Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AppVersion appVersion = appVersionService.getViewId(appId, id);
			map.put("data", Result.ok(appVersion).getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
