package ordine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import prodotto.Cart;

public interface OrdineModel {
	public  void doSave(OrdineBean ordine, Cart cart, ArrayList<Integer> pezzi) throws SQLException;

	public Collection<OrdineBean> leggiordini() throws SQLException;
	
	public OrdineBean leggiordine(int cod) throws SQLException;
	
	public void doUpdate(OrdineBean ordine) throws SQLException;
	
	public Collection<OrdineBean> leggiordine(String email) throws SQLException;
}
