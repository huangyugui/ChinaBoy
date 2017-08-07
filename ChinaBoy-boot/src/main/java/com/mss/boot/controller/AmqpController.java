package com.mss.boot.controller;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mss.boot.config.AmqpConfig;
import com.mss.boot.entity.User;
import com.mss.boot.enums.BaseCodeEnum;
import com.mss.boot.vo.BaseRes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="Amqp消息相关api接口")
@Controller
@RequestMapping("/amqp")
public class AmqpController extends BaseController{

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@ApiOperation(value="发送amqp消息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="msg", value="消息", required=true, dataType="String")
	})
	@ResponseBody
	@RequestMapping(value="/send", method=RequestMethod.POST)
	public BaseRes<Object> send(@RequestParam(required=false) String msg){
		
		BaseRes<Object> resInfo = new BaseRes<Object>();
		
		User user = new User();
		user.setName(msg);
		user.setCreateDate(new Date());
		//amqpTemplate.convertAndSend(AmqpConfig.DIRECTQUEUE, user);
		amqpTemplate.convertAndSend(AmqpConfig.DIRECTEXCHANGE, AmqpConfig.DIRECTROUTINGKEY, user);
		
		resInfo.setCode(BaseCodeEnum.CODE_0000.getCode());
		resInfo.setMsg(BaseCodeEnum.CODE_0000.getMsg());
		return resInfo;
	}
	
}
