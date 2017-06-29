package recensione;

import java.sql.SQLException;
import java.util.Collection;

import recensione.RecensioneBean;;

public interface RecensioneModel {
	public Collection<RecensioneBean> recensioni(String code) throws SQLException;
	public void doSave(RecensioneBean recensione) throws SQLException;
	public  boolean check(String email, int cod) throws SQLException;
}
