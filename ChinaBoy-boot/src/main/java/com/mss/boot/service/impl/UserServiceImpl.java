package com.mss.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mss.boot.entity.User;
import com.mss.boot.mapper.UserMapper;
import com.mss.boot.service.UserService;

@Transactional(rollbackFor=Exception.class)/**类所有方法事务*/
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	

	@Transactional(rollbackFor=Exception.class)/**方法事务*/
	@Override
	public void add(User user) {
		userMapper.insert(user);
	}

	@Override
	public void modify(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void remove(User user) {
		userMapper.delete(user);
	}

}
