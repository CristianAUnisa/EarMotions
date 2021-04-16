package it.unisa.control;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.BillingBean;
import it.unisa.model.BillingModelDS;
import it.unisa.model.Cart;
import it.unisa.model.PurchaseBean;
import it.unisa.model.OrderModelDS;
import it.unisa.model.ProductBean;
import it.unisa.model.ProductModel;
import it.unisa.model.ProductModelDS;
import it.unisa.model.PurchaseModelDS;

/**
 * Servlet implementation class CheckoutServlet
 */
public class CheckoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		int user;
		try {
			user = (int) request.getSession().getAttribute("idUser");
		} catch (Exception e) {
			response.sendRedirect("login-form.jsp");
			return;
		}
		if (cart == null) {
			response.sendRedirect("checkout.jsp?error=true");
			return;
		}
		if (user == 0) {
			response.sendRedirect("login-form.jsp");
			return;
		}
		
		BillingBean bill = new BillingBean();
		bill.setName(request.getParameter("nome"));
		bill.setSurname(request.getParameter("cognome"));
		bill.setEmail(request.getParameter("email"));
		bill.setNumtelefono(request.getParameter("telefono"));
		bill.setRegione(request.getParameter("regione"));
		bill.setProvincia(request.getParameter("provincia"));
		bill.setCitta(request.getParameter("citta"));
		bill.setCap(request.getParameter("cap"));
		bill.setIndirizzo(request.getParameter("indirizzo"));
		bill.setPagamento(request.getParameter("pagamento"));
		
		List<PurchaseBean> purchases = cart.getProducts();
		
		if ( !purchases.isEmpty() ) {
			ProductModel pds = new ProductModelDS();
			OrderModelDS ods = new OrderModelDS();
			PurchaseModelDS puds = new PurchaseModelDS();
			BillingModelDS bds = new BillingModelDS();
			boolean flag = true;
			int numOrder = 0;
			try {
				numOrder = ods.doSave(user);
				bill.setOrder(ods.doRetrieveByKey(numOrder));
				request.setAttribute("order", numOrder);
				bds.doSave(bill);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
			}
			for (PurchaseBean purchase : purchases) {
				try {
					ProductBean product = pds.doRetrieveByKey(purchase.getCode());
					product.setQuantity(product.getQuantity() - purchase.getNumItems());
					flag &= pds.doUpdate(product);
					purchase.setNumOrder(numOrder);
					puds.doSave(purchase);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (flag)
				request.setAttribute("ordine", true);
			else
				request.setAttribute("ordine", false);
			request.getSession().removeAttribute("cart");
		}
		RequestDispatcher rd = request.getRequestDispatcher("common/dispatch.jsp");
		rd.forward(request, response);
	}
}
