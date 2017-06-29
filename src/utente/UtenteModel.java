package utente;

import java.sql.SQLException;
import java.util.Collection;


public interface UtenteModel {
	public void doSave (UtenteBean utente) throws SQLException ;
	
	public String Email(String email) throws SQLException ;
	
	public String login(String email,String password) throws SQLException;
	
	public void doUpdate(UtenteBean utente) throws SQLException;
	
	public boolean doDelete(String email) throws SQLException;
	
	public Collection<UtenteBean> LeggiUtenti() throws SQLException;
	
	public UtenteBean LeggiUtente(String email) throws SQLException;
}
