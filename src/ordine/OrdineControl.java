package ordine;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prodotto.Cart;
import prodotto.ProductBean;
import prodotto.ProductModel;
import prodotto.ProductModelDM;


/**
 * Servlet implementation class OrdoneControl
 */
public class OrdineControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
static OrdineModel model;
static ProductModel model2;	
	static {
			model = new OrdineModelDM();
			model2 = new ProductModelDM();
	}
    public OrdineControl() {
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
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("insert")){
			boolean ok =true;
			int i=0;
			String prod="";
			ArrayList<Integer> pezzi=new ArrayList<Integer>();
			String email = (String) request.getSession().getAttribute("email");
			String corriere = request.getParameter("corriere");
			String mod_pagamento = request.getParameter("mod_pagamento");
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			int cod_ordine = Integer.valueOf(request.getParameter("cod_ordine"));
			double tot = Double.valueOf(request.getParameter("importo_tot"));
			List<ProductBean> prodcart=cart.getProducts();
			List<ProductBean> prodcart2=cart.getProducts();
	        for(ProductBean beancart:prodcart){
	        	i=0;
	        	for(ProductBean beancart2:prodcart2){
	        		if(beancart.getCodice()==beancart2.getCodice())i++;                                   
	        }
	        	try {
					if(i>model2.quantita(beancart.getCodice())){ok=false;
					                                           prod=beancart.getNome();
					                                            break;
					}
					pezzi.add(model2.quantita(beancart.getCodice())-i);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		}
	        if(ok==true){
			OrdineBean bean = new OrdineBean();
			bean.setCod_ordine(cod_ordine);
			bean.setEmail(email);
			bean.setCorriere(corriere);
			bean.setMod_pagamento(mod_pagamento);
			bean.setData_ord(new java.sql.Date(System.currentTimeMillis()));
			bean.setImporto_tot(tot);
			bean.setTracciamento("Non disponibile");
			bean.setStato("In elaborazione");
			try {
				
				model.doSave(bean, cart, pezzi);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	        else response.sendRedirect("http://localhost:8080/e-collectibles/carrello.jsp?done=no&name="+prod);
	            
	        if(ok){RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/indexprotected.jsp");
		           dispatcher.forward(request, response);
		           request.getSession().removeAttribute("cart");
		}
		
		}
		else if(action.equalsIgnoreCase("leggio")){
			request.removeAttribute("ordine");
			request.removeAttribute("ordini");
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				request.setAttribute("ordine",model.leggiordine(id));
				request.setAttribute("ordini", model.leggiordini());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/gestioneordini.jsp");
			dispatcher2.forward(request, response);
		}
		
		else if(action.equalsIgnoreCase("ordini")){
			try {
				request.setAttribute("ordini", model.leggiordini());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher3 = getServletContext().getRequestDispatcher("/gestioneordini.jsp");
			dispatcher3.forward(request, response);
		}
		else if(action.equalsIgnoreCase("update")){
			String email = (String) request.getSession().getAttribute("email");
			String corriere = request.getParameter("corriere");
			String mod_pagamento = request.getParameter("mod_pagamento");
			int cod_ordine = Integer.valueOf(request.getParameter("cod_ordine"));
			double importo_tot = Double.valueOf(request.getParameter("importo_tot"));
			String tracciamento = request.getParameter("tracciamento");
			Date data_partenza = Date.valueOf(request.getParameter("data_partenza"));
			Date data_ordine = Date.valueOf(request.getParameter("data_ordine"));
			String stato = request.getParameter("stato");
			OrdineBean bean = new OrdineBean();
			bean.setEmail(email);
			bean.setCorriere(corriere);
			bean.setMod_pagamento(mod_pagamento);
			bean.setCod_ordine(cod_ordine);
			bean.setStato(stato);
			bean.setImporto_tot(importo_tot);
			bean.setTracciamento(tracciamento);
			bean.setData_partenza(data_partenza);
			bean.setData_ord(data_ordine);
			try {
				response.sendRedirect("http://localhost:8080/e-collectibles/ordine?action=ordini");
				model.doUpdate(bean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		else if (action.equalsIgnoreCase("acquisti")){
			String email = (String) request.getSession().getAttribute("email");
            request.removeAttribute("ordini");
            request.removeAttribute("prodotti");
            try {
				request.setAttribute("ordini", model.leggiordine(email));
				request.setAttribute("prodotti", model2.leggiprodotto(email));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            RequestDispatcher dispatcher4 = getServletContext().getRequestDispatcher("/acquisti.jsp");
    		dispatcher4.forward(request, response);
		}
		
		
	}

}
