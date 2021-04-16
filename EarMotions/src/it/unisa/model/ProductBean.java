package it.unisa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	static final short DEFAULTIVA = 22;
	
	private int code;
	private String name;
	private String description;
	private BigDecimal price;
	private short iva;
	private int quantity;
	private transient byte[] picture;
	private String category;

	public ProductBean() {
		code = -1;
		name = "";
		description = "";
		quantity = 0;
		picture = null;
		price = BigDecimal.ZERO;
	    iva = DEFAULTIVA;
	    setCategory("");
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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
	 * @throws Exception 
	 */
	public void setIva(short iva) throws Exception {
		if (iva < 0 || iva > 100) throw new Exception("Iva non valida");
		this.iva = iva;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	public byte[] getPicture() {
		return picture;
	}
	
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		return name + " (" + code + "), " + price + " " + quantity + ". " + description;
	}
	
	public BigDecimal getTaxedPrice() {
		double iva = this.iva;
		return price.add(price.multiply(new BigDecimal (iva / 100))).setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
}
