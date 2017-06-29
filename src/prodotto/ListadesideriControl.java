package prodotto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListadesideriControl
 */
@WebServlet("/ListadesideriControl")
public class ListadesideriControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductModel model;
	
	static {
			model = new ProductModelDM();
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadesideriControl() {
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
	
		if(azione.equalsIgnoreCase("addl")){
			String email=request.getParameter("email");
			int cod=Integer.valueOf(request.getParameter("cod_prod"));
			try {
				if(model.checklist(cod, email)==false){
				try {
					response.sendRedirect("http://localhost:8080/e-collectibles/product2?action=prodotto&id="+cod+"&insert=yes");
					model.saveLista(email, cod);
                 } catch (SQLException e) {
				}
				}else response.sendRedirect("http://localhost:8080/e-collectibles/product2?action=prodotto&id="+cod+"&insert=no");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(azione.equalsIgnoreCase("leggil")){
			Collection<ProductBean> products = new LinkedList<ProductBean>();
			ArrayList<Integer> codes = new ArrayList<Integer>();
			String email = (String) request.getSession().getAttribute("email");
			try {
				codes=model.leggiLista(email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i=0;

			while(i<codes.size()){
				try {
					products.add(model.doRetrieveByKey(codes.get(i)));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                i++;
			}
		    request.removeAttribute("prodotti");
			request.setAttribute("prodotti",products);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listadesideri.jsp");
			dispatcher.forward(request, response);
		
			
		}
		 else if (azione.equalsIgnoreCase("deletel")) {
				int id = Integer.parseInt(request.getParameter("id"));
				String email=request.getParameter("email");
				try {
					model.doDeletelist(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("http://localhost:8080/e-collectibles/lista?azione=leggil&email="+email);
		 }
	}
	}

