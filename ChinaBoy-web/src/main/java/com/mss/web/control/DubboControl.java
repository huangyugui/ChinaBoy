package com.mss.web.control;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mss.pojo.DemoPojo;
import com.mss.facade.DemoFacadeService;
import com.mss.util.DateUtil;

/**
 * mybatis测试control
 * @author zt
 * @version 20160531
 */
@RestController
@Scope("prototype")
public class DubboControl extends BaseControl{
	
	@Autowired
	private DemoFacadeService demoFacadeService;
	
	@RequestMapping(value="/dubboAdd")
	public void addDemo(){
		getCurrentUsername();
		demoFacadeService.addDemo(null);
	}
	
	@RequestMapping(value="/dubboModify")
	public void modifyDemo(){
		demoFacadeService.modifyDemo(null);
	}
	
	@RequestMapping(value="/dubboRemove")
	public void removeDemo(){
		demoFacadeService.removeDemo(null);
	}
	
	@RequestMapping(value="/dubboQuery")
	public void queryDemo(HttpServletResponse response) throws Exception{
		StringBuffer sb = new StringBuffer();
		
		List<DemoPojo> list = demoFacadeService.queryDemo(null);
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
