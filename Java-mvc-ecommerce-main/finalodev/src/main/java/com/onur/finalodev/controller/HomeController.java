package com.onur.finalodev.controller;

import java.io.IOException;
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
import com.onur.finalodev.model.Category;
import com.onur.finalodev.model.Product;
import com.onur.finalodev.model.User;

@Controller
public class HomeController {

    @Autowired
    private CategoryDao categoryDao;
    
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CartProductDao cartProductDao;
	@GetMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("home");
		List<Category> categories = categoryDao.getAllCategories();
		modelAndView.addObject("categories", categories);
		List<Product> products = productDao.getAllProducts();
		modelAndView.addObject("products", products);

		return modelAndView;
	}

    @RequestMapping(value = "/category")
    public ModelAndView getCategoryProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryName = request.getParameter("name");
        
        List<Category> categories = categoryDao.getAllCategories();
        List<Product> products = productDao.getProductsByCategoryName(categoryName);
        
        ModelAndView modelAndView = new ModelAndView("categoryProducts");
        modelAndView.addObject("products", products);
        modelAndView.addObject("categoryName", categoryName);
        modelAndView.addObject("categories", categories);

        return modelAndView;
    }
    @RequestMapping(value = "/ara")
    public ModelAndView searchProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryName = request.getParameter("name");
        
        List<Category> categories = categoryDao.getAllCategories();
        List<Product> products = productDao.getProductsByName(categoryName);
        
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("products", products);
        modelAndView.addObject("categoryName", categoryName);
        modelAndView.addObject("categories", categories);

        return modelAndView;
    }
    @RequestMapping(value = "/urun/{id}")
    public ModelAndView productDetail(@PathVariable String id,HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        Product product = productDao.getProductById(Integer.parseInt(id));
        List<Category> categories = categoryDao.getAllCategories();
        ModelAndView modelAndView = new ModelAndView("productDetails");
        
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("product", product);

        return modelAndView;
    }
    
}
