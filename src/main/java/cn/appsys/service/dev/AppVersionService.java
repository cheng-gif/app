package cn.appsys.service.dev;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.AppVersion;

public interface AppVersionService {
	
	public List<AppVersion> getAppVersionList(@Param("appId")Integer appId) throws Exception;

	public int deleteVersionByAppId(@Param("appId")Integer appId)throws Exception;
	
	public int findCountByAppId(@Param("appId") Integer appId) throws Exception;
	
	public int add(AppVersion appVersion)throws Exception;
	
	/**
	 * 根据id获取AppVersion
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AppVersion getAppVersionById(Integer id)throws Exception;
	
	/**
	 * 修改app版本信息
	 * @param appVersion
	 * @return
	 * @throws Exception
	 */
	public int modify(AppVersion appVersion)throws Exception;
	
	/**
	 * 删除apk文件
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteApkFile(Integer id)throws Exception;
	
	public int updateSaleStatusByAppId(@Param(value="versionId")Integer id,Integer publishStatus) throws Exception;

	public AppVersion getViewId(Integer appId,Integer id) throws Exception;
}
