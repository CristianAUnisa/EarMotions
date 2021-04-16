package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.UserBean;
import it.unisa.model.UserModel;
import it.unisa.model.UserModelDS;

public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String redirectedPage;
			try {
				UserBean bean = checkLogin(username, password);
				request.getSession().setAttribute("role", bean.getRole().name());
				request.getSession().setAttribute("username", bean.getUsername());
				request.getSession().setAttribute("idUser", bean.getId());

				redirectedPage = "/index.jsp";
				
			} catch (Exception e) {
				redirectedPage = "/login-form.jsp";
			}
			response.sendRedirect(request.getContextPath() + redirectedPage);
		}
	}

	private UserBean checkLogin(String username, String password) throws Exception {
		UserModel ds = new UserModelDS();
		UserBean user = null;
		try {
			user = ds.doRetrieveByUsername(username);
			if (!user.getPassword().equals(password))
				user = null;
		} catch (SQLException e) {
			throw new SQLException("Errore di connessione al database");
		}
		if (user != null)
			return user;
		else
			throw new Exception("Invalid login and password");
	}
	
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
