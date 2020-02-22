package cn.appsys.service.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.pojo.AppInfo;
import cn.appsys.tools.PageSupport;

@Service
public class AppServiceImpl implements AppService{
	@Autowired
	private AppInfoMapper appInfoMapper;

	@Override
	public PageSupport<AppInfo> getList(String softwareName, Integer status,
			Integer flatformId, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, Integer devId, Integer pageNum,
			Integer pageSize) {
		int totalCount = appInfoMapper.getTotal(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, devId);
		List<AppInfo> list = appInfoMapper.getList(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, devId, pageNum, pageSize);
		PageSupport<AppInfo> page = new PageSupport<AppInfo>(pageNum, totalCount, pageSize, list);
		return page;
	}
	
	
}
