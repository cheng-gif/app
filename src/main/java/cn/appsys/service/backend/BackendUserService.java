package cn.appsys.service.backend;

import cn.appsys.pojo.BackendUser;

public interface BackendUserService {
	
	public BackendUser login(String userCode,String userPassword);
}
