package recensione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import it.unisa.DriverManagerConnectionPool;

public class RecensioneModelDM implements RecensioneModel {
	private static final String TABLE_NAME = "recensione";

	
	public synchronized Collection<RecensioneBean> recensioni(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<RecensioneBean> recensioni = new LinkedList<RecensioneBean>();

		String selectSQL = "SELECT * FROM " + RecensioneModelDM.TABLE_NAME + " WHERE recensione.cod_prod = ? ";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
                bean.setValutazione(rs.getInt("VALUTAZIONE"));
                bean.setTesto(rs.getString("TESTO"));
                bean.setEmail(rs.getString("EMAIL"));
                bean.setCod(rs.getInt("COD_PROD"));
				recensioni.add(bean);
			}
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return recensioni;
	  }
	
	public synchronized void doSave(RecensioneBean recensione) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RecensioneModelDM.TABLE_NAME
				+ " (VALUTAZIONE, TESTO, EMAIL, COD_PROD) VALUES (?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, recensione.getValutazione());
			preparedStatement.setString(2, recensione.getTesto());
			preparedStatement.setString(3, recensione.getEmail());
			preparedStatement.setInt(4, recensione.getCod());
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
	public synchronized boolean check(String email,int cod) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT email FROM " + RecensioneModelDM.TABLE_NAME + " WHERE recensione.email = ? AND recensione.cod_prod = ? ";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setInt(2, cod);
			ResultSet rs = preparedStatement.executeQuery();
            if(rs.first()) return false;
            else return true;
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	  }
}
