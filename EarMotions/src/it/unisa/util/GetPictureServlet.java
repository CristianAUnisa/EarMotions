package it.unisa.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.ProductBean;
import it.unisa.model.ProductModel;
import it.unisa.model.ProductModelDS;
import it.unisa.model.PurchaseBean;
import it.unisa.model.PurchaseModelDS;

@WebServlet("/getPicture")
public class GetPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ProductModel model = new ProductModelDS();
	static PurchaseModelDS pmodel = new PurchaseModelDS();
	
	public GetPictureServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String numOrder = request.getParameter("numOrder");
		
		if (id != 0 || (numOrder != null && !numOrder.equals(""))) {
			ProductBean product = null;
			try {
				product = (ProductBean) model.doRetrieveByKey(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] bi = product.getPicture();
			ServletOutputStream out = response.getOutputStream();
			if (bi != null) {
				out.write(bi);
				response.setContentType("image/jpeg");
			}
			out.close();
		}
		else {
			PurchaseBean product = null;
			try {
				product = (PurchaseBean) pmodel.doRetrieveByKey(id, Integer.parseInt(numOrder));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] bi = product.getPicture();
			ServletOutputStream out = response.getOutputStream();
			if (bi != null) {
				out.write(bi);
				response.setContentType("image/jpeg");
			}
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
