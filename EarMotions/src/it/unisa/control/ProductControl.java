package it.unisa.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import it.unisa.model.ProductModel;
import it.unisa.model.ProductModelDS;
import it.unisa.model.Cart;
import it.unisa.model.ProductBean;

/**
 * Servlet implementation class ProductControl
 */
@MultipartConfig
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static String SAVE_DIR = "/uploadTemp";

	static ProductModelDS model = new ProductModelDS();

	public ProductControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		// Decide quale azione intraprendere
		String action = request.getParameter("action");
		String role = (String) request.getSession().getAttribute("role");

		try {
			if (role == null || !role.equalsIgnoreCase("amministratore")) {
				userActions(action, cart, request, response);
			}
			else if (role.equalsIgnoreCase("amministratore")) {
				adminActions(action, cart, request, response);
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private void adminActions(String action, Cart cart, HttpServletRequest request, HttpServletResponse response) throws SQLException, FileNotFoundException, IOException, ServletException {
		// TODO Auto-generated method stub
		if (action != null) {
			if (action.equalsIgnoreCase("update")) {
				ProductBean uBean = new ProductBean();
	
				uBean.setCode(Integer.parseInt(request.getParameter("id")));
				uBean.setName(request.getParameter("name"));
				uBean.setDescription(request.getParameter("description"));
				uBean.setPrice(new BigDecimal(Double.parseDouble(request.getParameter("price"))));
				uBean.setQuantity(Integer.parseInt(request.getParameter("quantity")));
				model.doUpdate(uBean);
	
				int id = Integer.parseInt(request.getParameter("id"));
				request.removeAttribute("product");
				request.setAttribute("product", model.doRetrieveByKey(id));
			    response.setStatus(HttpServletResponse.SC_OK);
			} else if (action.equalsIgnoreCase("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.removeAttribute("product");
				request.setAttribute("product", model.doRetrieveByKey(id));
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ProductEdit.jsp");
				rd.forward(request, response);
				return;	
			
			} else if (action.equalsIgnoreCase("read")) { // Dettagli del prodotto
				int id = Integer.parseInt(request.getParameter("id"));
				request.removeAttribute("product");
				request.setAttribute("product", model.doRetrieveByKey(id));
			} else if (action.equalsIgnoreCase("delete")) {
						int id = Integer.parseInt(request.getParameter("id"));
						model.doDelete(id);
					    response.setStatus(HttpServletResponse.SC_OK);
			} else if (action.equalsIgnoreCase("readall")) {
				Collection<ProductBean> products = model.doRetrieveAll(null);
				response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write(new Gson().toJson(products));
		        return;
			} else if (action.equalsIgnoreCase("insert")) {
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				BigDecimal price = new BigDecimal(Double.parseDouble(request.getParameter("price")));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				int iva = Integer.parseInt(request.getParameter("iva"));
				String category = request.getParameter("category");
				ProductBean bean = new ProductBean();
				bean.setName(name);
				bean.setDescription(description);
				bean.setPrice(price);
				bean.setQuantity(quantity);
				bean.setCategory(category);
				try {
					bean.setIva((short) iva);
				} catch (Exception e) {
					// IVA resta 22%
				}
				if (request.getParts() != null) {
					String appPath = request.getServletContext().getRealPath("");
					String savePath = appPath + File.separator + SAVE_DIR;
					System.out.println(savePath);
	
					File fileSaveDir = new File(savePath);
					if (!fileSaveDir.exists()) {
						fileSaveDir.mkdir();
					}
	
					for (Part part : request.getParts()) {
						String fileName = extractFileName(part);
						if (fileName != null && !fileName.equals("")) {
							part.write(savePath + File.separator + fileName);
							FileInputStream file = new FileInputStream(savePath + File.separator + fileName);
							bean.setPicture(file.readAllBytes());
							file.close();
						}
					}
				}
				model.doSave(bean);
			    response.setStatus(HttpServletResponse.SC_OK); 
			}
		}
		response.sendRedirect(request.getContextPath() + "/admin/ControlPanel.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Crea l'oggetto carrello se non presente.
		doGet(request, response);
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	
	private void userActions(String action, Cart cart, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		if (action != null) {
			if (action.equalsIgnoreCase("addC")) { // Aggiungi al carrello
				int id = Integer.parseInt(request.getParameter("id"));
				int quantity = 1;
				if (request.getParameter("quantity") != null)
					quantity = Integer.parseInt(request.getParameter("quantity"));
				ProductBean product = (ProductBean) model.doRetrieveByKey(id);
				while (quantity > 0) {
					cart.addProduct(product);
					quantity--;
				}
			} else if (action.equalsIgnoreCase("deleteC")) { // Elimina dal carrello
				int id = Integer.parseInt(request.getParameter("id"));
				cart.deleteProduct(model.doRetrieveByKey(id));
			} else if (action.equalsIgnoreCase("delCart")) { // Elimina dal carrello
				cart.emptyCart();
			} else if (action.equalsIgnoreCase("read")) { // Dettagli del prodotto
				int id = Integer.parseInt(request.getParameter("id"));
				request.removeAttribute("product");
				request.setAttribute("product", model.doRetrieveByKey(id));
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/product.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}

		request.getSession().setAttribute("cart", cart);

		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("products");
			String category = request.getParameter("category");
			if (category == null || category.equals("")) {
				request.setAttribute("products", model.doRetrieveAll(sort));
			} else {
				request.setAttribute("products", model.doRetrieveByCategory(category));
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}
