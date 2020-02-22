package cn.appsys.service.dev;


import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.AppInfo;
import cn.appsys.tools.PageSupport;


public interface AppInfoService {
	
	public PageSupport<AppInfo> getList(String softwareName,Integer status,Integer flatformId,
										Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3,
										Integer devId,Integer pageNum,Integer pageSize);
	
	public int addInfo(AppInfo appInfo);
	
	/**
	 * 根据id、apkName查找appInfo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AppInfo getAppInfo(Integer id,String APKName)throws Exception;
	
	public int deleteById(Integer id) throws Exception;
	
	public int modify(AppInfo appInfo);
	
	//修改LOGO
	public int deleteAppLogo(@Param(value="id")Integer id)throws Exception;
	
	public int updateVersionId(@Param(value="versionId")Integer versionId,@Param(value="id")Integer appId)throws Exception;

	public int updateSatus(@Param(value="status")Integer status,@Param(value="id")Integer id) throws Exception;

}
