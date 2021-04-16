package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import it.unisa.model.UserBean;
import it.unisa.model.UserModel;
import it.unisa.model.UserModelDS;

/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static UserModelDS ds = new UserModelDS();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		try {
			checkSignUp(username, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.sendError(Response.SC_FORBIDDEN, "Username o email già registrati!");
			return;
		}
		UserBean user = new UserBean();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setRole(UserBean.Role.Cliente);
		try {
			ds.doSave(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("common/dispatch.jsp?registration=ok");
	}
	
	private void checkSignUp(String username, String email) throws Exception {
		UserBean user = null;
		UserBean user2 = null;
		try {
			user = ds.doRetrieveByUsername(username);
			user2 = ds.doRetrieveByEmail(email);
		} catch (SQLException e) {
			throw new SQLException("Errore di connessione al database");
		}
		if (user == null || user2 == null || user.getId() != 0 || user2.getId() != 0)
			throw new Exception("Invalid login and password");
		}
}
