package com.kh.spring.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.menu.model.service.MenuService;
import com.kh.spring.menu.model.vo.Menu;

import lombok.extern.slf4j.Slf4j;

/**
 * @RestController
 * @Controller + 모든 메소드에 @ResponseBody에 적용 
 * 
 *
 */
@RestController
@Slf4j
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/menus")
	public List<Menu> menus(){
		List<Menu> list = menuService.selectAllMenu();
		log.debug("list = {}", list);
		return list;
	}
	
	//실습문제
	@GetMapping("/menu/{no}")
    public ResponseEntity<Menu> selectOneMenu(@PathVariable int no) {
        
		try {
            Menu menu = menuService.selectOneMenu(no);
    
            if(menu != null) {
                return ResponseEntity.ok(menu);
            } 
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

	/**
	 * @PathVariable 경로변수
	 * - url의 일부를 매개인자로 받을 수 있다.
	 */
	@GetMapping("/menus/{type}/{taste}")
	public List<Menu> selectMenu(@PathVariable String type, @PathVariable String taste){
		log.debug("type = {}, taste = {}", type, taste);
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("taste", taste);
		return menuService.selectMenuByTypeAndTaste(param);
	}
	
	/**
	 * @RequestBody
	 * - 요청메세지바디의 json문자열을 parsing해서 java객체로 변환
	 */
	@PostMapping("/menu")
	public Map<String, Object> insertMenu(@RequestBody Menu menu){
		log.debug("menu = {}", menu);
		int result = menuService.insertMenu(menu);
		
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "등록성공!");
		map.put("result", result);
		return map;
	}
}








