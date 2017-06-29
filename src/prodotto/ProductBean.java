package prodotto;

public class ProductBean {

	int codice;
	String nome;
	double prezzo;
	String categoria;
	String descrizione;
    double med_rec;
    int quantita;
    String foto;
    int offerta;

	
	public ProductBean() {
		codice = -1;
		nome = "";
		prezzo = 0;
		categoria = "";
		descrizione = "";
		med_rec=0;
		quantita = 0;
		foto="";
		offerta=0;
	}
	
    public int getOfferta() {
		return offerta;
	}

	public void setOfferta(int offerta) {
		this.offerta = offerta;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getCodice() {
		return codice;
	}



	public void setCodice(int codice) {
		this.codice = codice;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public double getPrezzo() {
		return prezzo;
	}



	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}



	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public String getDescrizione() {
		return descrizione;
	}



	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	public double getMed_rec() {
		return med_rec;
	}



	public void setMed_rec(double med_rec) {
		this.med_rec = med_rec;
	}



	public int getQuantita() {
		return quantita;
	}



	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	@Override
	public String toString() {
		return nome + " (" + codice + "), " + prezzo + " " + quantita + " " + categoria + " " + med_rec + ". " + descrizione;
	}

}
