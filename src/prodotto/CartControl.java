package prodotto;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CartControl
 */

public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductModel model;
	
	static {
			model = new ProductModelDM();
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart=(Cart)request.getSession().getAttribute("cart");
        if(cart==null){
                cart=new Cart();
                request.getSession().setAttribute("cart",cart);
           }
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("addC")){
			int id=Integer.parseInt(request.getParameter("id"));
			try {
				cart.addProduct(model.doRetrieveByKey(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            request.getSession().setAttribute("cart",cart);
            response.sendRedirect("http://localhost:8080/e-collectibles/product2?action=prodotto&id="+id);
		
		
		}else if(action.equalsIgnoreCase("deleteC")){
		int id=Integer.parseInt(request.getParameter("id"));
		try {
			cart.deleteProduct(model.doRetrieveByKey(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.getSession().setAttribute("cart",cart);
        response.sendRedirect("http://localhost:8080/e-collectibles/carrello.jsp");
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
