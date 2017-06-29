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
 * Servlet implementation class UtenteControl2
 */
public class UtenteControl2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UtenteModel model;
	 
	static {
		model = new UtenteModelDM();
}  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteControl2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String azione = request.getParameter("azione");
		if(azione.equalsIgnoreCase("logout")){
		request.getSession().removeAttribute("enter");
        request.getSession().invalidate();
        String redirectedPage="/product";
        response.sendRedirect(request.getContextPath() + redirectedPage);
		}
		else if(azione.equalsIgnoreCase("leggi")){
			doPost(request,response);
		}
		else if(azione.equalsIgnoreCase("delete")){
			doPost(request,response);
		}
		else if(azione.equalsIgnoreCase("update")){
			doPost(request,response);
		}
		else if(azione.equalsIgnoreCase("login")){
			doPost(request,response);
		}
		else if(azione.equalsIgnoreCase("leggi1")){
			doPost(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String azione = request.getParameter("azione");
		if(azione!=null){
		if(azione.equalsIgnoreCase("login")){
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		response.resetBuffer();
		if((email!=null) && (password!=null)){
		try {
			if (model.login(email, password).equals("true")){
				  String redirectedPage="/indexprotected.jsp";
			      response.sendRedirect(request.getContextPath() + redirectedPage);
				  request.getSession().setAttribute("enter", "user");
				  request.getSession().setAttribute("email", email);
				}
			else if (model.login(email, password).equals("admin")){
				   response.sendRedirect("indexprotectedadmin.jsp");
			       request.getSession().setAttribute("enter", "admin");
			       request.getSession().setAttribute("email", email);
					

			}
			else{
				response.sendRedirect("login.jsp?done=no");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}
		else if(azione.equalsIgnoreCase("logout")){
			doGet(request,response);
		}
		else if(azione.equalsIgnoreCase("update")){
			String email = request.getParameter("email");
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
				if(request.getSession().getAttribute("enter").equals("user")){
					response.sendRedirect("http://localhost:8080/e-collectibles/utente2?azione=leggi1&done=yes");
					model.doUpdate(bean);
				}
				else if(request.getSession().getAttribute("enter").equals("admin")){
					response.sendRedirect("http://localhost:8080/e-collectibles/utente2?azione=leggi&done=yes");
					model.doUpdate(bean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(azione.equalsIgnoreCase("delete")){
			String email = request.getParameter("email");
			
			try {
				if(request.getSession().getAttribute("enter").equals("user")){
					response.sendRedirect("http://localhost:8080/e-collectibles/utente2?azione=leggi1&done=eliminated");
					model.doDelete(email);
				}
				else if(request.getSession().getAttribute("enter").equals("admin")){
					response.sendRedirect("http://localhost:8080/e-collectibles/utente2?azione=leggi&done=eliminated");
					model.doDelete(email);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(azione.equalsIgnoreCase("leggi")){
			String email = (String)request.getParameter("email");
			request.removeAttribute("utenti");
			request.removeAttribute("utente");
			
			try {
				request.setAttribute("utenti", model.LeggiUtenti());
				request.setAttribute("utente", model.LeggiUtente(email));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestioneutente.jsp");
			dispatcher.forward(request, response);
		}
		else if(azione.equalsIgnoreCase("leggi1")){
			String email = (String) request.getSession().getAttribute("email");
			request.removeAttribute("utente");
	
			
			try {
				request.setAttribute("utente", model.LeggiUtente(email));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/datiutente.jsp?email="+email);
			dispatcher2.forward(request, response);
		}
		}
		}
	}
	


