package com.mss.web.control;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mss.pojo.DemoPojo;
import com.mss.facade.DemoService;
import com.mss.util.DateUtil;

/**
 * mybatis测试control
 * @author zt
 * @version 20160531
 */
@RestController
@Scope("prototype")
public class DemoControl extends BaseControl{
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping(value="/addDemo")
	public void addDemo(){
		getCurrentUsername();
		demoService.addDemo(null);
	}
	
	@RequestMapping(value="/modifyDemo")
	public void modifyDemo(){
		demoService.modifyDemo(null);
	}
	
	@RequestMapping(value="/removeDemo")
	public void removeDemo(){
		demoService.removeDemo(null);
	}
	
	@RequestMapping(value="/queryDemo")
	public void queryDemo(HttpServletResponse response) throws Exception{
		StringBuffer sb = new StringBuffer();
		
		List<DemoPojo> list = demoService.queryDemo(null);
		for(DemoPojo demo:list){
			sb.append(demo.getId()+"|");
			sb.append(demo.getAmount()+"|");
			sb.append(demo.getRemark()+"|");
			sb.append(DateUtil.formatDate(demo.getCreateDate(), "yyyy/MM/dd HH:mm:ss"));
			sb.append("\n");
		}
		
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/plain");
		response.getWriter().write(sb.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
}
