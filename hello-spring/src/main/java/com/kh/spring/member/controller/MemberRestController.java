package com.kh.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

/**
 * data를 직접 응답메세지에 출력 
 *
 */
@Controller
@RequestMapping("/member")
@Slf4j
public class MemberRestController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/selectOneMember.do")
	public ResponseEntity<?> selectOneMember(@RequestParam String id) {
		Member member = memberService.selectOneMember(id);
		if(member != null)
			return ResponseEntity.ok(member);
		else 
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/findMemberByName.do")
	public ResponseEntity<?> findMemberByName(String name){
		List<Member> list = memberService.findMemberByName(name);
		if(list != null)
			return ResponseEntity.ok(list);
		else
			return ResponseEntity.notFound().build();
	}
}
