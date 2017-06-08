package com.mss.web.control;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mss.pojo.DemoPojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="测试Swagger api工具")
@RestController
@RequestMapping(value="/swagger")
public class SwaggerControl extends BaseControl{

	@ApiOperation(value="查询demo详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="demoID", required=false, dataType="Integer"),
		@ApiImplicitParam(name="name", value="demo金额", required=false, dataType="String")
	})
	@ResponseBody
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public DemoPojo get(@RequestParam(required=false) String id, @RequestParam(required=false) String amount) {
		DemoPojo demo = new DemoPojo();
		demo.setId(new BigDecimal(System.currentTimeMillis()).intValue());
		demo.setAmount(new BigDecimal(System.currentTimeMillis()));
		demo.setRemark(System.currentTimeMillis()+"");
		demo.setCreateDate(new Date());
		return demo;
	}
	
}
