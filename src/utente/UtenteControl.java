package utente;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class UtenteControl
 */
public class UtenteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UtenteModel model;
 
	static {
		model = new UtenteModelDM();
}
    public UtenteControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String azione = request.getParameter("azione");
		if(azione.equalsIgnoreCase("registrati")){
			String email = request.getParameter("email");
			request.removeAttribute("done");
			try {
				request.setAttribute("done",model.Email(email));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			String password = request.getParameter("password"); 
			String ruolo = request.getParameter("ruolo");
			String cod_fiscale = request.getParameter("cod_fiscale"); 
			String nome = request.getParameter("nome"); 
			String cognome = request.getParameter("cognome"); 
			Date data_nascita = Date.valueOf(request.getParameter("data_nascita")); 
			String tel1 = request.getParameter("tel1"); 
			String tel2 = request.getParameter("tel2"); 
			Integer cap = Integer.valueOf(request.getParameter("cap")); 
			String via = request.getParameter("via"); 
			int civico = Integer.valueOf (request.getParameter("civico")); 
			String citta = request.getParameter("citta"); 
			String provincia = request.getParameter("provincia"); 
			String nazione = request.getParameter("nazione");
			UtenteBean bean = new UtenteBean();
			bean.setEmail(email);
			bean.setPassword(password);
			bean.setRuolo(ruolo);
			bean.setCod_fiscale(cod_fiscale);
			bean.setNome(nome);
			bean.setCognome(cognome);
			bean.setData_nascita(data_nascita);
			bean.setTel1(tel1);
			bean.setTel2(tel2);
			bean.setCap(cap);
			bean.setVia(via);
			bean.setCivico(civico);
			bean.setCitta(citta);
			bean.setProvincia(provincia);
			bean.setNazione(nazione);
			try {
				if(model.Email(email).equals("false"))model.doSave(bean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registrati.jsp");
		dispatcher.forward(request, response);
	}

}
