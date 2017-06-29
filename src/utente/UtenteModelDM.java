package utente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import it.unisa.DriverManagerConnectionPool;
import prodotto.ProductBean;
import prodotto.ProductModelDM;


public class UtenteModelDM implements UtenteModel {
	private static final String TABLE_NAME = "utente";

	public synchronized void doSave(UtenteBean utente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UtenteModelDM.TABLE_NAME
				+ " (EMAIL, PASSWORD, RUOLO, COD_FISCALE, NOME, COGNOME, DATA_NASCITA, TEL1, TEL2, CAP, VIA, CIVICO, CITTA, PROVINCIA, NAZIONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getEmail());
			preparedStatement.setString(2, utente.getPassword());
			preparedStatement.setString(3, utente.getRuolo());
			preparedStatement.setString(4, utente.getCod_fiscale());
			preparedStatement.setString(5, utente.getNome());
			preparedStatement.setString(6, utente.getCognome());
			preparedStatement.setDate(7, utente.getData_nascita());
			preparedStatement.setString(8, utente.getTel1());
			preparedStatement.setString(9, utente.getTel2());
			preparedStatement.setInt(10, utente.getCap());
			preparedStatement.setString(11, utente.getVia());
			preparedStatement.setInt(12, utente.getCivico());
			preparedStatement.setString(13, utente.getCitta());
			preparedStatement.setString(14, utente.getProvincia());
			preparedStatement.setString(15, utente.getNazione());
            
			preparedStatement.executeUpdate();

			//connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized String Email(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UtenteBean> Email = new LinkedList<UtenteBean>();

		String selectSQL = "SELECT * FROM " + UtenteModelDM.TABLE_NAME;
        String found="false";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UtenteBean bean = new UtenteBean();

				bean.setEmail(rs.getString("EMAIL"));
				Email.add(bean);
			}
			if (Email != null && Email.size() != 0) {
				Iterator<UtenteBean> it = Email.iterator();
				while (it.hasNext()) {
					UtenteBean bean = (UtenteBean) it.next();
					if(bean.getEmail().equals(email)){found="true";
					                                  break;
					                                 }
		}
			}
		}
		
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return found;
	  }
	
	public synchronized String login(String email,String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UtenteBean> user = new LinkedList<UtenteBean>();

		String selectSQL = "SELECT * FROM " + UtenteModelDM.TABLE_NAME;
        String found="false";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UtenteBean bean = new UtenteBean();
                
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setRuolo(rs.getString("RUOLO"));
				user.add(bean);
			}
			if (user != null && user.size() != 0) {
				Iterator<UtenteBean> it = user.iterator();
				while (it.hasNext()) {
					UtenteBean bean = (UtenteBean) it.next();
					if((bean.getEmail().equals(email))&&(bean.getPassword().equals(password))){found="true";
					                                                                          if(bean.getRuolo().equals("amministratore")) found="admin";
					                                                                           break;
					                                                                          }
		}
			}
		}
		
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return found;
	  }
	
	public synchronized boolean doDelete(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UtenteModelDM.TABLE_NAME + " WHERE EMAIL = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}
	
	public void doUpdate(UtenteBean utente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = " UPDATE " + UtenteModelDM.TABLE_NAME + " SET PASSWORD = ?, RUOLO = ?, COD_FISCALE = ?, NOME = ?, COGNOME = ?, DATA_NASCITA = ?, TEL1 = ?, TEL2 = ?, CAP = ?, VIA = ?, CIVICO = ?, CITTA = ?, PROVINCIA = ?, NAZIONE = ?" + " WHERE EMAIL = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, utente.getPassword());
			preparedStatement.setString(2, utente.getRuolo());
			preparedStatement.setString(3, utente.getCod_fiscale());
			preparedStatement.setString(4, utente.getNome());
			preparedStatement.setString(5, utente.getCognome());
			preparedStatement.setDate(6, utente.getData_nascita());
			preparedStatement.setString(7, utente.getTel1());
			preparedStatement.setString(8, utente.getTel2());
			preparedStatement.setInt(9, utente.getCap());
			preparedStatement.setString(10, utente.getVia());
			preparedStatement.setInt(11, utente.getCivico());
			preparedStatement.setString(12, utente.getCitta());
			preparedStatement.setString(13, utente.getProvincia());
			preparedStatement.setString(14, utente.getNazione());
			preparedStatement.setString(15, utente.getEmail());

			preparedStatement.executeUpdate();

			//connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	 public synchronized Collection<UtenteBean> LeggiUtenti() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<UtenteBean> utenti = new LinkedList<UtenteBean>();
		String selectSQL = "SELECT * FROM " + UtenteModelDM.TABLE_NAME;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
		        UtenteBean bean = new UtenteBean();
                bean.setEmail(rs.getString("EMAIL"));
                bean.setPassword(rs.getString("PASSWORD"));
                bean.setRuolo(rs.getString("RUOLO"));
                bean.setCod_fiscale(rs.getString("COD_FISCALE"));
                bean.setNome(rs.getString("NOME"));
                bean.setCognome(rs.getString("COGNOME"));
                bean.setData_nascita(rs.getDate("DATA_NASCITA"));
                bean.setTel1(rs.getString("TEL1"));
                bean.setTel2(rs.getString("TEL2"));
                bean.setCap(rs.getInt("CAP"));
                bean.setVia(rs.getString("VIA"));
                bean.setCivico(rs.getInt("CIVICO"));
                bean.setCitta(rs.getString("CITTA"));
                bean.setProvincia(rs.getString("PROVINCIA"));
                bean.setNazione(rs.getString("NAZIONE"));
                utenti.add(bean);
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utenti;
	}
	 
	 public synchronized UtenteBean LeggiUtente(String email) throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        UtenteBean bean = new UtenteBean();
			String selectSQL = "SELECT * FROM " + UtenteModelDM.TABLE_NAME + " WHERE EMAIL = ? ";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, email);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
	                bean.setEmail(rs.getString("EMAIL"));
	                bean.setPassword(rs.getString("PASSWORD"));
	                bean.setRuolo(rs.getString("RUOLO"));
	                bean.setCod_fiscale(rs.getString("COD_FISCALE"));
	                bean.setNome(rs.getString("NOME"));
	                bean.setCognome(rs.getString("COGNOME"));
	                bean.setData_nascita(rs.getDate("DATA_NASCITA"));
	                bean.setTel1(rs.getString("TEL1"));
	                bean.setTel2(rs.getString("TEL2"));
	                bean.setCap(rs.getInt("CAP"));
	                bean.setVia(rs.getString("VIA"));
	                bean.setCivico(rs.getInt("CIVICO"));
	                bean.setCitta(rs.getString("CITTA"));
	                bean.setProvincia(rs.getString("PROVINCIA"));
	                bean.setNazione(rs.getString("NAZIONE"));
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return bean;
		}
}
