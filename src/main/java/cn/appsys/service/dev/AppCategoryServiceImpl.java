package cn.appsys.service.dev;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.appcategory.AppCategoryMapper;
import cn.appsys.pojo.AppCategory;

@Service("appCategoryService")
public class AppCategoryServiceImpl implements AppCategoryService{
	@Autowired
	private AppCategoryMapper appCategoryMapper;
	
	@Override
	public List<AppCategory> getAppCategories(Integer parentId) {
		return appCategoryMapper.getAppCategoryListByParentId(parentId);
	}
	
}
