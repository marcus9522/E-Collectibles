package ordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.unisa.DriverManagerConnectionPool;
import prodotto.Cart;
import prodotto.ProductBean;
import prodotto.ProductModelDM;
import utente.UtenteBean;
import utente.UtenteModelDM;


public class OrdineModelDM implements OrdineModel {
	private static final String TABLE_NAME = "ordine";
	private static final String TABLE_NAME2 = "composto_da";

	public synchronized void doSave(OrdineBean ordine, Cart cart, ArrayList<Integer> pezzi) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		String table = "prodotto";
		String insertSQL = "INSERT INTO " + OrdineModelDM.TABLE_NAME
				+ " (EMAIL, COD_ORDINE, IMPORTO_TOT, DATA_ORD, MOD_PAGAMENTO, STATO, CORRIERE, TRACCIAMENTO, DATA_PARTENZA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String insertSQL2 = "INSERT INTO " + OrdineModelDM.TABLE_NAME2 + " (EMAIL, COD_ORDINE, COD_PRODOTTO, QUANTITA) VALUES(?, ?, ?, ?)" ;
		
		String updateSQL = " UPDATE " + table + " SET QUANTITA = ? " + " WHERE CODICE = ? "; ;
		try {
		    int i=0;
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, ordine.getEmail());
			preparedStatement.setInt(2, ordine.getCod_ordine());
			preparedStatement.setDouble(3, ordine.getImporto_tot());
			preparedStatement.setDate(4, ordine.getData_ord());
			preparedStatement.setString(5, ordine.getMod_pagamento());
			preparedStatement.setString(6, ordine.getStato());
			preparedStatement.setString(7, ordine.getCorriere());
			preparedStatement.setString(8, ordine.getTracciamento());
			preparedStatement.setDate(9, ordine.getData_partenza());
			preparedStatement.executeUpdate();
			List<ProductBean> prodcart=cart.getProducts();
	        for(ProductBean beancart:prodcart){
			preparedStatement2 = connection.prepareStatement(insertSQL2);
			preparedStatement3 = connection.prepareStatement(updateSQL);
			preparedStatement2.setString(1, ordine.getEmail());
			preparedStatement2.setInt(2, ordine.getCod_ordine());
			preparedStatement2.setInt(3, beancart.getCodice());
			preparedStatement2.setInt(4, 1);
			preparedStatement2.executeUpdate();
			preparedStatement3.setInt(1, pezzi.get(i));
			preparedStatement3.setInt(2, beancart.getCodice());
			preparedStatement3.executeUpdate();
			i++;
	        }
			
			//connection.commit();
		} finally {
			try {
				if ((preparedStatement != null) && (preparedStatement2 != null)){
					preparedStatement.close();
				    preparedStatement2.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized Collection<OrdineBean> leggiordini() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();
		String selectSQL = "SELECT * FROM " + OrdineModelDM.TABLE_NAME;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				OrdineBean bean = new OrdineBean();
				bean.setCod_ordine(Integer.valueOf(rs.getString("cod_ordine")));
				bean.setEmail(rs.getString("email"));
				bean.setCorriere(rs.getString("corriere"));
				bean.setMod_pagamento(rs.getString("mod_pagamento"));
				bean.setData_ord(rs.getDate("data_ord"));
				bean.setImporto_tot(Double.valueOf(rs.getString("importo_tot")));
				bean.setStato(rs.getString("stato"));
				bean.setTracciamento(rs.getString("tracciamento"));
				ordini.add(bean);
				}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}
	
	public void doUpdate(OrdineBean ordine) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = " UPDATE " + OrdineModelDM.TABLE_NAME + " SET STATO = ?, TRACCIAMENTO = ?, DATA_PARTENZA = ?" + " WHERE COD_ORDINE = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, ordine.getStato());
			preparedStatement.setString(2, ordine.getTracciamento());
			preparedStatement.setDate(3, ordine.getData_partenza());
			preparedStatement.setInt(4, ordine.getCod_ordine());
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
	
	public synchronized OrdineBean leggiordine(int cod) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OrdineBean bean = new OrdineBean();
		String selectSQL = "SELECT * FROM " + OrdineModelDM.TABLE_NAME + " WHERE COD_ORDINE = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, cod);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				bean.setCod_ordine(Integer.valueOf(rs.getString("cod_ordine")));
				bean.setEmail(rs.getString("email"));
				bean.setCorriere(rs.getString("corriere"));
				bean.setMod_pagamento(rs.getString("mod_pagamento"));
				bean.setData_ord(rs.getDate("data_ord"));
				bean.setData_partenza(rs.getDate("data_partenza"));
				bean.setImporto_tot(Double.valueOf(rs.getString("importo_tot")));
				bean.setStato(rs.getString("stato"));
				bean.setTracciamento(rs.getString("tracciamento"));
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
	
	
	public synchronized Collection<OrdineBean> leggiordine(String email) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();
		String selectSQL = "select * from prodotto,ordine,composto_da where prodotto.codice = composto_da.cod_prodotto and ordine.cod_ordine = composto_da.cod_ordine and ordine.email = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				OrdineBean bean = new OrdineBean();
				bean.setCod_ordine(Integer.valueOf(rs.getString("cod_ordine")));
				bean.setEmail(rs.getString("email"));
				bean.setCorriere(rs.getString("corriere"));
				bean.setMod_pagamento(rs.getString("mod_pagamento"));
				bean.setData_ord(rs.getDate("data_ord"));
				bean.setImporto_tot(Double.valueOf(rs.getString("importo_tot")));
				bean.setStato(rs.getString("stato"));
				bean.setData_partenza(rs.getDate("data_partenza"));
				bean.setTracciamento(rs.getString("tracciamento"));
				ordini.add(bean);
				}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}
	




}
