package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet implementation class navUserServlet
 */
@WebServlet("/navUserServlet")
public class navUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public navUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserHelper dao = new UserHelper();
		String action = request.getParameter("userAction");
		String path = "/viewUserServlet";
		String errMsg = "";
		
		if (action.equals("add")) {
			String name = request.getParameter("name");
			if (name.isEmpty()) {
				errMsg="*you must enter a name";
			} else {
				User userToAdd = new User(name);
				dao.addUser(userToAdd);
			}
			
			
		} else if (action.equals("del")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				User userToDelete = dao.searchForUserById(tempId);
				System.out.println(userToDelete.toString());
				dao.deleteUser(userToDelete);
			} catch (NumberFormatException e) {
				errMsg = "*you must make a selection first";
			}
		}
		request.setAttribute("errMsg", errMsg);
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
