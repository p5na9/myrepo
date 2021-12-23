<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>core - forEach</title>
<style>
table{
	border: 1px solid #000;
	border-collapse: collapse;
}
td,th{
	border: 1px solid #000;
	padding: 5px;
}
</style>
</head>
<body>
	<h1>core - forEach</h1>
	<h2>list</h2>
	<ul>
		<c:forEach items="${names}" var="name" varStatus="vs">
			<li>${vs.index} ${vs.count} ${vs.first} ${vs.last} ${name}</li>
					
		</c:forEach>
	</ul>
	
	<p>
		홍길동, 신사임당, 세종대왕, 윤봉준
	</p>
	<p>
		<c:forEach items="${names}" var="names" varStatus="vs">
				<%--${names}
				 <c:if test="${not vs.last ?}">
					,
				</c:if>
				--%>
				${names}${not vs.last ? "," : "" }
		</c:forEach>
	</p>
	
	<div>
		<h3>회원목록</h3>
		<table>
			<tr>
				<th>No</th>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>결혼여부</th>
			</tr>
			<c:forEach items="${persons}" var="p" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td>${p.name}</td>
				<td>
					<select name="" id="">
						<option value="M" ${p.gender eq 'M'.charAt(0) ? 'selected' : ''}>남</option>
						<option value="F" ${p.gender eq 'F'.charAt(0) ? 'selected' : ''}>여</option>
					</select>
				</td>
				<td>${p.age}</td>
				<td>
					<input type="checkbox" ${p.married ? "checked" : "" } />
				</td>
			</tr>
			
			</c:forEach>
		</table>
	</div>
	<h2>set</h2>
	<ul>
	<!--  set은 입력한 순서를 보장하지 않는다. -->
		<c:forEach items="${numbers}" var="numbers">
		<li>${numbers}</li>
		</c:forEach>
	</ul>
	<h2>map</h2>
	<c:forEach items="${map}" var="entry">
		<%-- <p>${entry}</p>--%>
		<p>${entry.key} : ${entry.value}</p>
	</c:forEach>
</body>
</html>