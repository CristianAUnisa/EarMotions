package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

/**
 *  Un modello per eseguire operazioni sul database contenente i dati degli utenti (UserBean).
 *
 */
public interface UserModel {
	public void doSave(UserBean user) throws SQLException;

	public boolean doDelete(String username) throws SQLException;

	public UserBean doRetrieveByKey(int id) throws SQLException;
	
	public UserBean doRetrieveByUsername(String username) throws SQLException;
	
	public Collection<UserBean> doRetrieveAll(String order) throws SQLException;
	
	public boolean doUpdate(UserBean user) throws SQLException;

}
