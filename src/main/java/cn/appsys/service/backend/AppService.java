package cn.appsys.service.backend;

import cn.appsys.pojo.AppInfo;
import cn.appsys.tools.PageSupport;

public interface AppService {
	/**
	 * 根据条件查询出待审核的APP列表并分页显示
	 * @param querySoftwareName
	 * @param queryCategoryLevel1
	 * @param queryCategoryLevel2
	 * @param queryCategoryLevel3
	 * @param queryFlatformId
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageSupport<AppInfo> getList(String softwareName,Integer status,Integer flatformId,
										Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3,
										Integer devId,Integer pageNum,Integer pageSize);
}
