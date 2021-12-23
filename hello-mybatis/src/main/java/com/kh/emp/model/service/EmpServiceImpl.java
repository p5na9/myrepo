package com.kh.emp.model.service;

import static com.kh.common.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.emp.model.dao.EmpDao;

public class EmpServiceImpl implements EmpService {

	private EmpDao empDao;

	public EmpServiceImpl(EmpDao empDao) {
		this.empDao = empDao;
	}

	@Override
	public List<Map<String, Object>> selectEmpMapList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.selectEmpMapList(session);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> search1(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.search1(session, param);
		session.close();
		return list;
	}
	
	
	
}
