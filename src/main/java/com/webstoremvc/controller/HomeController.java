package com.webstoremvc.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.webstoremvc.ResponseBuilder.CategorySpecificResponseBuilder;
import com.webstoremvc.ResponseBuilder.ResponseBuildDirector;
import com.webstoremvc.ResponseBuilder.SearchResponseBuilder;
import com.webstoremvc.ResponseBuilder.SimpleResponseBuilder;

import com.webstoremvc.constant.CookieConstants;
import com.webstoremvc.model.Cart;

/**
 * Controller for main pages
 */
@Controller
public class HomeController {
	
	/**
	 * callback function
	 */
	@RequestMapping(value={"/", "home"})
	public ModelAndView index(ModelMap response, HttpServletRequest request) throws IOException{
		ResponseBuildDirector.build(new SimpleResponseBuilder(response));
		
		return new ModelAndView("home", response);
	}
	
	/**
	 * callback function
	 */
	@RequestMapping(value="category/{name}")
	public ModelAndView categorySelected(@PathVariable String name, ModelMap response) throws IOException{
		ResponseBuildDirector.build(new CategorySpecificResponseBuilder(response, name));
		
		return new ModelAndView("selectedCategory", response);
	}
	
	/**
	 * callback function
	 */
	@RequestMapping(value={"category/addCart", "addCart"})
	public @ResponseBody String addCart(@RequestParam String input, 
			@CookieValue(value = "COOKIE_SHOPPING_CART", defaultValue = "notSet") String shoppingCart, 
			HttpServletResponse response) throws NoSuchRequestHandlingMethodException {
		Cart c = null;
		
		if (shoppingCart.equals("notSet")) {
			c = new Cart(); 
		} else {
			c = new Cart(shoppingCart);
		}
		
		c.addCommodity(input);
		
		Cookie cookie = new Cookie(CookieConstants.COOKIE_SHOPPING_CART.toString(), c.toString());
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return String.valueOf(c.getCount());
	}
	
	/**
	 * callback function
	 */
	@RequestMapping(value="search")
	public ModelAndView search(@RequestParam String name, @RequestParam String category,
			ModelMap response) throws IOException {
		ResponseBuildDirector.build(new SearchResponseBuilder(response, name, category));
		
		return new ModelAndView("search", response);
	}
}
