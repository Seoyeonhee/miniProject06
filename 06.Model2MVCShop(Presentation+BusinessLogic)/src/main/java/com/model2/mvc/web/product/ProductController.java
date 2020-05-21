package com.model2.mvc.web.product;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;


// ==> 상품관리 Controller
@Controller
public class ProductController {

	/// Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("product") Product product) throws Exception {
		
		System.out.println("/addProduct.do");
		// Business Logic
		productService.addProduct(product);
		
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct( @RequestParam("prodNo") String prodNo, @RequestParam("menu") String menu , Model model ) throws Exception {
		
		Product product = productService.getProduct(Integer.parseInt(prodNo));
		
		product.setProTranCode(purchaseService.getPurchase2(Integer.parseInt(prodNo)).getTranCode());
		
		model.addAttribute("product", product);
		
		if(menu.equals("menu")) {
			model.addAttribute("menu", "manage");
			return "forward:/product/updateProductView.jsp";
		} else if(menu.equals("search")) {
			model.addAttribute("menu", "search");
			return "forward:/product/getProduct.jsp";
		}
		return null;
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct(Model model) throws Exception {
		
		Product product = new Product();
		productService.updateProduct(product);
		
		model.addAttribute("product", product);
		model.addAttribute("menu", "manage");
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping("/listProduct.do")
	public String listProduct( @ModelAttribute("search") Search search , Model model, HttpSession session ) throws Exception{
		
		System.out.println("/listProduct.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		String role = ((User)session.getAttribute("user")).getRole();
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search, role);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		ArrayList<Product> list = null;
		int[] proTranCode = null;
		int prodNo = 0;
		
		if(map!=null) {
			list = (ArrayList<Product>)map.get("list");
		}
		
		for(int i=0 ; i<list.size() ; i++) {
			prodNo = list.get(i).getProdNo();
			purchase = purchaseService.getPurchase2(prodNo);
			
			if(purchase!=null && !purchase.getPurchaseProd().getProTranCode().equals("0")) {
				list.set(i, purchase.getPurchaseProd());
			} else {
				list.get(i).setProTranCode("0");
			}
		}
		
		map.put("list", list);
		
		// Model 과 View 연결
		model.addAttribute("map", map);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}

}
