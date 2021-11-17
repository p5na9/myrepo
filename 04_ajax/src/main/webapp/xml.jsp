<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<style>
table {
	border: 1px solid #000;
	border-collapse: collapse;
	margin-top: 10px;
}
th, td {
	border: 1px solid #000;
	padding: 5px;
}
table img {
	width: 100px;
}
</style>
</head>
<body>
	<h1>xml</h1>
	<button id="btn1">실행</button>
	<div class="celeb-container">
	<table>
		<thead>
			<tr>
				<th>이름</th>
				<th>전화번호</th>
				<th>나이</th>
				<th>프로필</th>
				<th>결혼여부</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	</div>
	<script>
	$(btn1).on('click', (e) => {
		$.ajax({
			url: "<%= request.getContextPath()%>/xml/celebList.do",
			dataType: "xml", //jquery : xml형식의 문자열을 parsing한 후 (선처리), document객체로 success함수에 전달 
			success(data){	
				console.log(data);	
				console.log(typeof data); //Object
				
				//루트태그 찾기
				const $root = $(data).find(":root");
				console.log($root);
				
				const $celebs = $root.children();
				console.log($celebs);
				
				const $tbody = $(".celeb-container table tbody");
				$tbody.empty();
				
				$celebs.each((i, celeb) => {
					const name = $(celeb).children("name").text();
					const phone = $(celeb).children("phone").text();
					const age = $(celeb).children("age").text();
					const profile = $(celeb).children("profile").text();
					const married = $(celeb).children("married").text();
					console.log(name, phone, age, profile, married);
					
					const tr = `<tr>
						<td>\${name}</td>
						<td>\${phone}</td>
						<td>\${age}</td>
						<td>
							<img src="\${profile}" alt="">
						</td>
						<td>
							<input type="checkbox" \${ married === 'true' ? 'checked' : ''} />
						</td>
					</tr>`;
					console.log(tr);
					$tbody.append(tr);
				});
			},
			error:console.log
		});
	});
	</script>
	
	<h2>일일 박스오피스 조회</h2>
	<div>
		<input type="date" id="targetDt" />
	</div>
	<div class="boxoffice-container">
		<table>
			<thead>
				<tr>
					<th>순위</th>
					<th>영화제목</th>
					<th>누적 관객수(만)</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<script>
	$(targetDt).click((e) => {
		$.ajax({
			url: "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?",
			data: {
				key: "4d6e7d50edf73925dc7c402d57976479",
				targetDt: "20211116"
			},
			success(data){
				console.log(data);
			},
			error: console.log
		});
		
	});
	
	</script>
</body>
</html>