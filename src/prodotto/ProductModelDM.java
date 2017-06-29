package prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import it.unisa.DriverManagerConnectionPool;
import ordine.OrdineBean;

public class ProductModelDM implements ProductModel {

	private static final String TABLE_NAME = "prodotto";

	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModelDM.TABLE_NAME
				+ " (CODICE, NOME, PREZZO, CATEGORIA, DESCRIZIONE, QUANTITA, OFFERTA , FOTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getCodice());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setDouble(3, product.getPrezzo());
			preparedStatement.setString(4, product.getCategoria());
			preparedStatement.setString(5, product.getDescrizione());
			preparedStatement.setInt(6, product.getQuantita());
			preparedStatement.setInt(7, product.getOfferta());
			preparedStatement.setString(8, product.getFoto());

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

	@Override
	public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME + " WHERE CODICE = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getString("CATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setPrezzo(rs.getDouble("PREZZO"));
				bean.setQuantita(rs.getInt("QUANTITA"));
				bean.setFoto(rs.getString("FOTO"));
				bean.setOfferta(rs.getInt("OFFERTA"));
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

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductModelDM.TABLE_NAME + " WHERE CODICE = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

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
	

	@Override
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getString("CATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setPrezzo(rs.getDouble("PREZZO"));
				bean.setQuantita(rs.getInt("QUANTITA"));
				bean.setFoto(rs.getString("FOTO"));
				bean.setOfferta(rs.getInt("OFFERTA"));
				products.add(bean);
			}
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	  }
		
	
	public void doUpdate(ProductBean product) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = " UPDATE " + ProductModelDM.TABLE_NAME + " SET CODICE = ?, NOME = ?, PREZZO = ?, CATEGORIA = ? , DESCRIZIONE = ? , QUANTITA = ? , OFFERTA = ? , FOTO = ?" + " WHERE CODICE = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1,product.getCodice());
			preparedStatement.setString(2,product.getNome());
			preparedStatement.setDouble(3,product.getPrezzo());
			preparedStatement.setString(4,product.getCategoria());
			preparedStatement.setString(5,product.getDescrizione());
			preparedStatement.setInt(6,product.getQuantita());
			preparedStatement.setInt(7,product.getOfferta());
			preparedStatement.setString(8,product.getFoto());
			preparedStatement.setInt(9,product.getCodice());

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
	
	public synchronized int saveLista(String email,int cod) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
        String table="lista_personale";
		String insertSQL = "INSERT INTO " + table
				+ " (EMAIL, COD_PROD) VALUES (?, ?)";
        int i;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setDouble(2, cod);
            i= preparedStatement.executeUpdate();
            connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return i;
	}
	
	public synchronized ArrayList<Integer> leggiLista(String email) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
        String table="lista_personale";
        ArrayList<Integer> codes = new ArrayList<Integer>();
		String insertSQL = "Select * FROM " + table
				+ " where email= ? ";

		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()){
				codes.add(rs.getInt(2));
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return codes;
	}
	
	public synchronized boolean doDeletelist(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String table="lista_personale";
		int result = 0;

		String deleteSQL = "DELETE FROM " + table + " WHERE COD_PROD = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

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
	
	
	public synchronized boolean checklist(int code,String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String table="lista_personale";

		String selectSQL = "SELECT email,cod_prod FROM  " + table + " WHERE COD_PROD = ? AND EMAIL = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			preparedStatement.setString(2, email);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.first()) return true;
			else return false;

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized boolean checkprodotto(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM  "  + ProductModelDM.TABLE_NAME + " WHERE CODICE = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.first()) return true;
			else return false;

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized int quantita(int cod) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT quantita FROM "  + ProductModelDM.TABLE_NAME + " WHERE CODICE = ?";
        int i=0;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, cod);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				i = rs.getInt("quantita");
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return i;
	}
	
	
	public synchronized Collection<ProductBean> leggiprodotto(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ProductBean> prodotti = new LinkedList<ProductBean>();

		String selectSQL = "select * from prodotto,ordine,composto_da where prodotto.codice = composto_da.cod_prodotto and ordine.cod_ordine = composto_da.cod_ordine and ordine.email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getString("CATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setPrezzo(rs.getDouble("PREZZO"));
				bean.setQuantita(rs.getInt("QUANTITA"));
				bean.setFoto(rs.getString("FOTO"));
				bean.setOfferta(rs.getInt("OFFERTA"));
				prodotti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return prodotti;
	}
	
	public synchronized Collection<ProductBean> cerca (String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ProductBean> prodotti = new LinkedList<ProductBean>();
		String selectSQL = "select * from prodotto where nome like '%" + name + "%' ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//preparedStatement.setString(1, a);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getString("CATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setPrezzo(rs.getDouble("PREZZO"));				
				bean.setQuantita(rs.getInt("QUANTITA"));
				bean.setFoto(rs.getString("FOTO"));
				bean.setOfferta(rs.getInt("OFFERTA"));
				prodotti.add(bean);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return prodotti;
	}
	
	public synchronized Collection<ProductBean> filtra(String order,double min,double max,int offerta) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        boolean first=true;
		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME;
		if (min != 0) {
			if(first==true){selectSQL += " WHERE ";
                            first=false;			 
			                }
			else selectSQL+= " AND ";
			selectSQL += " prezzo>= " + min;
		}
		if (max != 0) {
			if(first==true){selectSQL += " WHERE ";
                            first=false;			 
			                }
			else selectSQL+= " AND ";
			selectSQL += " prezzo<= " + max;
		}
		if ((offerta == 0)||(offerta == 1)) {
			if(first==true){selectSQL += " WHERE ";
                            first=false;			 
			                }
			else selectSQL+= " AND ";
			selectSQL += " offerta = " + offerta;
		}
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getString("CATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setPrezzo(rs.getDouble("PREZZO"));
				bean.setQuantita(rs.getInt("QUANTITA"));
				bean.setFoto(rs.getString("FOTO"));
				bean.setOfferta(rs.getInt("OFFERTA"));
				products.add(bean);
			}
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	  }
	
	
	public synchronized Collection<ProductBean> categoria (String categoria) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ProductBean> prodotti = new LinkedList<ProductBean>();
		String selectSQL = "select * from prodotto where categoria = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, categoria);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setCodice(rs.getInt("CODICE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getString("CATEGORIA"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setPrezzo(rs.getDouble("PREZZO"));				
				bean.setQuantita(rs.getInt("QUANTITA"));
				bean.setFoto(rs.getString("FOTO"));
				bean.setOfferta(rs.getInt("OFFERTA"));
				prodotti.add(bean);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return prodotti;
	}
	}
