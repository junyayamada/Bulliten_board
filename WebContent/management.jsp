<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<title>ユーザー管理画面</title>

	<script type="text/javascript">
		function stop() {
			if (window.confirm('停止してよろしいですか？')) { // 確認ダイアログを表示
				return true; // 「OK」時は送信を実行
			} else { // 「キャンセル」時の処理
				window.alert('キャンセルされました'); // 警告ダイアログを表示
				return false; // 送信を中止
			}
		}
	</script>
		<script type="text/javascript">
		function revival() {
			if (window.confirm('復活してよろしいですか？')) { // 確認ダイアログを表示
				return true; // 「OK」時は送信を実行
			} else { // 「キャンセル」時の処理
				window.alert('キャンセルされました'); // 警告ダイアログを表示
				return false; // 送信を中止
			}
		}
	</script>
	<script type="text/javascript">
		function check() {
			if (window.confirm('削除してよろしいですか？')) { // 確認ダイアログを表示
				return true; // 「OK」時は送信を実行
			} else { // 「キャンセル」時の処理
				window.alert('キャンセルされました'); // 警告ダイアログを表示
				return false; // 送信を中止
			}
		}
	</script>
</head>
<body>
<p class="title"><ul align="center"><h2>ユーザー管理</h2></ul></p>
<div class="managementErrorMessages">
<c:if test="${ not empty errorMessages }">
	<div>
		<ul style="list-style:none;">
			<c:forEach items ="${ errorMessages }" var="message">
				<FONT color="red"><c:out value="${ message }" /></FONT>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
</div>

<div class="managementBack"><a href="./">ホーム</a></div><br/>
<div class="signupLink"><a href="./signup">ユーザー新規登録</a></div>
<table border="1" cellspacing="1" cellpadding="5">
	<thead>
		<tr align="center">
			<th>ログインID</th>
			<th>名前</th>
			<th>支店</th>
			<th>部署</th>
			<th colspan="3">状態</th>
		</tr>
	</thead>
		<tbody>
			<c:forEach items="${managements}" var="management">
				<tr align="center">
					<td><span class="management_account"><c:out value="${management.account}"/></span></td>
					<td><span class="management_name"><c:out value="${management.name}"/></span></td>

					<td><span class="branch_name">
						<c:forEach items="${branches}" var="branch">
							<c:if test="${branch.id == management.branchId }">
								<c:out value="${branch.name}"/>
							</c:if>
						</c:forEach>
					    </span>
					</td>
					<td><span class="departmet_name">
						<c:forEach items="${departmentes}" var="department">
							<c:if test="${department.id == management.departmentId }">
								<c:out value="${department.name}"/>
							</c:if>
						</c:forEach>
					    </span>
					</td>

					<td>
						<form action="edit" method="get">
							<input type="hidden" name="id" value="${ management.id }"></input>
							<input class="edit" type="submit" value="編集"></input>
						</form>
					<td>
					<c:if test="${loginUser.id != management.id }">
						<form action="management" method="post" onSubmit="return stop()">
						    <input type="hidden" name="id" value="${management.id}"></input>
							<c:if test="${management.isStoped == 0}">
								<input type="hidden" name="isStoped" value="1"></input>
								<input type="submit" value="停止" class="stop1"></input>
							</c:if>
						</form>
					</c:if>
					    <form action="management" method="post" onSubmit="return revival()">
						    <input type="hidden" name="id" value="${management.id}"></input>
							    <c:if test="${management.isStoped == 1}">
									<input type="hidden" name="isStoped" value="0"></input>
									<input type="submit" value="復活" class="revival1"></input>
							    </c:if>
					    </form>
					</td>

					<td>
					<c:if test="${loginUser.id != management.id }">
						<form action="deleteuser" method="post" onSubmit="return check()">
							<input type="hidden" name="id" value="${management.id}"></input>
							<input type="submit" value="削除" class="delete1" ></input>
						</form>
					</c:if>
					</td>

				</tr>
			</c:forEach>
		</tbody>
</table>
<br/>
<br/>
<div class="copyright" align="center">Copyright(c)Junya Yamada</div>
</body>
</html>