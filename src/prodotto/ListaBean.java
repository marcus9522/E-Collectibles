package prodotto;

public class ListaBean {
String email;
int cod_prod;
public ListaBean() {
	email="";
	cod_prod=-1;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getCod_prod() {
	return cod_prod;
}
public void setCod_prod(int cod_prod) {
	this.cod_prod = cod_prod;
}
}
