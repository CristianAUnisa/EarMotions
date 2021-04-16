package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BillingModelDS  {

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/ecommerce");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "billing";
	
	public synchronized void doSave(BillingBean bill) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + BillingModelDS.TABLE_NAME
				+ " (NUMORDER, NAME, SURNAME, PHONENUMBER, EMAIL, CITY, ADDRESS, REGIONE, PROVINCIA, CAP, PAGAMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, bill.getOrder().getNumOrder());
			preparedStatement.setString(2, bill.getName());
			preparedStatement.setString(3, bill.getSurname());
			preparedStatement.setString(4, bill.getNumtelefono());
			preparedStatement.setString(5, bill.getEmail());
			preparedStatement.setString(6, bill.getCitta());
			preparedStatement.setString(7, bill.getIndirizzo());
			preparedStatement.setString(8, bill.getRegione());
			preparedStatement.setString(9, bill.getProvincia());
			preparedStatement.setString(10, bill.getCap());
			preparedStatement.setString(11, bill.getPagamento());
			preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	public BillingBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		BillingBean bean = new BillingBean();

		String selectSQL = "SELECT * FROM " + BillingModelDS.TABLE_NAME + " WHERE IDBILL = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			OrderModelDS ods = new OrderModelDS();
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdBill(rs.getInt("IDBILL"));
				bean.setOrder(ods.doRetrieveByKey(rs.getInt("NUMORDER")));
				bean.setCitta(rs.getString("CITY"));
				bean.setRegione(rs.getString("REGIONE"));
				bean.setProvincia(rs.getString("PROVINCIA"));
				bean.setIndirizzo(rs.getString("ADDRESS"));
				bean.setCap(rs.getString("CAP"));
				bean.setNumtelefono(rs.getString("PHONENUMBER"));
				bean.setName(rs.getString("NAME"));
				bean.setSurname(rs.getString("SURNAME"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPagamento(rs.getString("PAGAMENTO"));
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	public Collection<BillingBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<BillingBean> bills = new LinkedList<BillingBean>();

		String selectSQL = "SELECT * FROM " + BillingModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
			ResultSet rs = preparedStatement.executeQuery();

			OrderModelDS ods = new OrderModelDS();

			while (rs.next()) {
				BillingBean bean = new BillingBean();
				bean.setIdBill(rs.getInt("IDBILL"));
				bean.setOrder(ods.doRetrieveByKey(rs.getInt("NUMORDER")));
				bean.setCitta(rs.getString("CITY"));
				bean.setRegione(rs.getString("REGIONE"));
				bean.setProvincia(rs.getString("PROVINCIA"));
				bean.setIndirizzo(rs.getString("ADDRESS"));
				bean.setCap(rs.getString("CAP"));
				bean.setNumtelefono(rs.getString("PHONENUMBER"));
				bean.setName(rs.getString("NAME"));
				bean.setSurname(rs.getString("SURNAME"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPagamento(rs.getString("PAGAMENTO"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bills;
	}

	public boolean doUpdate(BillingBean bill) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + BillingModelDS.TABLE_NAME
				+ " SET IDBILL = ?, NUMORDER = ?, NAME = ?, SURNAME = ?, PHONENUMBER = ?,"
				+ " EMAIL = ?, CITY = ?, ADDRESS = ?, REGIONE = ?, PROVINCIA = ?, CAP = ?,"
				+ " PAGAMENTO = ?"
				+ " WHERE IDBILL = ?";
		int result = 0;
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, bill.getIdBill());
			preparedStatement.setInt(2, bill.getOrder().getNumOrder());
			preparedStatement.setString(3, bill.getName());
			preparedStatement.setString(4, bill.getSurname());
			preparedStatement.setString(5, bill.getNumtelefono());
			preparedStatement.setString(6, bill.getEmail());
			preparedStatement.setString(7, bill.getCitta());
			preparedStatement.setString(8, bill.getIndirizzo());
			preparedStatement.setString(9, bill.getRegione());
			preparedStatement.setString(10, bill.getProvincia());
			preparedStatement.setString(11, bill.getCap());
			preparedStatement.setString(12, bill.getPagamento());
			preparedStatement.setInt(13, bill.getIdBill());
			

			result = preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	public BillingBean doRetrieveByNumOrder(int numOrder) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BillingBean bean = new BillingBean();

		String selectSQL = "SELECT * FROM " + BillingModelDS.TABLE_NAME + " WHERE numOrder = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, numOrder);
			OrderModelDS ods = new OrderModelDS();
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdBill(rs.getInt("IDBILL"));
				bean.setOrder(ods.doRetrieveByKey(rs.getInt("NUMORDER")));
				bean.setCitta(rs.getString("CITY"));
				bean.setRegione(rs.getString("REGIONE"));
				bean.setProvincia(rs.getString("PROVINCIA"));
				bean.setIndirizzo(rs.getString("ADDRESS"));
				bean.setCap(rs.getString("CAP"));
				bean.setNumtelefono(rs.getString("PHONENUMBER"));
				bean.setName(rs.getString("NAME"));
				bean.setSurname(rs.getString("SURNAME"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPagamento(rs.getString("PAGAMENTO"));
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
}
