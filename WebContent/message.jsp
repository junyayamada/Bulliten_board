<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>新規投稿</title>
</head>
<body>
<p class="title"><ul align="center"><h2>新規投稿</h2></ul></p>
<div class="newPostErrorMessages">
<c:if test="${ not empty errorMessages }">
	<div class="${ errorMessages }">
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
<div class="postBody">
<form action="message" method="post">
	<label for="subject">件名(50文字以下)</label><br/>
	<input name="subject" id="subject" value="${subject}" size="50"><br/>
	<br/>
	<label for="category">カテゴリー(10文字以下)</label><br/>
	<input name="category" id="category" value="${category}" size="50"><br/>
	<br/>
	<label for="text">本文(1000文字以下)</label><br/>
	<textarea name = "text" rows="20" cols="100" class ="text-box"><c:out value="${ text }" /></textarea><br/>
	<br/>

<input type="submit" value="投稿"><br/>
</form>
</div>
<br/>
<div class="back"><a href="./">戻る</a></div><br />
<br/>
<div class="copyright" align="center">Copyright(c)Junya Yamada</div>
</body>
</html>