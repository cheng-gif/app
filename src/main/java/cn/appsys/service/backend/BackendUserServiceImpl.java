package cn.appsys.service.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.backenduser.BackendUserMapper;
import cn.appsys.pojo.BackendUser;

@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService{
	@Autowired
	private BackendUserMapper backendUserMapper;
	
	@Override
	public BackendUser login(String userCode, String userPassword) {
		return backendUserMapper.login(userCode, userPassword);
	}

}
