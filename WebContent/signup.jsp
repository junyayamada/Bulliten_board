<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>ユーザー新規登録</title>
</head>
<body>

<p class="title"><ul align="center"><h2>ユーザー新規登録</h2></ul></p>
<div class="signUpErrorMessages">
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul style="list-style:none;">
				<c:forEach items="${ errorMessages }" var="errormessages">
					<li>
					<FONT color="red"><c:out value="${ errormessages }"/></FONT>
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
</div>

<div class="signupBody">
	<form action="signup" method="post">
		<label for="name">名前(10文字以下)</label>
		<br/>
		<input name="name" id="name" value="${name}" size="30"><br/>
		<br/>
		<label for="account">ログインID(半角英数字6～20字)</label>
		<br/>
		<input name="account" id="account" value="${account}" size="30"><br/>
		<br/>
		<label for="password">登録用パスワード(記号含む半角6文字以上255文字以下)</label><br/>
		<input name="password" type = "password" id="password" size="30"><br/>
		<br/>
		<label for="confirmpassword">確認用パスワード(もう一度入力してください)</label><br/>
			<input name="confirmPassword" type = "password" id="confirmpassword" size="30"><br/>
		<br/>
		<label for="branch">支店名</label><br/>
			<select name="branchId">
					<c:forEach  items = "${branch}"  var = "branch">
					<c:if test="${branch.id==branchId}">
						<option class ="branch" value = "${branch.id}" selected="${branch.id}">${branch.name}</option></c:if>
					<c:if test="${branch.id==users.branchId}">
						<option class ="branch" value = "${branch.id}">${branch.name}</option></c:if>
					<c:if test="${branchId==null}">
						<option class ="branch" value = "${branch.id}">${branch.name}</option></c:if>
				</c:forEach>
			</select><br/>
		<br/>
		<label for="department">部署</label><br/>
			<select name="departmentId">
				<c:forEach items = "${ department }" var = "department">
					<c:if test="${ department.id == departmentId }">
						<option class ="department" value = "${department.id}" selected="${department.id}">${ department.name }</option></c:if>
					<c:if test="${ department.id == users.departmentId }">
						<option class ="department" value = "${department.id}">${ department.name }</option></c:if>
					<c:if test="${ departmentId == null }">
						<option class ="department" value = "${department.id}">${ department.name }</option></c:if>
				</c:forEach>
			</select>
		<br/>
		<br/>

		<input type="submit" value="登録"/><br/>
		</form>
	</div>
	<br/>
<div class="managementBack"><a href="./management">戻る</a></div><br/>
<br/>
<div class="copyright" align="center">Copyright(c)Junya Yamada</div>
</body>
</html>