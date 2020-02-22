package cn.appsys.service.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.devuser.DevUserMapper;
import cn.appsys.pojo.DevUser;

@Service("devUserService")
public class DevUserServiceImpl implements DevUserService{
	@Autowired
	private DevUserMapper devUserMapper;
	
	@Override
	public DevUser login(String devCode, String devPassword) {
		return devUserMapper.login(devCode, devPassword);
	}

}
