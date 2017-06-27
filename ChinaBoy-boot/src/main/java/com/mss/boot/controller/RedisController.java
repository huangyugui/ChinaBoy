package com.mss.boot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mss.boot.entity.User;
import com.mss.boot.enums.BaseCodeEnum;
import com.mss.boot.pojo.ResInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="Redis相关api接口")
@Controller
@RequestMapping("/redis")
public class RedisController extends BaseController{

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@ApiOperation(value="查询redis缓存所有数据")
	@ResponseBody
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public ResInfo<List<String>> listAll(){
		ResInfo<List<String>> resInfo = new ResInfo<List<String>>();
		List<String> list = new ArrayList<String>();

		Set<String> set = redisTemplate.keys("*");
		if(set!=null&&!set.isEmpty()){
			for(String str :set){
				list.add(str+"|"+redisTemplate.opsForValue().get(str));
			}
		}
		
		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		resInfo.setBody(list);
		return resInfo;
	}
	
	@ApiOperation(value="添加redis缓存数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name="key", value="缓存key值", required=true, dataType="String"),
		@ApiImplicitParam(name="value", value="缓存value值", required=true, dataType="String")
	})
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResInfo<List<User>> add(@RequestParam(required=false) String key,
			@RequestParam(required=false) String value){
		ResInfo<List<User>> resInfo = new ResInfo<List<User>>();
		
		redisTemplate.opsForValue().set(key, value, 30, TimeUnit.MINUTES);
		
		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		return resInfo;
	}
	
	@ApiOperation(value="查询redis缓存数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name="key", value="缓存key值", required=true, dataType="String")
	})
	@ResponseBody
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public ResInfo<String> get(@RequestParam(required=false) String key){
		ResInfo<String> resInfo = new ResInfo<String>();
		
		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		resInfo.setBody(redisTemplate.opsForValue().get(key));
		return resInfo;
	}
	
	@ApiOperation(value="修改redis缓存数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name="key", value="缓存key值", required=true, dataType="String"),
		@ApiImplicitParam(name="value", value="缓存value值", required=true, dataType="String")
	})
	@ResponseBody
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public ResInfo<List<User>> modify(@RequestParam(required=false) String key,
			@RequestParam(required=false) String value){
		ResInfo<List<User>> resInfo = new ResInfo<List<User>>();
		
		redisTemplate.opsForValue().set(key, value, 30, TimeUnit.MINUTES);
		
		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		return resInfo;
	}
	
	@ApiOperation(value="删除redis缓存数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name="key", value="缓存key值", required=true, dataType="String")
	})
	@ResponseBody
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public ResInfo<List<User>> del(@RequestParam(required=false) String key){
		ResInfo<List<User>> resInfo = new ResInfo<List<User>>();
		
		redisTemplate.delete(key);
		
		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		return resInfo;
	}
	
	
}
