package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Task;
import model.User;

/**
 * Servlet implementation class navServlet
 */
@WebServlet("/navServlet")
public class navServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public navServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TaskHelper dao = new TaskHelper();
		UserHelper uh = new UserHelper();

		String action = request.getParameter("taskAction");

		String path = "/viewTasksServlet";

		String errMsg = "";

		if (action.equals("add")) {
			String due = request.getParameter("due");
			String description = request.getParameter("description");
			if (description.isEmpty()) {
				errMsg = "*you must enter a description";
			} else {
				Task taskToAdd = new Task(due, description);
				dao.addItem(taskToAdd);
				if (request.getParameter("user").isEmpty()) {
				} else {
					Integer userId = Integer.parseInt(request.getParameter("user"));
					User user = uh.searchForUserById(userId);
					taskToAdd.setUser(user);
					dao.editTask(taskToAdd);
				}
			}

		} else if (action.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Task taskToEdit = dao.searchForTaskById(tempId);
				request.setAttribute("taskToEdit", taskToEdit);
				request.setAttribute("showUpdateRow", "display: table-row;");
			} catch (NumberFormatException e) {
				errMsg = "*you must make a selection first";
			}

		} else if (action.equals("update")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("idUpdate"));
				Integer completed = Integer.parseInt(request.getParameter("completedUpdate"));
				String due = request.getParameter("dueUpdate");
				String description = request.getParameter("descriptionUpdate");

				Task taskToUpdate = dao.searchForTaskById(tempId);

				if (request.getParameter("userUpdate").isEmpty()) {
				} else {
					Integer userId = Integer.parseInt(request.getParameter("userUpdate"));
					User userToAssign = uh.searchForUserById(userId);
					taskToUpdate.setUser(userToAssign);
					
				}
				taskToUpdate.setCompleted(completed);
				taskToUpdate.setDue(due);
				taskToUpdate.setDescription(description);
				dao.editTask(taskToUpdate);
//				System.out.println(taskToUpdate.toString());
			} catch (NumberFormatException e) {
				errMsg = "*you must make a selection first and then click <em>edit</em>";
			}

		} else if (action.equals("del")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Task taskToDelete = dao.searchForTaskById(tempId);
				dao.deleteTask(taskToDelete);
			} catch (NumberFormatException e) {
				errMsg = "*you must make a selection first";
			}

		} else if (action.equals(" ")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));

				Task taskToUpdate = dao.searchForTaskById(tempId);
				taskToUpdate.setCompleted(1);
				dao.editTask(taskToUpdate);
			} catch (NumberFormatException e) {
				errMsg = "*you must make a selection first";
			}
		} else {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));

				Task taskToUpdate = dao.searchForTaskById(tempId);
				taskToUpdate.setCompleted(0);
				dao.editTask(taskToUpdate);
			} catch (NumberFormatException e) {
				errMsg = "*you must make a selection first";
			}
		}
		request.setAttribute("errMsg", errMsg);
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
