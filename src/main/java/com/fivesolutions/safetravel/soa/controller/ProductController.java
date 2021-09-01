package com.fivesolutions.safetravel.soa.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(value = "/sv", method = RequestMethod.POST)
	public GenericResponse<ProductBean> save(@RequestPart("product") ProductBean productBean, @RequestPart("file") MultipartFile file) throws IOException {
		logger.info("ProductController.saveProduct()");
		GenericResponse<ProductBean> response = new GenericResponse<>();
		ProductBean product = new ProductBean();
		ProductBean productAux = productBean;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userService.getUserByUsername(principal.toString());
		if(user.getOrganizationId() != null) {
			productAux.setOrganization(new OrganizationBean());
			productAux.getOrganization().setId(user.getOrganizationId());			
		}
		if(file.getBytes().length > 0) {
			productAux.setImage(file.getBytes());
		}
		product = productService.saveProduct(productAux);
		response.setData(product);
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
	
	@RequestMapping(value = "/gi/{productId}", method = RequestMethod.POST)
	public GenericResponse<byte[]> getImage(@PathVariable("productId") Integer productId) {
		logger.info("ProductController.getImage()");
		GenericResponse<byte[]> response = new GenericResponse<>();
		ProductBean productBean = new ProductBean();
		productBean = productService.getProductById(productId);
		byte[] imageData = productBean.getImage();
		response.setData(imageData);
		return response;
	}
	
	@RequestMapping(value = "/dp", method = RequestMethod.POST)
	public GenericResponse<ProductBean> deleteProduct(@RequestBody GenericRequest<ProductBean> request) {
		logger.info("ProductController.deleteProduct()");
		GenericResponse<ProductBean> response = new GenericResponse<>();
		productService.deleteProduct(request.getData().getId());
		return response;
	}
	
	@RequestMapping(value = "/gapd", method = RequestMethod.POST)
	public GenericResponse<ProductBean> getAllProductsDisabled(@RequestBody GenericRequest<ProductBean> request) {
		logger.info("ProductController.getAllProductsDisabled()");
		GenericResponse<ProductBean> response = new GenericResponse<>();
		List<ProductBean> productList = productService.getProductsDisabled();
		response.setDatalist(productList);
		return response;
	}
	
	@RequestMapping(value = "/uep", method = RequestMethod.POST)
	public GenericResponse<ProductBean> updateStatusProduct(@RequestBody GenericRequest<ProductBean> request) {
		logger.info("ProductController.updateStatusProduct()");
		GenericResponse<ProductBean> response = new GenericResponse<>();
		productService.updateStatus(request.getData().getId());
		return response;
	}
	
	@RequestMapping(value = "/gpbnad", method = RequestMethod.POST)
	public GenericResponse<List<Map<String, Object>>> getProductByNameAndDates(@RequestBody GenericRequest<ProductBean> request) {
		logger.info("ProductController.getProductByNameAndDates()");
		GenericResponse<List<Map<String, Object>>> response = new GenericResponse<>();
		List<Map<String, Object>> productList= productService.getProductByNameAndDates(request.getData());
		response.setData(productList);
		return response;
	}
	
}
