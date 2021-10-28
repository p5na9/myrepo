<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>유효성검사</title>
    <style>
    table#enroll{
        border: 1px solid;
        border-spacing:10px;
        width: 500px;
        margin: 0 auto;
    }
    table#enroll th{
        text-align:right;
    }
    table#enroll td.btn-wrapper{
        text-align: center;
    }
    .phone {
        width:30px;
    }
    span.req{
        color:red;
    }
    .short{
        width:50px;
    }
    </style>
</head>
<body>
​
    <h3>회원가입 유효성검사</h3>
    <p>
        회원가입시 각 필드마다 유효성 검사를 하여 처리 할 수 있도록 만들어라. <br>
        1. ID는 영어소문자,숫자만 가능해야함(4~12자리,소문자로 시작해야함) <br>
        2. 비밀번호는 숫자/문자/특수문자(*!&) 포함 형태의 8~15자리<br>
        3. 비밀번호와 비밀번호확인은 같아야 함<br>
        4. 이름은 무조건 한글만 가능해야 함(최소2글자 이상)<br>
	5. 주민번호는 자릿수6자리,7자리이고, 모두 숫자여야함.<br>
        6. 이메일은 @가 포함 되어야 함(@앞에가 영문자,숫자로  4~12글자 ) <br>
        7. 전화번호는 첫번째 필드는 010만 입력 가능 해야 함 <br>
                    두번째 필드는 3자리 이상 입력 해야 함 <br>
                    세번째 필드는 무조건 4자리 입력 해야함 <br>
                    (모두 숫자만 가능 해야함) <br>
    </p>
    <form 
    action="<%= request.getContextPath() %>/member/enroll.do" 
    method="POST">
    <!-- table#enroll>(tr>th+td)*10 -->
        <table id="enroll">
            <tr>
                <th>아이디<span class="req">*</span></th>
                <td>
                    <input type="text" name="userId" id="userId" value="p5na9" >
                </td>
            </tr>
            <tr>
                <th>비밀번호<span class="req">*</span></th>
                <td>
                    <input type="password" name="pwd" id="pwd" value="1234">
                </td>
            </tr>
            <tr>
                <th>비밀번호확인</th>
                <td>
                    <input type="password" id="pwdCheck" value="1234">
                </td>
            </tr>
            <tr>
                <th>이름<span class="req">*</span></th>
                <td>
                    <input type="text" name="userName" id="userName" value="희연" > 
                </td>
            </tr>
            <tr>
                <th>주민번호<span class="req">*</span></th>
                <td>
                    <input type="text" name="ssn1" id="ssn1" class="short" >-
                    <input type="password" name="ssn2" id="ssn2" class="short" >
                </td>
            </tr>
            <tr>
                <th>이메일<span class="req">*</span></th>
                <td>
                    <input type="email" name="email" id="email" value="p5na9@naver.com">
                </td>
            </tr>
            <tr>
                <th>전화번호<span class="req">*</span></th>
                <td>
                <!-- input:text[name=tel$]#tel$.phone*3 -->
                    <input type="text" name="tel1" id="tel1" class="phone" >-
                    <input type="text" name="tel2" id="tel2" class="phone" >-
                    <input type="text" name="tel3" id="tel3" class="phone" >
                </td>
            </tr>
            <tr>
                <th>직업</th>
                <td>
                    <select name="job" id="job">
                        <option value="공무원">공무원</option>
                        <option value="개발자">개발자</option>
                        <option value="무직" selected>무직</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>취미</th>
                <td>
            <!-- (input:checkbox#hobby$[name=hobby]+label[for=hobby$])*5 -->
                    <input type="checkbox" name="hobby" id="hobby1" value="reading" checked>
                    <label for="hobby1">독서</label>
                    <input type="checkbox" name="hobby" id="hobby2" value="development">
                    <label for="hobby2">개발</label>
                    <input type="checkbox" name="hobby" id="hobby3" value="exercise">
                    <label for="hobby3">운동</label>
                    <input type="checkbox" name="hobby" id="hobby4" value="game">
                    <label for="hobby4">게임</label>
                    <input type="checkbox" name="hobby" id="hobby5" value="movie">
                    <label for="hobby5">영화</label>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="btn-wrapper">
                    <input type="submit" value="제출">&nbsp;
                    <input type="reset" value="초기화">
                </td>
            </tr>
        </table>
    </form>
    <script>
​
​
	document.memberFrm.onsubmit = function(){
​
        var userId = document.getElementById("userId");
        var pwd = document.getElementById("pwd");
        var pwdCheck = document.getElementById("pwdCheck");
        var userName = document.getElementById("userName");
        var email = document.getElementById("email");
        var ssn1 = document.getElementById("ssn1");
        var ssn2 = document.getElementById("ssn2");
        var tel1 = document.getElementById("tel1");
        var tel2 = document.getElementById("tel2");
        var tel3 = document.getElementById("tel3");
​
        //1.아이디검사
        //첫글자는 반드시 영소문자로 이루어지고, 
        //숫자가 하나이상 포함되어야함.
        //아이디의 길이는 4~12글자사이
        var regExp1 = /^[a-z][a-z\d]{3,11}$/;
        var regExp2 = /[0-9]/;
        // if(!regExpTest(regExp1
        //               ,userId
        //               ,"아이디는 영소문자로 시작하는 4~12글자입니다."))
        //     return false;
        // if(!regExpTest(regExp2
        //               ,userId
        //               ,"아이디는 숫자를 하나이상 포함하세요."))
        //     return false;
​
​
        //2.비밀번호 확인 검사
        //숫자/문자/특수문자/ 포함 형태의 8~15자리 이내의 암호 정규식 
        //전체길이검사 /^.{8,15}$/
        //숫자하나 반드시 포함 /\d/ 
        //영문자 반드시 포함 /[a-zA-Z]/
        //특수문자 반드시 포함  /[\*!&]/
			
		var regExpArr = [/^.{8,15}$/, /\d/, /[a-zA-Z]/, /[\*!&]/];
​
        for(let i = 0; i < regExpArr.length; i++){
            if(!regExpTest(regExpArr[i], pwd, "비밀번호는 8~15자리 숫자/문자/특수문자를 포함해야합니다.")){
                return false;
            }
        }
        
	    //비밀번호일치여부
        if(!isEqualPwd()){
            return false;
        }
​
        //3.이름검사
        //한글2글자 이상만 허용. [가-힣] 으로 해도되긴 하지만 자음만(ㄱㄴㄷㄹ)있으면 필터링이 안됨
        var regExp3 = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,}$/;
        if(!regExpTest(regExp3,userName,"한글2글자이상 입력하세요."))
            return false;
​
        //4.주민번호체크
        var regExp4 = /^\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[01])$/;
        var regExp5 = /^[1234]\d{6}$/;
        if(!regExpTest(regExp4,ssn1,"숫자만 입력하세요."))
            return false;
        if(!regExpTest(regExp5,ssn2,"숫자만 입력하세요."))
            return false;
​
        if(!ssnCheck(ssn1.value,ssn2.value)){
            alert("올바른 주민번호가 아닙니다.");
            return false;
        }
​
        //5.이메일 검사
        // 4글자 이상(\w = [a-zA-Z0-9_], [\w-\.]) @가 나오고
        // 1글자 이상(주소). 글자 가 1~3번 반복됨
        if(!regExpTest(/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/, email, "이메일 형식에 어긋납니다."))
                return false;
​
        
        
        //6. 전화번호 검사
        // 전화번호 앞자리는 010, 두번째 자리는 3~4자리 숫자, 세번째 자리는 4자리 숫자
        if (!regExpTest(/^010$/, tel1, "번호 2자리 이상 입력"))
                return false;
        if (!regExpTest(/^[0-9]{3,4}$/, tel2, "번호 3자리 이상 입력"))
                return false;
        if (!regExpTest(/^[0-9]{4}$/, tel3, "4자리 번호 입력"))
                return false;
​
        return true;
    }
​
    function ssnCheck(ssn1, ssn2){
        var ssn = ssn1+ssn2;
        /*
			주민등록번호 체계 및 유효성 검사 (javascript)	
			https://eyecandyzero.tistory.com/240	
​
			//900909-1234561
			var total = 9*2 + 0*3 + 0*4 + 9*5 + 0*6 + 9*7 + 1*8 + 2*9 + 3*2 + 4*3 + 5*4 + 6*5;//220
            var result = total%11;//0
			result = 11-0;//11
			result = result%10;//1
​
			if(result == 13번째자리수) return true;
			else return false;
​
        */
        var total = 0;
        for(var i=0; i<12; i++){
            if(i<8){
                total += parseInt(ssn.substr(i,1))*(i+2);
            }
            else {
                total += parseInt(ssn.substr(i,1))*(i-6);
            }
        }
        //마지막수와 비교할 수 구하기
        var result = (11-(total%11))%10;
        //마지막수(13번째 자리)
        var num13 = parseInt(ssn.substr(12,1));
        console.log(result+'=='+num13);
        //결과
        if(result==num13)
            return true;
        else 
            return false;
    }
​
	function isEqualPwd(){
        if(pwd.value == pwdCheck.value){
            return true;
        }
        else{
            alert("비밀번호가 일치하지 않습니다.");
            pwd.select();
            return false;
        }
    }
​
    function regExpTest(regExp, el, msg){
        if(regExp.test(el.value))
            return true;
        //적합한 문자열이 아닌 경우
        alert(msg);
        el.value="";
        el.focus();
        return false;
    }
    </script>
​
​
</body>
</html>