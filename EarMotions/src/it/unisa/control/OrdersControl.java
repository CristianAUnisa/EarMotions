package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.BillingBean;
import it.unisa.model.BillingModelDS;
import it.unisa.model.OrderBean;
import it.unisa.model.OrderModelDS;
import it.unisa.model.PurchaseBean;
import it.unisa.model.PurchaseModelDS;
import it.unisa.model.UserModelDS;

import com.google.gson.Gson;

/**
 * Servlet implementation class OrdersControl
 */
@WebServlet("/OrdersControl")
public class OrdersControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static OrderModelDS model = new OrderModelDS();
	static PurchaseModelDS pmodel = new PurchaseModelDS();
	static UserModelDS umodel = new UserModelDS();
	static BillingModelDS bmodel = new BillingModelDS();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String role = (String) request.getSession().getAttribute("role");
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if (role == null || !role.equalsIgnoreCase("amministratore"))
        	userOrdini(request, response);
        else if (role.equalsIgnoreCase("amministratore"))
			try {
				adminOrdini(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void adminOrdini(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, ServletException {
		String tipo = (String) request.getParameter("type");
		
		if (tipo.equalsIgnoreCase("ordini")) {
			Collection<OrderBean> orders = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String inizio = request.getParameter("inizio");
			String fine = request.getParameter("fine");
			String stato = request.getParameter("stato");
			String utente = request.getParameter("utente");
			Timestamp parsedInizio = null;
			Timestamp parsedFine = null;
			if (inizio == null || fine == null || inizio.equals("") || fine.equals("")) {
				parsedInizio = new Timestamp(0);
				parsedFine = new Timestamp(System.currentTimeMillis());
			}
			else {
				parsedInizio = new Timestamp(format.parse(inizio).getTime());
				parsedFine = new Timestamp(format.parse(fine).getTime());
			}
			try {
				int idUser = umodel.doRetrieveByUsername(utente).getId();
				orders = model.doRetrieveByParameters(stato, parsedInizio, parsedFine, idUser);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write(new Gson().toJson(orders));
			return;
		}
		else if (tipo.equalsIgnoreCase("dettaglioordine")) {
			OrderBean order = null;
			BillingBean billing = null;
			try {
				order = model.doRetrieveByKey(Integer.parseInt(request.getParameter("numOrder")));
				billing = bmodel.doRetrieveByNumOrder(order.getNumOrder());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("order", order);
			request.setAttribute("billing", billing);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/OrderPage.jsp");
			rd.forward(request, response);
			return;	
		} else if (tipo.equalsIgnoreCase("cambioStato")) {
			OrderBean order = null;
			OrderBean.Status status = OrderBean.Status.valueOf(request.getParameter("status").toUpperCase());
			BillingBean billing = null;
			try {
				order = model.doRetrieveByKey(Integer.parseInt(request.getParameter("numOrder")));
				billing = bmodel.doRetrieveByNumOrder(order.getNumOrder());
				order.setStato(status);
				model.doUpdate(order);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("order", order);
			request.setAttribute("billing", billing);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/OrderPage.jsp");
			rd.forward(request, response);
			return;	
		}
	}
	
	private void userOrdini(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = (int) request.getSession().getAttribute("idUser");
		String tipo = (String) request.getParameter("type");
		if (tipo.equalsIgnoreCase("ordini")) {
			Collection<OrderBean> orders = null;
			try {
				orders = model.doRetrieveByUserId(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write(new Gson().toJson(orders));
		}
		else if (tipo.equalsIgnoreCase("prodotti")) {
			Collection<PurchaseBean> purchases = null;
			try {
				purchases = pmodel.doRetrieveByNumOrder(Integer.parseInt(request.getParameter("numOrder")));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write(new Gson().toJson(purchases));
		}
		else if (tipo.equalsIgnoreCase("prodotto")) {
			PurchaseBean prodotto = null;
			try {
				prodotto = pmodel.doRetrieveByKey(Integer.parseInt(request.getParameter("code")), Integer.parseInt(request.getParameter("numOrder")));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write(new Gson().toJson(prodotto));
			return;
		}
	}
}