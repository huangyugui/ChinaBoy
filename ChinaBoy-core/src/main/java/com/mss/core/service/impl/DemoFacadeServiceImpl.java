package com.mss.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mss.dao.DemoDao;
import com.mss.facade.DemoFacadeService;
import com.mss.pojo.DemoPojo;

/**
 * mybatis测试service实现
 * @author zt
 * @version 20160531
 */
@Service("demoFacadeService")
@Scope("singleton")
public class DemoFacadeServiceImpl implements DemoFacadeService {

	@Autowired
	private DemoDao demoDao;

	@Override
	public void addDemo(DemoPojo demoPojo) {
		demoDao.addDemo(demoPojo);
	}

	@Override
	public void modifyDemo(DemoPojo demoPojo) {
		demoDao.updateDemo(demoPojo);
	}

	@Override
	public List<DemoPojo> queryDemo(DemoPojo demoPojo) {
		return demoDao.selectDemoList();
	}

	@Override
	public void removeDemo(DemoPojo demoPojo) {
		demoDao.deleteDemo();
	}
	
	
}
