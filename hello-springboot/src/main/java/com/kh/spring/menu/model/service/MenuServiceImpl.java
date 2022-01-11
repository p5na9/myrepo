package com.kh.spring.menu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.menu.model.dao.MenuDao;
import com.kh.spring.menu.model.vo.Menu;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> selectAllMenu() {
		return menuDao.selectAllMenu();
	}

	@Override
	public List<Menu> selectMenuByTypeAndTaste(Map<String, Object> param) {
		return menuDao.selectMenuByTypeAndTaste(param);
	}

	@Override
	public int insertMenu(Menu menu) {
		return menuDao.insertMenu(menu);
	}

	@Override
	public Menu selectOneMenu(int id) {
		return menuDao.selectOneMenu(id);
	}
	
	
}
