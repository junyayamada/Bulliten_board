<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<title>ユーザーの編集</title>
</head>
<body>
<p class="title"><ul align="center"><h2>ユーザー編集</h2></ul></p>
<div class="editUserErrorMessages">
	<c:if test="${ not empty errorMessages }">
		<div>
			<ul style="list-style:none;">
				<c:forEach items ="${ errorMessages }" var="message">
					<li>
					<FONT color="red"><c:out value="${ message }" /></FONT>
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
</div>

<div class="edit">
	<form action="edit" method="post"  enctype="multipart/form-date"><br />
		<input type="hidden" name="id" value="${ edit.id }" ></input>
		<label for="name">名前(10文字以下)</label>
		<br />
		<input name="name" value="${ edit.name }" id="name"/>
		<br /><br />
		<label for="account">ログインID(半角英数字6～20字)</label>
		<br />
		<input name="account" value="${ edit.account }" />
		<br /><br />
		<label for="password">パスワード(記号含む半角6文字以上255文字以下)</label>
		<br />
		<input name="password" type="password" id="password"/>
		<br /><br />
		<label for="confirmpassword">パスワード(もう一度入力してください)</label>
		<br />
		<input name=confirmPassword type="password" id="confirmpassword" />
		<br /><br />


        <label for="branch">支店名</label><br/>
        	<c:if test="${loginUser.id == editUser.id}">
			    <c:out value="${loginUser.branch_id}"></c:out>
		    </c:if>
		    <c:if test="${loginUser.id != editUser.id}">
			    <select name="branch_id">
				<c:forEach  items="${ branch }"  var = "branch">
					<c:if test="${ branch.id == editUser.branchId }" >
						<option  class="branch" value= "${ branch.id }" selected="${ branch.name }">${ branch.name }</option>
					</c:if>
					<c:if test="${ branch.id != editUser.branchId }">
						<option  class="branch" value="${ branch.id }">${ branch.name }</option>
					</c:if>
				</c:forEach>
			</select></c:if><br/>
		<br/>


<%--

		<label for="branch">支店名</label><br/>
			<select name="branch_id">
				<c:forEach  items="${ branch }"  var = "branch">
				    <option  class="branch" value= "${ branch.id }" selected>${ branch.name }</option>
				</c:forEach>
			</select><br/>
		<br/>
--%>

<%--			<c:if test="${ branch.id == editUser.branch_id }" >
						<option  class="branch" value= "${ branch.id }" selected="${ branch.name }">${ branch.name }</option>
					</c:if>
					<c:if test="${ branch.id != editUser.branch_id }">
						<option  class="branch" value="${ branch.id }">${ branch.name }</option>
					</c:if>
				</c:forEach>
			</select><br/>
		<br/>



		<label for="branch">支店名</label><br/>
					<c:if test="${branch.id==user.branch_id}">
						<option class ="branch" value = "${branch.id}" selected="${branch.id}">${branch.name}</option></c:if>
					<c:if test="${branch.id!=user.branch_id}">
						<option class ="branch" value = "${branch.id}">${branch.name}</option></c:if>
					<c:if test="${branchId==null}">
						<option class ="branch" value = "${branch.id}">${branch.name}</option></c:if>
		<br/>
		--%>





		<label for="department">部署</label>
		<br/>
		<c:if test="${loginUser.id == editUser.id}">
			<c:out value="人事総務部"></c:out>
		</c:if>
		<c:if test="${loginUser.id != editUser.id}">
			<select name="department_id">
				<c:forEach  items="${ department }"  var = "department">
					<c:if test="${ department.id == editUser.departmentId }" >
						<option  class="department" value= "${ department.id }" selected="${ department.name }">${ department.name }</option>
					</c:if>
					<c:if test="${ department.id != editUser.departmentId }">
						<option  class="department" value="${ department.id }">${ department.name }</option>
					</c:if>
				</c:forEach>
			</select></c:if>
		<br/><br />
		<input type="submit" value="登録"> <br />
	</form>
</div>
<br/>
<div class="back"><a href="./management">戻る</a></div><br/>
<br/>
<div class="copyright" align="center">Copyright(c)Junay Yamada</div>
</body>
</html>