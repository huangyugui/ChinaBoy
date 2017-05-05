package com.test.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.test.entity.Demo;

@Mapper
public interface DemoMapper extends MyMapper<Demo> {

}
