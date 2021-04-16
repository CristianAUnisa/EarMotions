package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// Gestione degli ordini nel database
public class OrderModelDS {

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

	private static final String TABLE_NAME = "orders";

	/*
	 * Salva l'ordine e ne ritorna il numero, 0 se errore.
	 */
	public synchronized int doSave(int user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int id = 0;
		String insertSQL = "INSERT INTO " + OrderModelDS.TABLE_NAME
				+ " (IDUSER) VALUES (?); ";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, user);

			preparedStatement.executeUpdate();
			connection.commit();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			id = rs.getInt("1");
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return id;
	}

	public synchronized OrderBean doRetrieveByKey(int numOrder) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderBean order = new OrderBean();
		
		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME + " WHERE numOrder = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, numOrder);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				order.setNumOrder(rs.getInt("numOrder"));
				order.setUser(rs.getInt("idUser"));
				order.setDate(rs.getTimestamp("Date"));
				order.setStato(OrderBean.Status.valueOf(rs.getString("Status")));
				PurchaseModelDS purchaseds = new PurchaseModelDS();
				order.setPurchases(purchaseds.doRetrieveByNumOrder(order.getNumOrder()));
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
		return order;
	}
	
	public synchronized Collection<OrderBean> doRetrieveByUserId(int idUser) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrderBean> orders = new LinkedList<OrderBean>();
		
		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME + " WHERE idUser = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idUser);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setNumOrder(rs.getInt("numOrder"));
				bean.setUser(rs.getInt("idUser"));
				bean.setDate(rs.getTimestamp("Date"));
				orders.add(bean);
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
		return orders;
	}

	public synchronized boolean doDelete(int numOrder) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrderModelDS.TABLE_NAME + " WHERE numOrder = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, numOrder);

			result = preparedStatement.executeUpdate();

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

	public synchronized Collection<OrderBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrderBean> orders = new LinkedList<OrderBean>();

		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setNumOrder(rs.getInt("numOrder"));
				bean.setUser(rs.getInt("idUser"));
				bean.setDate(rs.getTimestamp("Date"));
				orders.add(bean);
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
		return orders;
	}

	public boolean doUpdate(OrderBean order) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + OrderModelDS.TABLE_NAME
				+ " SET IDUSER = ?, DATE = ?, STATUS = ? "
				+ "WHERE NUMORDER = ?;";
		int result = 0;
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, order.getUser());
			preparedStatement.setTimestamp(2, Timestamp.from(order.getDate().toInstant()));
			preparedStatement.setString(3, order.getStato().name());
			preparedStatement.setInt(4, order.getNumOrder());

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

	public Collection<OrderBean> doRetrieveByParameters(String stato, Timestamp parsedInizio,
			Timestamp parsedFine, int idUser) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrderBean> orders = new LinkedList<OrderBean>();
		
		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME + " WHERE date > ? AND date < ? AND STATUS LIKE ? AND IDUSER LIKE ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setTimestamp(1, parsedInizio);
			preparedStatement.setTimestamp(2, parsedFine);
			if (stato == null || stato.equalsIgnoreCase("tutti"))
				preparedStatement.setString(3, "%");
			else
				preparedStatement.setString(3, "%" + stato + "%");
			if (idUser == 0)
				preparedStatement.setString(4, "%");
			else
				preparedStatement.setString(4, "" + idUser);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setNumOrder(rs.getInt("numOrder"));
				bean.setUser(rs.getInt("idUser"));
				bean.setDate(rs.getTimestamp("Date"));
				bean.setStato(OrderBean.Status.valueOf(rs.getString("Status")));
				orders.add(bean);
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
		return orders;
	}
}
