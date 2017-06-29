package recensione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RecensioneControl
 */

public class RecensioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static RecensioneModel model;

	static {
		model = new RecensioneModelDM();
}
    public RecensioneControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String azione = request.getParameter("azione");
		if(azione.equalsIgnoreCase("leggi")){
		String code = request.getParameter("code");
        try {
			request.removeAttribute("recensioni");
			request.setAttribute("recensioni", model.recensioni(code));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
		if(azione.equalsIgnoreCase("inserisci")){
			doPost(request,response);
		}


	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String enter= (String) request.getParameter("enter");
		
		Integer valutazione=Integer.valueOf(request.getParameter("valutazione"));
		String testo=request.getParameter("testo");
		Integer cod_prod=Integer.valueOf(request.getParameter("cod_prod"));
		String email= request.getParameter("email");
		if(enter.equals("user")){
		try {
			if(model.check(email,cod_prod)){	
			RecensioneBean bean = new RecensioneBean();
			bean.setValutazione(valutazione);
			bean.setEmail(email);
			bean.setTesto(testo);
			bean.setCod(cod_prod);
			try {
				response.sendRedirect("http://localhost:8080/e-collectibles/product2?action=prodotto&id="+cod_prod+"&done=yes");
				model.doSave(bean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
				response.sendRedirect("http://localhost:8080/e-collectibles/product2?action=prodotto&id="+cod_prod+"&done=repeat");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else
		{
		    response.sendRedirect("http://localhost:8080/e-collectibles/product2?action=prodotto&id="+cod_prod+"&done=no");	 
		}
}
}
