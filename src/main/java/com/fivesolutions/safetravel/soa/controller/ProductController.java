package com.fivesolutions.safetravel.soa.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fivesolutions.safetravel.service.ProductService;
import com.fivesolutions.safetravel.service.UserService;
import com.fivesolutions.safetravel.soa.bean.OrganizationBean;
import com.fivesolutions.safetravel.soa.bean.ProductBean;
import com.fivesolutions.safetravel.soa.bean.UserBean;
import com.fivesolutions.safetravel.soa.request.GenericRequest;
import com.fivesolutions.safetravel.soa.response.GenericResponse;

@RestController
@RequestMapping(value = "/pc")
public class ProductController {

	private static final Logger logger = LogManager.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/sp", method = RequestMethod.POST)
	public GenericResponse<ProductBean> saveProduct(@RequestBody GenericRequest<ProductBean> request) {
		logger.info("ProductController.saveProduct()");
		GenericResponse<ProductBean> response = new GenericResponse<>();
		ProductBean product = request.getData();
		ProductBean productAux = new ProductBean();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userService.getUserByUsername(principal.toString());
		if(user.getOrganizationId() != null) {
			product.setOrganization(new OrganizationBean());
			product.getOrganization().setId(user.getOrganizationId());			
		}
		productAux = productService.saveProduct(product);
		response.setData(productAux);
		return response;
	}
	
	@RequestMapping(value = "/gap", method = RequestMethod.POST)
	public GenericResponse<ProductBean> getAllProducts(@RequestBody GenericRequest<ProductBean> request) {
		logger.info("ProductController.getAllProducts()");
		GenericResponse<ProductBean> response = new GenericResponse<>();
		List<ProductBean> productList = productService.getAllProducts();
		response.setDatalist(productList);
		return response;
	}
	
	@RequestMapping(value = "/gpbup", method = RequestMethod.POST)
	public GenericResponse<ProductBean> getProductsByUserPrincipal(@RequestBody GenericRequest<ProductBean> request) {
		logger.info("ProductController.getProductsByUserPrincipal()");
		GenericResponse<ProductBean> response = new GenericResponse<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userService.getUserByUsername(principal.toString());
		List<ProductBean> productList = null;
		System.out.println(user);
		if(user.getOrganizationId() != null) {
			productList = productService.getProductsByUserPrincipal(user.getOrganizationId());			
		}
		response.setDatalist(productList);
		return response;
	}
	
	@RequestMapping(value = "/gpbi", method = RequestMethod.POST)
	public GenericResponse<ProductBean> getProductById(@RequestBody GenericRequest<ProductBean> request) {
		logger.info("ProductController.getProductById()");
		GenericResponse<ProductBean> response = new GenericResponse<>();
		ProductBean product = new ProductBean();
		product = productService.getProductById(request.getData().getId());
		response.setData(product);
		return response;
	}
	
	@RequestMapping(value = "/gpbt", method = RequestMethod.POST)
	public GenericResponse<ProductBean> getProductByType(@RequestBody GenericRequest<ProductBean> request) {
		logger.info("ProductController.getProductByType()");
		GenericResponse<ProductBean> response = new GenericResponse<>();
		List<ProductBean> productList = productService.getProductByType(request.getData());
		response.setDatalist(productList);
		return response;
	}	
	
}
