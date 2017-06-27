package com.mss.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mss.boot.entity.User;

@Mapper
public interface UserMapper extends MyMapper<User> {

}
