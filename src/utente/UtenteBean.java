package utente;

import java.sql.Date;

public class UtenteBean {
	String email; 
	String password; 
	String ruolo;
	String cod_fiscale; 
	String nome; 
	String cognome; 
	Date data_nascita; 
	String tel1; 
	String tel2; 
	Integer cap; 
	String via; 
	int civico; 
	String citta; 
	String provincia; 
	String nazione; 
public UtenteBean(){
	email=""; 
	password="";  
	ruolo="";   
	cod_fiscale="";  
	nome="";   
	cognome="";
	tel1=""; 
	tel2 ="";
	cap=0; 
	via="";  
	civico=0;  
	citta="";  
	provincia="";  
	nazione="";  
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRuolo() {
	return ruolo;
}
public void setRuolo(String ruolo) {
	this.ruolo = ruolo;
}
public String getCod_fiscale() {
	return cod_fiscale;
}
public void setCod_fiscale(String cod_fiscale) {
	this.cod_fiscale = cod_fiscale;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCognome() {
	return cognome;
}
public void setCognome(String cognome) {
	this.cognome = cognome;
}
public Date getData_nascita() {
	return data_nascita;
}
public void setData_nascita(Date data_nascita) {
	this.data_nascita = data_nascita;
}
public String getTel1() {
	return tel1;
}
public void setTel1(String tel1) {
	this.tel1 = tel1;
}
public String getTel2() {
	return tel2;
}
public void setTel2(String tel2) {
	this.tel2 = tel2;
}
public Integer getCap() {
	return cap;
}
public void setCap(Integer cap) {
	this.cap = cap;
}
public String getVia() {
	return via;
}
public void setVia(String via) {
	this.via = via;
}
public int getCivico() {
	return civico;
}
public void setCivico(int civico) {
	this.civico = civico;
}
public String getCitta() {
	return citta;
}
public void setCitta(String citta) {
	this.citta = citta;
}
public String getProvincia() {
	return provincia;
}
public void setProvincia(String provincia) {
	this.provincia = provincia;
}
public String getNazione() {
	return nazione;
}
public void setNazione(String nazione) {
	this.nazione = nazione;
}

}
