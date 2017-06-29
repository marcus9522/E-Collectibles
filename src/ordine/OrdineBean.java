package ordine;

import java.sql.Date;

public class OrdineBean {
 String email;
 int cod_ordine;
 double importo_tot;
 Date data_ord;
 String mod_pagamento;
 String stato;
 String corriere;
 String tracciamento;
 Date data_partenza;
 
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getCod_ordine() {
	return cod_ordine;
}
public void setCod_ordine(int cod_ordine) {
	this.cod_ordine = cod_ordine;
}
public double getImporto_tot() {
	return importo_tot;
}
public void setImporto_tot(double importo_tot) {
	this.importo_tot = importo_tot;
}
public Date getData_ord() {
	return data_ord;
}
public void setData_ord(Date data_ord) {
	this.data_ord = data_ord;
}
public String getMod_pagamento() {
	return mod_pagamento;
}
public void setMod_pagamento(String mod_pagamento) {
	this.mod_pagamento = mod_pagamento;
}
public String getStato() {
	return stato;
}
public void setStato(String stato) {
	this.stato = stato;
}
public String getCorriere() {
	return corriere;
}
public void setCorriere(String corriere) {
	this.corriere = corriere;
}
public String getTracciamento() {
	return tracciamento;
}
public void setTracciamento(String tracciamento) {
	this.tracciamento = tracciamento;
}
public Date getData_partenza() {
	return data_partenza;
}
public void setData_partenza(Date data_partenza) {
	this.data_partenza = data_partenza;
}
}




