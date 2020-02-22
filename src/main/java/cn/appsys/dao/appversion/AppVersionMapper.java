package cn.appsys.dao.appversion;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.AppVersion;



public interface AppVersionMapper {
	
	//通过appId获得所有版本信息
	public List<AppVersion> getAppVersionList(@Param("appId")Integer appId) throws Exception;
	
	//通过appId删除版本信息
	public int deleteVersionByAppId(@Param("appId")Integer appId)throws Exception;
	
	//通过appId查看是否存在版本信息
	public int findCountByAppId(@Param("appId") Integer appId) throws Exception;
	
	//新增版本信息
	public int add(AppVersion appVersion)throws Exception;
	
	//通过最新的版本号ID获得版本信息
	public AppVersion getAppVersionById(@Param("id")Integer id)throws Exception;
	
	public int modify(AppVersion appVersion)throws Exception;
	
	public int deleteApkFile(@Param("id")Integer id)throws Exception;
	
	/**
	 * updateSaleStatusByAppId
	 * 修改发布状态
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public int updateSaleStatusByAppId(@Param(value="versionId")Integer id,@Param(value="publishStatus") Integer publishStatus) throws Exception;
	
	public AppVersion getViewById(@Param("appId")Integer appId,@Param("id") Integer id) throws Exception;
}
