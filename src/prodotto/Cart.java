package prodotto;
import java.util.ArrayList;
import java.util.List;
public class Cart {
     private List<ProductBean> products;
     public Cart(){
    	 products=new ArrayList<ProductBean>();
     }
	public List<ProductBean> getProducts() {
		return products;
	}
	public void addProduct(ProductBean product){
		products.add(product);
	}
	public void deleteProduct(ProductBean product){
		for(ProductBean prod : products){
			if(prod.getCodice()==product.getCodice()){
				products.remove(prod);
				break;
			}
		}
	}
}
