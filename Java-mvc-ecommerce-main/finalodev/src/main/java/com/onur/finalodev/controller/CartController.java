package com.onur.finalodev.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.onur.finalodev.dao.ProductDao;
import com.onur.finalodev.model.CartProduct;
import com.onur.finalodev.model.CartProductListing;
import com.onur.finalodev.model.Category;
import com.onur.finalodev.model.Product;
import com.onur.finalodev.model.User;

@Controller
public class CartController {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CartProductDao cartProductDao;

	@GetMapping(value = "/sepet")
	public ModelAndView sepet(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

			String formattedTotalPrice = String.format("%.2f", totalPrice);

			ModelAndView modelAndView = new ModelAndView("cart");
			List<Category> categories = categoryDao.getAllCategories();
			modelAndView.addObject("categories", categories);
			modelAndView.addObject("cartProductListings", cartProductListings);
			modelAndView.addObject("totalPrice", formattedTotalPrice);
			return modelAndView;

		} else {
			response.sendRedirect("/finalodev/login");
		}
		return new ModelAndView("home");
	}

	@GetMapping(value = "/increaseCartQuantityInCart/{productId}")
	public void increaseCartQuantityInCart(@PathVariable("productId") String productId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession httpSession = request.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			int cartId = user.getCartId();

			try {

				CartProduct cartProduct = cartProductDao.getCartProductById(cartId, Integer.parseInt(productId));
				cartProduct.setQuantity(cartProduct.getQuantity() + 1);

				cartProductDao.updateCartProduct(cartProduct);

				response.sendRedirect("/finalodev/sepet");

			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			response.sendRedirect("/finalodev/login");
		}
	}

	@GetMapping(value = "/decreaseCartQuantityInCart/{productId}")
	public void decreaseCartQuantityInCart(@PathVariable("productId") String productId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession httpSession = request.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			int cartId = user.getCartId();

			try {

				CartProduct cartProduct = cartProductDao.getCartProductById(cartId, Integer.parseInt(productId));

				if (cartProduct.getQuantity() == 1) {
					cartProductDao.deleteCartProduct(cartId, Integer.parseInt(productId));
				} else {
					cartProduct.setQuantity(cartProduct.getQuantity() - 1);

					cartProductDao.updateCartProduct(cartProduct);

				}

				response.sendRedirect("/finalodev/sepet");

			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			response.sendRedirect("/finalodev/login");
		}
	}

	@GetMapping(value = "/deleteFromCart/{productId}")
	public void deleteFromCart(@PathVariable("productId") String productId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession httpSession = request.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			int cartId = user.getCartId();

			try {

				cartProductDao.deleteCartProduct(cartId, Integer.parseInt(productId));

				response.sendRedirect("/finalodev/sepet");

			} catch (Exception e) {
				System.out.println(e);
				
			}

		} else {
			response.sendRedirect("/finalodev/login");
		}
	}

	@PostMapping(value = "/addToCart/{productId}")
	public void addToCart(@PathVariable("productId") String productId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession httpSession = request.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			int cartId = user.getCartId();

			CartProduct cProduct = new CartProduct();
			cProduct.setCartId(cartId);
			cProduct.setProductId(Integer.parseInt(productId));
			cProduct.setQuantity(1);

			try {

				cartProductDao.addCartProduct(cProduct);
			} catch (DuplicateKeyException e) {

				CartProduct cProduct2 = cartProductDao.getCartProductById(cartId, Integer.parseInt(productId));
				cProduct2.setQuantity(cProduct2.getQuantity() + 1);
				cartProductDao.updateCartProduct(cProduct2);

			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}

			String referer = request.getHeader("Referer");
			if (referer != null) {
				response.sendRedirect(referer);
			} else {
				response.sendRedirect("/finalodev");
			}

		} else {
			response.sendRedirect("/finalodev/login");
		}
	}
}
