package com.kh.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface EmpDao {

	List<Map<String, Object>> selectEmpMapList(SqlSession session);

	List<Map<String, Object>> search1(SqlSession session, Map<String, Object> param);

}
