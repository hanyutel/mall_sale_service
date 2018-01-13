package cn.hanyuweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hanyuweb.mapper.UserLogMapper;

@Service
public class UserServiceImp implements UserServiceInf{

	@Autowired
	UserLogMapper userLogMapper;
	
	@Override
	public void save_log(String text) {
		userLogMapper.insert_user_log(text);
	}

}
