package com.mss.boot.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mss.boot.entity.User;
import com.mss.boot.enums.BaseCodeEnum;
import com.mss.boot.mapper.UserMapper;
import com.mss.boot.vo.BaseRes;
import com.mss.boot.vo.PageData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

@Api(tags="用户相关api接口")
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	    
	@Autowired
	private UserMapper userMapper;
	
	@ApiOperation(value="分页查询用户列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="ID", required=false, dataType="Long"),
		@ApiImplicitParam(name="name", value="登录名称", required=false, dataType="String"),
		@ApiImplicitParam(name="page", value="页码", required=false, dataType="String"),
		@ApiImplicitParam(name="rows", value="每页展示条目数", required=false, dataType="String")
	})
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public BaseRes<PageData<User>> list(@RequestParam(required=false) String id,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String page,
			@RequestParam(required=false) String rows){

		BaseRes<PageData<User>> resInfo = new BaseRes<PageData<User>>();
		
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		if(StringUtil.isNotEmpty(id)){
			criteria.andEqualTo("id", id);
		}
		if(StringUtil.isNotEmpty(name)){
			criteria.andLike("name", "%"+name+"%");
		}
		
		getPageRows(page,rows);
		
		List<User> list = userMapper.selectByExampleAndRowBounds(example, new RowBounds(curPage, curRows));
		PageData<User> pageInfo = new PageData<User>();
		pageInfo.setTotal(userMapper.selectCountByExample(example));
		pageInfo.setRows(list);

		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		resInfo.setBody(pageInfo);
		return resInfo;
	}
	
	@ApiOperation(value="查询所有用户列表")
	@ResponseBody
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public BaseRes<List<User>> listAll(){
		BaseRes<List<User>> resInfo = new BaseRes<List<User>>();
		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		resInfo.setBody(userMapper.selectAll());
		return resInfo;
	}
	
	@ApiOperation(value="添加用户")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name", value="登录名称", required=true, dataType="String"),
		@ApiImplicitParam(name="password", value="登录密码", required=true, dataType="String")
	})
	@Transactional(rollbackFor=Exception.class)
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public BaseRes<Object> add(@RequestParam(required=false) String name,
			@RequestParam(required=false) String password) throws Exception{
		
		BaseRes<Object> resInfo = new BaseRes<Object>();
		
		if(StringUtil.isEmpty(name)){
			resInfo.setCode(BaseCodeEnum.CODE_0002.getCode());
			resInfo.setMsg(BaseCodeEnum.CODE_0002.getMsg());
			return resInfo;
		}
		
		if(StringUtil.isEmpty(password)){
			resInfo.setCode(BaseCodeEnum.CODE_0003.getCode());
			resInfo.setMsg(BaseCodeEnum.CODE_0003.getMsg());
			return resInfo;
		}
		
		Date date = new Date();
		User user = new User();
		user.setName(name);
		user.setPassword(DigestUtils.md5Hex(password));
		user.setLastLoginDate(date);
		user.setCreateDate(date);
		user.setUpdateDate(date);
		userMapper.insert(user);
		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		return resInfo;
	}
	
	@ApiOperation(value="修改用户")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="唯一主键", required=true, dataType="Long"),
		@ApiImplicitParam(name="password", value="登录密码", required=true, dataType="String")
	})
	@ResponseBody
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public BaseRes<Object> modify(@RequestParam(required=false) Long id,
			@RequestParam(required=false) String password){
		
		BaseRes<Object> resInfo = new BaseRes<Object>();
		
		if(id == null){
			resInfo.setCode(BaseCodeEnum.CODE_0001.getCode());
			resInfo.setMsg(BaseCodeEnum.CODE_0001.getMsg());
			return resInfo;
		}
		
		if(StringUtil.isEmpty(password)){
			resInfo.setCode(BaseCodeEnum.CODE_0003.getCode());
			resInfo.setMsg(BaseCodeEnum.CODE_0003.getMsg());
			return resInfo;
		}
		
		Date date = new Date();
		User user = new User();
		user.setId(id);
		user.setPassword(DigestUtils.md5Hex(password));
		user.setUpdateDate(date);
		userMapper.updateByPrimaryKeySelective(user);
		
		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		return resInfo;
	}
	
	@ApiOperation(value="查询用户详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="唯一主键", required=true, dataType="Long"),
		})
	@ResponseBody
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public BaseRes<User> get(@RequestParam Long id){
		
		BaseRes<User> resInfo = new BaseRes<User>();
		
		User user = new User();
		user.setId(id);
		user = userMapper.selectOne(user);

		resInfo.setBody(user);
		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		return resInfo;
	}
	
	@ApiOperation(value="删除用户")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="唯一主键", required=true, dataType="Long")
	})
	@ResponseBody
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public BaseRes<Object> remove(@RequestParam Long id){
		
		BaseRes<Object> resInfo = new BaseRes<Object>();
		
		if(id == null){
			resInfo.setCode(BaseCodeEnum.CODE_0001.getCode());
			resInfo.setMsg(BaseCodeEnum.CODE_0001.getMsg());
			return resInfo;
		}
		
		User user = new User();
		user.setId(id);
		userMapper.delete(user);

		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		return resInfo;
	}
	
	
}
