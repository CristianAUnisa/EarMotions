package it.unisa.model;

/**
 * UserBean rappresenta un utente.
 * 
 * @author cristian
 *
 */
public class UserBean {
	
	public static enum Role {
		Amministratore,
		Cliente;
	}
	
	private String username;
	private String password;
	private Role role;
	private int id;
	private String email;
	
	/**
	 * Crea un utente con credenziali vuote.
	 */
	public UserBean() {
		username = "";
		password = "";
		role = null;
		id = 0;
		setEmail("");
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
}
