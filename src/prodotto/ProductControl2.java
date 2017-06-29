package prodotto;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recensione.RecensioneModel;
import recensione.RecensioneModelDM;

/**
 * Servlet implementation class procuctControl2
 */
public class ProductControl2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	static ProductModel model;
	static RecensioneModel model2;
	static {
		model = new ProductModelDM();
		model2= new RecensioneModelDM();
}
    public ProductControl2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,ServletException, IOException {
		String action = request.getParameter("action");
		if(action!=null){
		if(action.equalsIgnoreCase("prodotto")){
		int id = Integer.parseInt(request.getParameter("id"));
		request.removeAttribute("prodotto");
		request.removeAttribute("recensioni");
		try {
			request.setAttribute("prodotto", model.doRetrieveByKey(id));
			request.setAttribute("recensioni", model2.recensioni(String.valueOf(id)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/prodotto.jsp");
		dispatcher.forward(request, response);
	}
		else if(action.equalsIgnoreCase("prodotti")){
			String sort = request.getParameter("sort");
			try {
				request.setAttribute("prodotti2", model.doRetrieveAll(sort));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/gestioneprodotto.jsp");
			dispatcher2.forward(request, response);
		}
		else if (action.equalsIgnoreCase("leggip")){
			String sort = request.getParameter("sort");
			request.removeAttribute("prodotto2");
			request.removeAttribute("prodotti2");
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				request.setAttribute("prodotto2",model.doRetrieveByKey(id));
				request.setAttribute("prodotti2", model.doRetrieveAll(sort));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher3 = getServletContext().getRequestDispatcher("/gestioneprodotto.jsp");
			dispatcher3.forward(request, response);
		}
		else if(action.equalsIgnoreCase("delete")){
			int cod = Integer.parseInt(request.getParameter("cod"));
			try {
				response.sendRedirect("http://localhost:8080/e-collectibles/product2?action=prodotti&done=eliminated");
				model.doDelete(cod);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(action.equalsIgnoreCase("categoria")){
			String cat = request.getParameter("cat");
			request.removeAttribute("prodotti");
			try {
				request.setAttribute("prodotti", model.categoria(cat));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher6 = getServletContext().getRequestDispatcher("/ricerca.jsp");
			dispatcher6.forward(request, response);
		}
		else if(action.equalsIgnoreCase("insert")){
			doPost(request,response);
		}
		
		
		
	}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
			if(action.equalsIgnoreCase("update")){
		    int codice = Integer.valueOf(request.getParameter("codice"));
			String nome = request.getParameter("nome");
			double prezzo = Double.valueOf(request.getParameter("prezzo"));
			String categoria = request.getParameter("categoria");
			String descrizione = request.getParameter("descrizione");
		    int quantita = Integer.parseInt(request.getParameter("quantita"));
		    String foto = request.getParameter("foto");
		    int offerta = Integer.valueOf(request.getParameter("offerta"));

			ProductBean bean = new ProductBean();
			bean.setCodice(codice);
			bean.setNome(nome);
			bean.setPrezzo(prezzo);
			bean.setCategoria(categoria);
			bean.setDescrizione(descrizione);
			bean.setQuantita(quantita);
			bean.setFoto(foto);
			bean.setOfferta(offerta);
			try {
				response.sendRedirect("http://localhost:8080/e-collectibles/product2?action=prodotti&done=updated");
				model.doUpdate(bean);

				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else if(action.equalsIgnoreCase("insert")){
				int codice = Integer.valueOf(request.getParameter("codice"));
				String nome = request.getParameter("nome");
				double prezzo = Double.valueOf(request.getParameter("prezzo"));
				String categoria = request.getParameter("categoria");
				String descrizione = request.getParameter("descrizione");
			    int quantita = Integer.parseInt(request.getParameter("quantita"));
			    String foto = request.getParameter("foto");
			    int offerta = Integer.valueOf(request.getParameter("offerta"));
                try {
					if(model.checkprodotto(codice)==false){
					ProductBean bean = new ProductBean();
					bean.setCodice(codice);
					bean.setNome(nome);
					bean.setPrezzo(prezzo);
					bean.setCategoria(categoria);
					bean.setDescrizione(descrizione);
					bean.setQuantita(quantita);
					bean.setFoto(foto);
					bean.setOfferta(offerta);
					try {
						response.sendRedirect("http://localhost:8080/e-collectibles/inserisciprodotto.jsp?done=yes");
						model.doSave(bean);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					else response.sendRedirect("http://localhost:8080/e-collectibles/inserisciprodotto.jsp?done=no");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(action.equalsIgnoreCase("search")){
				String nome = request.getParameter("name");
				request.removeAttribute("prodotti");
				try {
					request.setAttribute("prodotti", model.cerca(nome));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher dispatcher4 = getServletContext().getRequestDispatcher("/ricerca.jsp");
				dispatcher4.forward(request, response);
			}
			
			else if(action.equalsIgnoreCase("filtra")){
				double min = Double.valueOf(request.getParameter("min"));
				double max = Double.valueOf(request.getParameter("max"));
				int offerta = Integer.valueOf(request.getParameter("offerta"));
				String order = request.getParameter("order");
				request.removeAttribute("prodotti");
				try {
					request.setAttribute("prodotti", model.filtra(order, min, max, offerta));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher dispatcher5 = getServletContext().getRequestDispatcher("/ricerca.jsp");
				dispatcher5.forward(request, response);
			}
			
	
			
		
			
}
}




