package com.onur.finalodev.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onur.finalodev.dao.CartProductDao;
import com.onur.finalodev.dao.CategoryDao;
import com.onur.finalodev.dao.OrderDao;
import com.onur.finalodev.dao.OrderProductDao;
import com.onur.finalodev.dao.PaymentMethodDao;
import com.onur.finalodev.dao.ProductDao;
import com.onur.finalodev.model.CartProduct;
import com.onur.finalodev.model.CartProductListing;
import com.onur.finalodev.model.Category;
import com.onur.finalodev.model.Order;
import com.onur.finalodev.model.OrderProduct;
import com.onur.finalodev.model.OrderProductListing;
import com.onur.finalodev.model.PaymentMethod;
import com.onur.finalodev.model.Product;
import com.onur.finalodev.model.User;

@Controller
public class OrderController {

    @Autowired
    private CategoryDao categoryDao;
    
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CartProductDao cartProductDao;
    
    @Autowired
    private PaymentMethodDao paymentMethodDao;
    
    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private OrderProductDao orderProductDao;
    
    
	@GetMapping(value = "/satinal")
	public ModelAndView satinalAndView(HttpServletRequest request,HttpServletResponse response) throws IOException {

	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {

			List<CartProduct> cartProducts = cartProductDao.getCartProductsByCartId(user.getCartId());

			if(cartProducts.size()> 0) {

				List<CartProductListing> cartProductListings = new ArrayList<CartProductListing>();

				double totalPrice = 0.0; 

				for (CartProduct cartProduct : cartProducts) {
					Product product = productDao.getProductById(cartProduct.getProductId());
					cartProductListings.add(new CartProductListing(product, cartProduct.getQuantity()));
					totalPrice += product.getPrice() * cartProduct.getQuantity(); 
																				
				}
		        String formattedTotalPrice = String.format("%.2f", totalPrice);
				List<PaymentMethod> paymentMethods = paymentMethodDao.getAllPaymentMethods();
				
				ModelAndView modelAndView = new ModelAndView("satinal");
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
				modelAndView.addObject("paymentMethods", paymentMethods);
				modelAndView.addObject("cartProductListings", cartProductListings);
		        modelAndView.addObject("totalPrice", formattedTotalPrice);
				return modelAndView;
			}else {

				response.sendRedirect("/finalodev");
			}
			
		
		}
		return new ModelAndView("home");
	}
	
	
	@PostMapping(value = "/satinalpost")
	public ModelAndView satinalPost(HttpServletRequest request,HttpServletResponse response) throws IOException {

	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			
			List<CartProduct> cartProducts = cartProductDao.getCartProductsByCartId(user.getCartId());

			List<CartProductListing> cartProductListings = new ArrayList<CartProductListing>();

			double totalPrice = 0.0; 

			for (CartProduct cartProduct : cartProducts) {
				Product product = productDao.getProductById(cartProduct.getProductId());
				cartProductListings.add(new CartProductListing(product, cartProduct.getQuantity()));
				totalPrice += product.getPrice() * cartProduct.getQuantity(); 
																				
			}
			
			String address = request.getParameter("address");
			String paymentMethodId = request.getParameter("paymentMethod");
			
			Order order = new Order();
			order.setUserId(user.getId());
			order.setCreatedAt(LocalDateTime.now());
			order.setAddress(address);
			order.setTotalPrice(totalPrice);
			order.setPaymentMethodId(Integer.parseInt(paymentMethodId));
			
			int orderId = orderDao.addOrder(order);
			
			for (CartProduct cartProduct : cartProducts) {
				
				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setOrderId(orderId);
				orderProduct.setProductId(cartProduct.getProductId());
				orderProduct.setQuantity(cartProduct.getQuantity());
				orderProductDao.addOrderProduct(orderProduct);
				
				cartProductDao.deleteCartProduct(cartProduct.getCartId(), cartProduct.getProductId());
				
			}
			
			
			
			
			response.sendRedirect("/finalodev/");
			
			/*
	        String formattedTotalPrice = String.format("%.2f", totalPrice);
			List<PaymentMethod> paymentMethods = paymentMethodDao.getAllPaymentMethods();
			
			ModelAndView modelAndView = new ModelAndView("satinal");
			modelAndView.addObject("paymentMethods", paymentMethods);
			modelAndView.addObject("cartProductListings", cartProductListings);
	        modelAndView.addObject("totalPrice", formattedTotalPrice);
			return modelAndView;*/
		
		}
		return new ModelAndView("home");
	}
    
}
