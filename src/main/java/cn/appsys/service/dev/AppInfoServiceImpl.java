package cn.appsys.service.dev;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.pojo.AppInfo;
import cn.appsys.tools.PageSupport;

@Service("appInfoService")
@Transactional
public class AppInfoServiceImpl implements AppInfoService{
	@Autowired
	private AppInfoMapper appInfoMapper;
	
	@Override
	@Transactional(readOnly=true)
	public PageSupport<AppInfo> getList(String softwareName, Integer status,
			Integer flatformId, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, Integer devId, Integer pageNum,
			Integer pageSize) {
		int totalCount = appInfoMapper.getTotal(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, devId);
		List<AppInfo> list = appInfoMapper.getList(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, devId, pageNum, pageSize);
		PageSupport<AppInfo> page = new PageSupport<AppInfo>(pageNum, totalCount, pageSize, list);
		return page;
	}

	@Override
	public int addInfo(AppInfo appInfo) {
		return appInfoMapper.addAppInfo(appInfo);
	}

	@Override
	public AppInfo getAppInfo(Integer id, String APKName) throws Exception {
		return appInfoMapper.getAppInfo(id, APKName);
	}

	@Override
	@Transactional
	public int deleteById(Integer id) throws Exception {
		int count = appInfoMapper.deleteById(id);
		if (count>0) {
			return count;
		}else {
			throw new RuntimeException();
		}
	}

	@Override
	public int modify(AppInfo appInfo) {
		return appInfoMapper.modify(appInfo);
	}

	@Override
	public int deleteAppLogo(Integer id) throws Exception {
		return appInfoMapper.deleteAppLogo(id);
	}

	@Override
	public int updateVersionId(Integer versionId, Integer appId)
			throws Exception {
		return appInfoMapper.updateVersionId(versionId, appId);
	}

	@Override
	public int updateSatus(Integer status, Integer id) throws Exception {
		return appInfoMapper.updateSatus(status, id);
	}


}
