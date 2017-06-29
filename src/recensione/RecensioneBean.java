package recensione;

public class RecensioneBean {
int valutazione;
String testo;
String email;
int cod;
public RecensioneBean(){
	valutazione=0;
	testo="";
	email="";
	cod=0;
}
public int getValutazione() {
	return valutazione;
}
public void setValutazione(int valutazione) {
	this.valutazione = valutazione;
}
public String getTesto() {
	return testo;
}
public void setTesto(String testo) {
	this.testo = testo;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getCod() {
	return cod;
}
public void setCod(int cod) {
	this.cod = cod;
}

}