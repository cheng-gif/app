package cn.appsys.service.dev;

import java.util.List;

import cn.appsys.pojo.AppCategory;

public interface AppCategoryService {
	
	public List<AppCategory> getAppCategories(Integer parentId);
}
