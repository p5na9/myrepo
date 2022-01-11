package com.kh.spring.menu.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.menu.model.vo.Menu;

/**
 * 
 * - namespace : com.kh.spring.menu.model.dao.MenuDao
 * - id : method이름
 *
 */
@Mapper
public interface MenuDao {

	List<Menu> selectAllMenu();

	List<Menu> selectMenuByTypeAndTaste(Map<String, Object> param);

	int insertMenu(Menu menu);

	@Select("select * from menu where id = #{id}")
	Menu selectOneMenu(int id);

}
