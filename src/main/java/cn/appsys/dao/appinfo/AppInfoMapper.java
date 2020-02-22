package cn.appsys.dao.appinfo;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.AppInfo;

public interface AppInfoMapper {
	
	//获得APP信息列表
	public List<AppInfo> getList(@Param("softwareName") String softwareName,
								 @Param("status") Integer status,
								 @Param("flatformId") Integer flatformId,
								 @Param("categoryLevel1") Integer categoryLevel1,
								 @Param("categoryLevel2") Integer categoryLevel2,
								 @Param("categoryLevel3") Integer categoryLevel3,
								 @Param("devId") Integer devId,
								 @Param("from") Integer pageNum,
								 @Param("pageSize") Integer pageSize);
	
	//获得App信息总数
	public int getTotal(@Param("softwareName") String softwareName,
			 			@Param("status") Integer status,
			 			@Param("flatformId") Integer flatformId,
			 			@Param("categoryLevel1") Integer categoryLevel1,
			 			@Param("categoryLevel2") Integer categoryLevel2,
			 			@Param("categoryLevel3") Integer categoryLevel3,
			 			@Param("devId") Integer devId);
		
	//添加
	public int addAppInfo(AppInfo appInfo);
	
	//通过ID查看APP信息
	public AppInfo getAppInfo(@Param(value="id")Integer id,@Param(value="APKName")String APKName)throws Exception;
	
	//通过id删除app信息
	public int deleteById(@Param("id") Integer id) throws Exception;
	
	//修改
	public int modify(AppInfo appInfo);
	
	//修改LOGO
	public int deleteAppLogo(@Param(value="id")Integer id)throws Exception;
	
	public int updateVersionId(@Param(value="versionId")Integer versionId,@Param(value="id")Integer appId)throws Exception;
	
	/*
	 * 更新app状态
	 * @param status
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateSatus(@Param(value="status")Integer status,@Param(value="id")Integer id)throws Exception;

	
}
