package it.unisa.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PurchaseModelDS {

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

	private static final String TABLE_NAME = "purchase";

	public synchronized void doSave(PurchaseBean order) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PurchaseModelDS.TABLE_NAME
				+ " (numorder, code, purchasedquantity, price, iva, name, description, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, order.getNumOrder());
			preparedStatement.setInt(2, order.getCode());
			preparedStatement.setInt(3, order.getNumItems());
			preparedStatement.setBigDecimal(4, order.getPrice());
			preparedStatement.setShort(5, order.getIva());
			preparedStatement.setString(6, order.getName());
			preparedStatement.setString(7, order.getDescription());
			if (order.getPicture() != null) {
				Blob blob = new javax.sql.rowset.serial.SerialBlob(order.getPicture());
				preparedStatement.setBlob(8, blob);
			}
			else
				preparedStatement.setNull(8, Types.BLOB);
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

	public synchronized PurchaseBean doRetrieveByKey(int code, int numOrder) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		PurchaseBean order = new PurchaseBean();
		
		String selectSQL = "SELECT * FROM " + PurchaseModelDS.TABLE_NAME + " WHERE CODE = ? AND numorder = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			preparedStatement.setInt(2, numOrder);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				order.setCode(rs.getInt("CODE"));
				order.setNumItems(rs.getInt("PURCHASEDQUANTITY"));
				order.setPrice(rs.getBigDecimal("PRICE"));
				order.setDescription(rs.getString("DESCRIPTION"));
				try {
					order.setIva(rs.getShort("IVA"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				order.setName(rs.getString("NAME"));
				InputStream is = rs.getBinaryStream("PHOTO");
				if (is != null) {
					try {
						order.setPicture(is.readAllBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
	
	public synchronized Collection<PurchaseBean> doRetrieveByNumOrder(int numOrder) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		String selectSQL = "SELECT * FROM " + PurchaseModelDS.TABLE_NAME + " WHERE numorder = ?";
		LinkedList<PurchaseBean> purchases = null;
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, numOrder);
			purchases = new LinkedList<PurchaseBean>();
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PurchaseBean order = new PurchaseBean();
				order.setCode(rs.getInt("code"));
				order.setNumItems(rs.getInt("purchasedquantity"));
				order.setPrice(rs.getBigDecimal("price"));
				order.setName(rs.getString("name"));
				order.setDescription(rs.getString("description"));
				order.setNumOrder(rs.getInt("numOrder"));
				InputStream is = rs.getBinaryStream("PHOTO");
				if (is != null) {
					try {
						order.setPicture(is.readAllBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				purchases.add(order);
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
		return purchases;
	}

	public synchronized boolean doDelete(int numOrder, int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + PurchaseModelDS.TABLE_NAME + " WHERE numOrder = ? AND code = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, numOrder);
			preparedStatement.setInt(2, code);

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

	public synchronized Collection<PurchaseBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PurchaseBean> orders = new LinkedList<PurchaseBean>();

		String selectSQL = "SELECT * FROM " + PurchaseModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PurchaseBean bean = new PurchaseBean();
				bean.setCode(rs.getInt("code"));
				bean.setNumItems(rs.getInt("purchasedquantity"));
				bean.setPrice(rs.getBigDecimal("price"));
				bean.setName(rs.getString("name"));
				bean.setDescription(rs.getString("description"));
				bean.setNumOrder(rs.getInt("numOrder"));
				InputStream is = rs.getBinaryStream("PHOTO");
				if (is != null) {
					try {
						bean.setPicture(is.readAllBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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

	public boolean doUpdate(PurchaseBean order) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + PurchaseModelDS.TABLE_NAME
				+ " SET PRICE = ?, PURCHASEDQUANTITY = ?, IVA = ?, NAME = ?, DESCRIPTION = ?, PHOTO = ?"
				+ " WHERE CODE = ? AND numOrder = ?;";
		int result = 0;
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setBigDecimal(1, order.getPrice());
			preparedStatement.setInt(2, order.getNumItems());
			preparedStatement.setShort(3, order.getIva());
			preparedStatement.setString(4, order.getName());
			preparedStatement.setString(5, order.getDescription());
			if (order.getPicture() != null) {
				InputStream is = new ByteArrayInputStream(order.getPicture());
				preparedStatement.setBlob(6, is);
			}
			else
				preparedStatement.setNull(6, java.sql.Types.BLOB);
			preparedStatement.setInt(7, order.getCode());
			preparedStatement.setInt(8, order.getNumOrder());

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
	
}