package it.unisa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Associates a catalog Item with a specific order by keeping track of the
 * number ordered and the total price. Also provides some convenience methods to
 * get at the CatalogItem data without extracting the CatalogItem separately.
 * <P>
 * Taken from Core Servlets and JavaServer Pages 2nd Edition from Prentice Hall
 * and Sun Microsystems Press, http://www.coreservlets.com/. &copy; 2003 Marty
 * Hall; may be freely used or adapted.
 */

public class PurchaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int numOrder;
	private String name;
	private int code;
	private int numItems;
	private BigDecimal price;
	private String description = null;
	private short iva;
	transient byte[] picture;
	
	static final short DEFAULTIVA = 22;

	public PurchaseBean() {
		numOrder = 0;
		code = 0;
		numItems = 1;
		price = (BigDecimal.ZERO);
		iva = DEFAULTIVA;
		picture = null;
	}

	public PurchaseBean(ProductBean item) {
		setCode(item.getCode());
		setName(item.getName());
		setNumItems(1);
		setPrice(item.getPrice());
		setDescription(item.getDescription());
		try {
			setIva(item.getIva());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getCode() {
		return (code);
	}

	protected void setCode(int code) {
		this.code = code;
	}

	public BigDecimal getPrice() {
		return price.setScale(2, RoundingMode.HALF_UP);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the iva
	 */
	public short getIva() {
		return iva;
	}

	/**
	 * @param iva the iva to set
	 */
	public void setIva(short iva) throws Exception {
		if (iva < 0 || iva > 100) throw new Exception("Iva non valida");
		this.iva = iva;
	}

	public int getNumItems() {
		return (numItems);
	}

	public void setNumItems(int n) {
		this.numItems = n;
	}

	public void incrementNumItems() {
		setNumItems(getNumItems() + 1);
	}

	public void cancelOrder() {
		setNumItems(0);
	}

	public BigDecimal getTotalCost() {
		return getPrice().multiply(new BigDecimal(getNumItems()));
	}

	public int getNumOrder() {
		return numOrder;
	}

	public void setNumOrder(int numOrder) {
		this.numOrder = numOrder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getTaxedPrice() {
		double iva = this.iva;
		return (price.add(price.multiply(new BigDecimal (iva / 100)))).setScale(2, RoundingMode.HALF_UP);
	}
	
	public byte[] getPicture() {
		return picture;
	}
	
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "PurchaseBean [numOrder=" + numOrder + ", name=" + name + ", code=" + code + ", numItems=" + numItems
				+ ", price=" + price + ", description=" + description + ", iva=" + iva + "]";
	}
}