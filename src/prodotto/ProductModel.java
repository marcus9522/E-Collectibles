package prodotto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface ProductModel {
	public void doSave(ProductBean product) throws SQLException;

	public void doUpdate(ProductBean product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public ProductBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException;
	
	public int saveLista(String email,int cod) throws SQLException;
	
	public ArrayList<Integer> leggiLista(String email) throws SQLException;
	
	public boolean doDeletelist(int code) throws SQLException;
	
	public boolean checklist(int code,String email) throws SQLException; 
	
	public boolean checkprodotto(int code) throws SQLException; 
	
	public int quantita(int cod) throws SQLException;
	
	public Collection<ProductBean> leggiprodotto(String email) throws SQLException;
	
	public Collection<ProductBean> cerca(String name) throws SQLException;
	
	public Collection<ProductBean> filtra(String order,double min,double max,int offerta) throws SQLException;
	
	public Collection<ProductBean> categoria (String categoria) throws SQLException;
}
