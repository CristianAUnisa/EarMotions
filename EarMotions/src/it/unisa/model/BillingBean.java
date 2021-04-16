package it.unisa.model;

public class BillingBean {
	
	private String name;
	private String surname;
	private String email;
	private String regione;
	private String provincia;
	private String citta;
	private String indirizzo;
	private String numtelefono;
	private String cap;
	private OrderBean order;
	private int IdBill;
	private String pagamento;
	
	public BillingBean() {
		surname = null;
		name = null;
		email = null;
		regione = null;
		provincia = null;
		citta = null;
		indirizzo = null;
		cap = null;
		setOrder(null);
		setIdBill(0);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the regione
	 */
	public String getRegione() {
		return regione;
	}

	/**
	 * @param regione the regione to set
	 */
	public void setRegione(String regione) {
		this.regione = regione;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the citta
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * @param citta the citta to set
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return the numtelefono
	 */
	public String getNumtelefono() {
		return numtelefono;
	}

	/**
	 * @param numtelefono the numtelefono to set
	 */
	public void setNumtelefono(String numtelefono) {
		this.numtelefono = numtelefono;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * @return the order
	 */
	public OrderBean getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(OrderBean order) {
		this.order = order;
	}

	/**
	 * @return the IdBill
	 */
	public int getIdBill() {
		return IdBill;
	}

	/**
	 * @param IdBill the IdBill to set
	 */
	public void setIdBill(int IdBill) {
		this.IdBill = IdBill;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getPagamento() {
		return pagamento;
	}

	@Override
	public String toString() {
		return "BillingBean [name=" + name + ", surname=" + surname + ", email=" + email + ", regione=" + regione
				+ ", provincia=" + provincia + ", citta=" + citta + ", indirizzo=" + indirizzo + ", numtelefono="
				+ numtelefono + ", cap=" + cap + ", order=" + order + ", IdBill=" + IdBill + ", pagamento=" + pagamento
				+ "]";
	}
}
