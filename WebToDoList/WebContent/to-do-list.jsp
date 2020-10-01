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
	<form action="navServlet" method="post">
		<table>
			<tr>
				<th>&#10003;</th>
				<th>Due</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${requestScope.allTasks}" var="task">
				<tr>
					<td><input type="submit" name="taskAction"
						value="${task.getCompleteHTML()}"
						style="width: 25px; height: 25px;"></td>
					<td>${task.due}</td>
					<td>${task.description}</td>
					<td><input type="submit" name="taskAction" value="del"
						style="width: 40px;"> <input type="submit"
						name="taskAction" value="edit" style="width: 40px;"> <input
						type="radio" name="id" value="${task.id}"
						>
					</td>
				</tr>
			</c:forEach>
			<tr id="addRow">
				<td></td>
				<td><input type="text" name="due" size="8"></td>
				<td><input type="text" name="description" size="46"></td>
				<td><input type="submit" name="taskAction" value="add"
					style="width: 112px;"></td>
			</tr>
			<tr id="updateRow">
				<td><input type="hidden" name="completedUpdate"
					value="${taskToEdit.completed}"></td>
				<td><input type="text" name="dueUpdate" size="8"
					value="${taskToEdit.due}"></td>
				<td><input type="text" name="descriptionUpdate" size="46"
					value="${taskToEdit.description}"></td>
				<td><input type="hidden" name="idUpdate"
					value="${taskToEdit.id}"> <input type="submit"
					name="taskAction" value="update" style="width: 112px;"></td>
			</tr>
		</table>
	</form>
	<footer>
		<h3 id="errorMessage">${errMsg}</h3>
	</footer>


</body>
</html>
