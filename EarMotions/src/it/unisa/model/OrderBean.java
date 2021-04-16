package it.unisa.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Date;
import com.google.gson.Gson;

public class OrderBean {

	public static enum Status {
		ATTESA_PAGAMENTO, IN_PREPARAZIONE, SPEDITO, CONSEGNATO;
	}
	
	private int numOrder;
	private int user;
	private Timestamp date;
	private Status stato;
	private Collection<PurchaseBean> purchases;
	
	public OrderBean() {
		numOrder = 0;
		user = 0;
		date = new Timestamp(System.currentTimeMillis());
		purchases = new LinkedList<PurchaseBean>();
		stato = Status.ATTESA_PAGAMENTO;
	}
	
	/**
	 * @return the numOrder
	 */
	public int getNumOrder() {
		return numOrder;
	}

	/**
	 * @param numOrder the numOrder to set
	 */
	public void setNumOrder(int numOrder) {
		this.numOrder = numOrder;
	}

	/**
	 * @return the user
	 */
	public int getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(int user) {
		this.user = user;
	}

	/**
	 * @return the dateTimestamp
	 */
	public Timestamp getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Status getStato() {
		return stato;
	}

	public void setStato(Status stato) {
		this.stato = stato;
	}

	/**
	 * @return the purchases
	 */
	public Collection<PurchaseBean> getPurchases() {
		return purchases;
	}

	/**
	 * @param purchases the purchases to set
	 */
	public void setPurchases(Collection<PurchaseBean> purchases) {
		this.purchases = purchases;
	}
	
	public String getOrder() {
        return new Gson().toJson(this);
	}
	
	@Override
	public String toString() {
		return "OrderBean [numOrder=" + numOrder + ", user=" + user + ", date=" + date + ", stato=" + stato
				+ ", purchases=" + purchases + "]";
	}
}
