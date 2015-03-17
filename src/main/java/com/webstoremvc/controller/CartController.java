package com.webstoremvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Optional;
import com.webstoremvc.database.CommodityQueries;
import com.webstoremvc.database.model.Commodity;
import com.webstoremvc.model.Cart;

/**
 * Shopping cart controller. Contains methods for working with shopping cart.
 */
@Controller
public class CartController {
	/**
	 * callback function
	 */
@RequestMapping(value = "/cart")
	public ModelAndView showCart(@CookieValue(value = "COOKIE_SHOPPING_CART", defaultValue = "") String shoppingCart, 
			HttpServletRequest request) throws IOException {
	   Cart c = new Cart(shoppingCart);
	   Set<String> ids = c.getIds();
	   Map<Commodity, Integer> comms = new HashMap<>();
	   Integer i = null;
	   Optional<Commodity> optionalCommodity = null;
	   Commodity commodity = null;
	   	   
	   // for a map from all commodity in the shopping cart
	   for (String id : ids) {
		   i = Integer.valueOf(id);
		   optionalCommodity = new CommodityQueries().getCommodity(i);
		   commodity = optionalCommodity.get();
		   
		   if (optionalCommodity.isPresent()) {
			   comms.put(commodity, c.getTimes(id));
		   } else {
			   System.out.println("commodity with specified id is not in the database");
		   }
	   }
	   
	   // add the map as an attribute
	   request.setAttribute("merchandises", comms);
	
	   return new ModelAndView("cart");
	}
}
