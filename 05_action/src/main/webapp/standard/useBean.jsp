<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%--request의 속성에 저장된 person객체(Person타입)을 가져오기
    	-id는 속성의 key 값
    	-해당 스코프에 sinsa key값의 Person객체가 없다면 , 새로 생성
    	Person sinsa = new Person();
    	
    	servlet & jsp bean 규약
    	-bean : container에 의해 생성 /관리되는 자바 객체
    	-기본생성자가 작성되어있어야 한다.
    	-getter/setter가 작성되어야 한다.
    	-property참조방식(OGNL Object Graphical Navigation Language)에 의해 get/set이 처리된다.
    	-필드의 타입이 String/기본형이 아니라면, 표준액션에서 사용시 제약이 있으므로 scriptlet을 이용할것
    	 --%>
<jsp:useBean id="person" class="com.kh.action.person.model.vo.Person" scope="request"></jsp:useBean>
<jsp:useBean id="sinsa" class="com.kh.action.person.model.vo.Person" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp - useBean</title>
</head>
<body>
	<h1>useBean</h1>
	<h2>person</h2>
	<p>성명:<jsp:getProperty property="name" name="person"/></p>
	<p>성별:<jsp:getProperty property="gender" name="person"/></p>
	<p>나이:<jsp:getProperty property="age" name="person"/></p>
	<p>결혼여부:<jsp:getProperty property="married" name="person"/></p>

	<h2>sinsa</h2>
	<jsp:setProperty property="name" name="sinsa" value="신사임당"/>
	<jsp:setProperty property="gender" name="sinsa" value="F"/>
	<jsp:setProperty property="age" name="sinsa" value="48"/>
	<jsp:setProperty property="married" name="sinsa" value="true"/>
	
	<p>성명:<jsp:getProperty property="name" name="sinsa"/></p>
	<p>성별:<jsp:getProperty property="gender" name="sinsa"/></p>
	<p>나이:<jsp:getProperty property="age" name="sinsa"/></p>
	<p>결혼여부:<jsp:getProperty property="married" name="sinsa"/></p>


</body>
</html>