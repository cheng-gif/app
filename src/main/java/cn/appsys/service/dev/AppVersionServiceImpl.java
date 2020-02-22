package cn.appsys.service.dev;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.appversion.AppVersionMapper;
import cn.appsys.pojo.AppVersion;

@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService{
	@Autowired
	private AppVersionMapper appVersionMapper;
	
	@Override
	public List<AppVersion> getAppVersionList(Integer appId) throws Exception {
		return appVersionMapper.getAppVersionList(appId);
	}

	@Override
	@Transactional
	public int deleteVersionByAppId(Integer appId) throws Exception {
		int count = appVersionMapper.deleteVersionByAppId(appId);
		if (count>0) {
			return count; 
		}else{
			throw new RuntimeException();
		}
	}

	@Override
	public int findCountByAppId(Integer appId) throws Exception {
		int count = appVersionMapper.findCountByAppId(appId);
		return count;
	}

	@Override
	public int add(AppVersion appVersion) throws Exception {
		return appVersionMapper.add(appVersion);
	}

	@Override
	public AppVersion getAppVersionById(Integer id) throws Exception {
		return appVersionMapper.getAppVersionById(id);
	}

	@Override
	public int modify(AppVersion appVersion) throws Exception {
		return appVersionMapper.modify(appVersion);
	}

	@Override
	public int deleteApkFile(Integer id) throws Exception {
		return appVersionMapper.deleteApkFile(id);
	}

	@Override
	public int updateSaleStatusByAppId(Integer versionId,Integer publishStatus) throws Exception {
		return appVersionMapper.updateSaleStatusByAppId(versionId,publishStatus);
	}

	@Override
	public AppVersion getViewId(Integer appId, Integer id) throws Exception {
		return appVersionMapper.getViewById(appId, id);
	}

}
