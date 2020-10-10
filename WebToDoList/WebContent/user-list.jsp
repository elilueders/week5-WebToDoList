<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>

	<header>
		<h1>User List</h1>
	</header>
	<form action="navUserServlet" method="post">
		<table>
			<tr>
				<th>Name</th>
				<th></th>
				<th></th>
				<th>Action</th>
			</tr>
			<c:forEach items="${requestScope.allUsers}" var="user">
				<tr>
					<td>${user.userName}</td>
					<td></td>
					<td></td>
					<td><input type="submit" name="userAction" value="del"
						style="width: 112px;"> <!-- <input type="submit"
						name="taskAction" value="edit" style="width: 40px;"> --> <input
						type="radio" name="id" value="${user.id}"></td>
				</tr>
			</c:forEach>
			<tr id="addRow">
				<td><input type="text" name="name" size="46"></td>
				<td></td>
				<td></td>
				<td><input type="submit" name="userAction" value="add"
					style="width: 112px;"></td>
			</tr>
			<%-- <tr id="updateRow">
				<td><input type="hidden" name="completedUpdate"
					value="${taskToEdit.completed}"></td>
				<td><input type="text" name="dueUpdate" size="8"
					value="${taskToEdit.due}"></td>
				<td><input type="text" name="descriptionUpdate" size="46"
					value="${taskToEdit.description}"></td>
				<td><input type="hidden" name="idUpdate"
					value="${taskToEdit.id}"> <input type="submit"
					name="taskAction" value="update" style="width: 112px;"></td>
			</tr> --%>
		</table>
	</form>
	<footer>
		<h3 id="errorMessage">${errMsg}</h3>
	</footer>

</body>
</html>