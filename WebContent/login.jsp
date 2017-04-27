<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>掲示板へログイン</title>
</head>
<body>
<p class="title" align="center">ログイン</p>
	<c:if test="${ not empty errorMessages }">
		<div class="loginErrorMessages">
			<ul align="center" style="list-style:none;">
				<c:forEach items="${ errorMessages }" var="errormessages" >
					<FONT color="red"><c:out value="${ errormessages }" /></FONT>
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessage" scope="session"/>
	</c:if>

<div class="loginBody" align="center">
	<form action="login" method="post"><br/>
		<h4>ログインID</h4>
		<input name="account" id="account"/><br/>

		<h4>パスワード</h4>
		<input name = "password" type = "password" id = "password"/> <br />
		<br/>
		<input type = "submit" value = "ログイン" /> <br />
	</form>
</div>
<br/><br/>
<div class="copyright" align="center">Copyright(c)Junya Yamada</div>
</body>
</html>