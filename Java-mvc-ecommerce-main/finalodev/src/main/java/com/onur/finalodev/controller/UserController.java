package com.onur.finalodev.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onur.finalodev.dao.UserDao;
import com.onur.finalodev.dao.CartDao;
import com.onur.finalodev.dao.CategoryDao;
import com.onur.finalodev.dao.OrderDao;
import com.onur.finalodev.dao.OrderProductDao;
import com.onur.finalodev.dao.PaymentMethodDao;
import com.onur.finalodev.dao.ProductDao;
import com.onur.finalodev.model.Category;
import com.onur.finalodev.model.Order;
import com.onur.finalodev.model.OrderProduct;
import com.onur.finalodev.model.OrderProductListing;
import com.onur.finalodev.model.PaymentMethod;
import com.onur.finalodev.model.Product;
import com.onur.finalodev.model.User;

@Controller
public class UserController {

    @Autowired
    private PaymentMethodDao paymentMethodDao;
    
    @Autowired
    private OrderProductDao orderProductDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private CartDao cartDao;
    

    @Autowired
    private OrderDao orderDao;

    @GetMapping(value = "/login")
    public ModelAndView getLogin( HttpServletResponse response) throws IOException {


		ModelAndView modelAndView = new ModelAndView("login");
		List<Category> categories = categoryDao.getAllCategories();
		modelAndView.addObject("categories", categories);

		return modelAndView;
    }
    
    @GetMapping(value = "/profil")
    public ModelAndView userProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession httpSession = request.getSession();

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			

			Map<Order, Object[]> map = new HashMap<Order, Object[]>();
			List<Order> orders = orderDao.getOrdersByUserId(user.getId());
			
			
			for (Order order : orders) {
				User orderUser = userDao.getUserById(order.getUserId());
				PaymentMethod paymentMethod = paymentMethodDao.getPaymentMethodById(order.getPaymentMethodId());

				
				
				ArrayList<OrderProduct> orderProducts = (ArrayList<OrderProduct>) orderProductDao.getOrderProductsByOrderId(order.getId());
				List<OrderProductListing> orderProductListings = new ArrayList<OrderProductListing>();
				
				for (OrderProduct orderProduct : orderProducts) {
					
					Product product =  productDao.getProductById(orderProduct.getProductId());
					
					orderProductListings.add(new OrderProductListing(orderProduct.getQuantity() ,product , order));
					
				}
				
				
                Object[] orderDetails = {orderProductListings, orderUser, paymentMethod};
                
				map.put(order, orderDetails);
				
			}

			ModelAndView modelAndView = new ModelAndView("profil");
			modelAndView.addObject("orders", map);
			List<Category> categories = categoryDao.getAllCategories();
			modelAndView.addObject("categories", categories);
			return modelAndView;
			
		}else {

			response.sendRedirect("/finalodev/");
		}
		return new ModelAndView("home");

    }
    @GetMapping(value = "/register")
    public ModelAndView registerr( HttpServletResponse response) throws IOException {

		

		ModelAndView modelAndView = new ModelAndView("register");
		List<Category> categories = categoryDao.getAllCategories();
		modelAndView.addObject("categories", categories);

		return modelAndView;
    }
    
    
    @PostMapping(value = "/register")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int cartId = cartDao.createCart();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setCartId(cartId);
        
        try {
            userDao.registerUser(user);
            
            response.sendRedirect("/finalodev/login");
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("message", "Kayıt Başarılı giriş yapın.");
            
            return modelAndView;
			
		} catch (Exception e) {
			System.out.println("error");
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("message", "Registration successful! Please log in.");
            
            return modelAndView;
			// TODO: handle exception
		}

    }

    @PostMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            User user = userDao.loginUser(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                ModelAndView modelAndView = new ModelAndView("home");
                response.sendRedirect("/finalodev/");
                return modelAndView;
            }
		} catch (Exception e) {
			System.out.println(e);
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("message", "Invalid email or password.");
            return modelAndView;
		}
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("message", "You have been logged out.");
        
        return modelAndView;
    }
}
