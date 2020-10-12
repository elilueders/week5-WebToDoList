<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>To Do List</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<header>
		<h1>To Do List</h1>
	</header>
	<form action="navServlet" method="post" autocomplete="off">
		<table>
			<tr>
				<th>Sel</th>
				<th>&#10003;</th>
				<th>Due</th>
				<th>Description</th>
				<th>Assigned To
				<th>Action</th>
			</tr>
			<c:forEach items="${requestScope.allTasks}" var="task">
				<tr>
					<td style="background: darkgrey;"><input type="radio"
						name="id" value="${task.id}"></td>
					<td><input type="submit" name="taskAction"
						value="${task.getCompleteHTML()}"
						style="width: 25px; height: 25px;"></td>
					<td>${task.due}</td>
					<td>${task.description}</td>
					<td>${task.user.userName}</td>
					<td style="background: darkgrey;"><input type="submit"
						name="taskAction" value="del" style="width: 40px;"> <input
						type="submit" name="taskAction" value="edit" style="width: 40px;"></td>
				</tr>
			</c:forEach>
			<tr style="${showUpdateRow}" id="updateRow">
				<td colspan="2">Edit Task<input type="hidden" name="idUpdate"
					value="${taskToEdit.id}"><input type="hidden"
					name="completedUpdate" value="${taskToEdit.completed}"></td>
				<td><input type="text" name="dueUpdate" size="8"
					value="${taskToEdit.due}"></td>
				<td><input type="text" name="descriptionUpdate" size="46"
					value="${taskToEdit.description}"></td>
				<td><select name="userUpdate">
						<option value="">no change</option>
						<c:forEach items="${requestScope.allUsers}" var="currentUser">
							<option value="${currentUser.id}">${currentUser.userName}</option>
						</c:forEach>
						<option value="0">unassign</option>
				</select></td>
				<td><input type="submit" name="taskAction" value="update"
					style="width: 85px;"></td>
			</tr>
			<tr id="addRow">
				<td colspan="2">New Task</td>
				<td><input type="text" name="due" size="8"></td>
				<td><input type="text" name="description" size="46"></td>
				<td><select name="user">
						<option value="">Assign to...</option>
						<c:forEach items="${requestScope.allUsers}" var="currentUser">
							<option value="${currentUser.id}">${currentUser.userName}</option>
						</c:forEach>
				</select></td>
				<td><input type="submit" name="taskAction" value="add"
					style="width: 85px;"></td>
			</tr>
		</table>
	</form>
	<footer>
		<h3 id="errorMessage">${errMsg}</h3>
		<br> 
		<a href="viewUserServlet">User List</a>
	</footer>


</body>
</html>
