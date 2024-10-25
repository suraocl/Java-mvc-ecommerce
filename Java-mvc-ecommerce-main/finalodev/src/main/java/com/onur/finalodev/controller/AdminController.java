package com.onur.finalodev.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.onur.finalodev.dao.CategoryDao;
import com.onur.finalodev.dao.OrderDao;
import com.onur.finalodev.dao.OrderProductDao;
import com.onur.finalodev.dao.PaymentMethodDao;
import com.onur.finalodev.dao.ProductDao;
import com.onur.finalodev.dao.UserDao;
import com.onur.finalodev.model.Category;
import com.onur.finalodev.model.Order;
import com.onur.finalodev.model.OrderProduct;
import com.onur.finalodev.model.OrderProductListing;
import com.onur.finalodev.model.PaymentMethod;
import com.onur.finalodev.model.Product;
import com.onur.finalodev.model.User;

@Controller
public class AdminController {

	String accessRoleString = "ADMIN";
	
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private PaymentMethodDao paymentMethodDao;
    
    @Autowired
    private OrderProductDao orderProductDao;
    
	@RequestMapping(value = "/admin")
	public ModelAndView admin(HttpServletRequest request,HttpServletResponse response) throws IOException {

	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {
				List<Category> categories = categoryDao.getAllCategories();
				ModelAndView modelAndView = new ModelAndView("admin");
				modelAndView.addObject("categories", categories);


				return modelAndView;
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}

	@RequestMapping(value = "/admin/editProduct/{productId}", method = RequestMethod.POST)
	public ModelAndView editProduct(@PathVariable("productId") int productId, HttpServletRequest request, HttpServletResponse response) throws IOException {
	    HttpSession httpSession = request.getSession();
	    User sessionUser = (User) httpSession.getAttribute("user");    
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
	    if (sessionUser != null && sessionUser.getRole().equals(accessRoleString)) {
	        Product product = productDao.getProductById(productId);
	        
	        if (product != null) {
	            String newName = request.getParameter("name");
	            String newPrice = request.getParameter("price");
	            String newCategoryId = request.getParameter("categoryId");
	            String newDescription = request.getParameter("description");
	            String imageUrl = request.getParameter("imageUrl");
	            String[] priceeString = newPrice.split(" ");
	            
	            product.setCategoryId(Integer.parseInt(newCategoryId));
	            product.setDescription(newDescription);
	            product.setImageUrl(imageUrl);
	            product.setName(newName);
	            product.setPrice(Double.parseDouble(priceeString[0]));

	           productDao.updateProduct(product);

	            response.sendRedirect("/finalodev/admin/products");
	        } else {
	            response.sendRedirect("/finalodev/");
	        }
	    } else {
	        response.sendRedirect("/finalodev/");
	    }
	    return null;
	}
	@RequestMapping(value = "/admin/editUser/{userId}", method = RequestMethod.POST)
	public ModelAndView editUser(@PathVariable("userId") int userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
	    HttpSession httpSession = request.getSession();
	    User sessionUser = (User) httpSession.getAttribute("user");
	    
	    if (sessionUser != null && sessionUser.getRole().equals(accessRoleString)) {
	        User user = userDao.getUserById(userId);
	        
	        if (user != null) {
	            String newName = request.getParameter("name");
	            String newEmail = request.getParameter("email");
	            System.out.println(newName);
	            user.setName(newName);
	            user.setEmail(newEmail);

	            userDao.updateUser(user);

	            response.sendRedirect("/finalodev/admin/users");
	        } else {
	            response.sendRedirect("/finalodev/");
	        }
	    } else {
	        response.sendRedirect("/finalodev/");
	    }
	    return null;
	}
	@RequestMapping(value = "/admin/deleteUser/{userId}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("userId") int userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(userId);
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            if (user.getRole().equals(accessRoleString)) {

                userDao.deleteUser(userId);

                List<User> users = userDao.getAllUsers();
                ModelAndView modelAndView = new ModelAndView("usersadmin");
                modelAndView.addObject("users", users);

                return modelAndView;

            } else {
                response.sendRedirect("/finalodev/");
            }

        } else {
            response.sendRedirect("/finalodev/");
        }
        return new ModelAndView("home");
    }	
	
	@RequestMapping(value = "/admin/deleteProduct/{productId}", method = RequestMethod.GET)
    public ModelAndView deleteProd(@PathVariable("productId") int productId, HttpServletRequest request, HttpServletResponse response) throws IOException {
	
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            if (user.getRole().equals(accessRoleString)) {

                productDao.deleteProduct(productId);

				ModelAndView modelAndView = new ModelAndView("adminlistproducts");
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
				List<Product> products = productDao.getAllProducts();
				modelAndView.addObject("products", products);


                return modelAndView;

            } else {
                response.sendRedirect("/finalodev/");
            }

        } else {
            response.sendRedirect("/finalodev/");
        }
        return new ModelAndView("home");
    }
	
	

	@PostMapping(value = "/admin/ara/kullanici")
	public ModelAndView adminuserssearch(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		HttpSession httpSession = request.getSession();

		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {

				List<User> users = userDao.searchUsersByEmail(email);
				ModelAndView modelAndView = new ModelAndView("usersadmin");
				modelAndView.addObject("users", users);
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);



				return modelAndView;
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}
	
	
	@RequestMapping(value = "/admin/users")
	public ModelAndView adminusers(HttpServletRequest request,HttpServletResponse response) throws IOException {


		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {
				
				List<User> users = userDao.getAllUsers();
				ModelAndView modelAndView = new ModelAndView("usersadmin");
				modelAndView.addObject("users", users);
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);


				return modelAndView;
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}
	
	@PostMapping(value = "/admin/products")
	public void adminproducts(HttpServletRequest request,HttpServletResponse response) throws IOException {

	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {

		        String name = request.getParameter("name");
		        String imageUrl = request.getParameter("imageUrl");
		        String description = request.getParameter("description");
		        String price = request.getParameter("price");
		        String categoryId = request.getParameter("categoryId");
		        
		        
		        Product product = new Product();
		        product.setImageUrl(imageUrl);
		        product.setCategoryId(Integer.parseInt(categoryId));
		        product.setDescription(description);
		        product.setName(name);
		        product.setPrice(Double.parseDouble(price));
		        
		        productDao.addProduct(product);
			       
		        response.sendRedirect("/finalodev/admin");
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		
	}
	@PostMapping(value = "/admin/ara")
	public ModelAndView adminproductssearch(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		HttpSession httpSession = request.getSession();

		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {
				
				ModelAndView modelAndView = new ModelAndView("adminlistproducts");
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
		        List<Product> products = productDao.getProductsByName(name);
				modelAndView.addObject("products", products);


				return modelAndView;
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}

    @GetMapping(value = "/admin/products")
    public ModelAndView prods(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {
				
				ModelAndView modelAndView = new ModelAndView("adminlistproducts");
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
				List<Product> products = productDao.getAllProducts();
				modelAndView.addObject("products", products);


				return modelAndView;
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}
    
    @GetMapping(value = "/admin/createproduct")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws IOException {


		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {
				
				List<User> users = userDao.getAllUsers();
				ModelAndView modelAndView = new ModelAndView("productAdmin");
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
				modelAndView.addObject("users", users);


				return modelAndView;
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}
    

    @PostMapping(value = "/admin/newCategories")
    public ModelAndView createcategory(HttpServletRequest request, HttpServletResponse response) throws IOException {


	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {

		        String categoryName = request.getParameter("categoryName");

		        Category category = new Category();
		        category.setName(categoryName);
		        categoryDao.addCategory(category);

				response.sendRedirect("/finalodev/admin");
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}
    @GetMapping(value = "/admin/newCategories")
    public ModelAndView category(HttpServletRequest request, HttpServletResponse response) throws IOException {


		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {
				
				List<User> users = userDao.getAllUsers();
				ModelAndView modelAndView = new ModelAndView("newCategory");
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
				modelAndView.addObject("users", users);


				return modelAndView;
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}
    
    

	@PostMapping(value = "/admin/ara/siparis")
	public ModelAndView adminorderssearch(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		HttpSession httpSession = request.getSession();

		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {

				Map<Order, Object[]> map = new HashMap<Order, Object[]>();
				
				
				List<Order> orders = orderDao.getOrdersByUserEmail(email);
				
				
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

				List<User> users = userDao.getAllUsers();
				ModelAndView modelAndView = new ModelAndView("adminOrders");
				modelAndView.addObject("orders", map);
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
				modelAndView.addObject("users", users);
				return modelAndView;
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}
    
    @GetMapping(value = "/admin/orders")
    public ModelAndView adminorders(HttpServletRequest request, HttpServletResponse response) throws IOException {


		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {
				
				Map<Order, Object[]> map = new HashMap<Order, Object[]>();
				List<Order> orders = orderDao.getAllOrders();
				
				
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

				List<User> users = userDao.getAllUsers();
				ModelAndView modelAndView = new ModelAndView("adminOrders");
				modelAndView.addObject("orders", map);
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
				modelAndView.addObject("users", users);
				return modelAndView;

				/*
				for (Order order: orders) {
					ArrayList<OrderProduct> orderProduct = (ArrayList<OrderProduct>) orderProductDao.getOrderProductsByOrderId(order.getId());
					User orderUser = userDao.getUserById(order.getUserId());
					PaymentMethod paymentMethod = paymentMethodDao.getPaymentMethodById(order.getPaymentMethodId());
					
					map.put(order,[orderProduct]);
					
				}*/
				/*
				for (Map.Entry<Order, ArrayList<OrderProduct>> entry : map.entrySet()) {
			        System.out.println(entry.getKey().getAddress() + ":" + entry.getValue());
			    }
				
				

				List<OrderProductListing> orderProductListings = new ArrayList<OrderProductListing>();
				
				for (OrderProduct orderProduct : orderProducts) {
					
					Product product =  productDao.getProductById(orderProduct.getProductId());
					Order order = orderDao.getOrderById(orderProduct.getOrderId());
					
					orderProductListings.add(new OrderProductListing(orderProduct.getQuantity() ,product , order));
					
				}
				List<OrderProduct> orderProducts = orderProductDao.getAllOrderProducts();
				
			

				List<User> users = userDao.getAllUsers();
				ModelAndView modelAndView = new ModelAndView("adminOrders");
				modelAndView.addObject("orderProductListings",orderProductListings);
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
				modelAndView.addObject("users", users);


				return modelAndView;*/
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}
    
    
    @RequestMapping(value = "/admin/approveOrder/{id}", method = RequestMethod.GET)
    public ModelAndView approveOrder(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
	
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            if (user.getRole().equals(accessRoleString)) {

            	Order order = orderDao.getOrderById(id);
            	
            	order.setStatus("ONAYLANDI");
            	
            	orderDao.updateOrder(order);

                response.sendRedirect("/finalodev/admin/orders");

            } else {
                response.sendRedirect("/finalodev/");
            }

        } else {
            response.sendRedirect("/finalodev/");
        }
        return new ModelAndView("home");
    }
    @RequestMapping(value = "/admin/denyOrder/{id}", method = RequestMethod.GET)
    public ModelAndView denyOrder(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
	
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            if (user.getRole().equals(accessRoleString)) {

            	Order order = orderDao.getOrderById(id);
            	
            	order.setStatus("REDDEDILDI");
            	
            	orderDao.updateOrder(order);

                response.sendRedirect("/finalodev/admin/orders");

            } else {
                response.sendRedirect("/finalodev/");
            }

        } else {
            response.sendRedirect("/finalodev/");
        }
        return new ModelAndView("home");
    }
    
    @GetMapping(value = "/admin/createCategory")
    public ModelAndView admincategorys(HttpServletRequest request, HttpServletResponse response) throws IOException {


	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {
				
				List<User> users = userDao.getAllUsers();
				ModelAndView modelAndView = new ModelAndView("adminCreateCategory");
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
				modelAndView.addObject("users", users);


				return modelAndView;
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}
    @GetMapping(value = "/admin/odemeYontemleri")
    public ModelAndView odemeYontemleri(HttpServletRequest request, HttpServletResponse response) throws IOException {


	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {
				
				List<User> users = userDao.getAllUsers();
				List<PaymentMethod> paymentMethods = paymentMethodDao.getAllPaymentMethods();
				ModelAndView modelAndView = new ModelAndView("adminPaymentMethod");
				List<Category> categories = categoryDao.getAllCategories();
				modelAndView.addObject("categories", categories);
				modelAndView.addObject("users", users);
				modelAndView.addObject("paymentMethods", paymentMethods);


				return modelAndView;
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}

    @PostMapping(value = "/admin/newOdemeYontemi")
    public ModelAndView newOdemeYontemi(HttpServletRequest request, HttpServletResponse response) throws IOException {


	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		if(user != null) {
			if(user.getRole().equals(accessRoleString)) {

		        String paymentName = request.getParameter("paymentName");

		        
		        PaymentMethod paymentMethod = new PaymentMethod();
		        paymentMethod.setName(paymentName);
		        
		        paymentMethodDao.addPaymentMethod(paymentMethod);

				response.sendRedirect("/finalodev/admin");
				
			}else {
				response.sendRedirect("/finalodev/");
			}
			
		}else {
			response.sendRedirect("/finalodev/");
			
		}
		return new ModelAndView("home");
		
	}
}