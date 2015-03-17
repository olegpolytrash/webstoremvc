package com.webstoremvc.controller;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Optional;
import com.webstoremvc.constant.AttributeConstants;
import com.webstoremvc.constant.CookieConstants;
import com.webstoremvc.constant.SecurityConstants;
import com.webstoremvc.database.CustomerQueries;
import com.webstoremvc.database.LoginQueries;
import com.webstoremvc.database.model.Customer;
import com.webstoremvc.database.model.CustomerCookieToken;
import com.webstoremvc.utils.CookieUtils;

/**
 * Controller for account pages
 */
@Controller
public class AccountController {
	private SecureRandom random = new SecureRandom();

	public String nextSessionId() {
		String id = new BigInteger(SecurityConstants.LOGIN_COOKIE_LENGTH.getValue(), random).
				toString(SecurityConstants.LOGIN_COOKIE_RADIX.getValue());
		return id;
	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView loginGet() {
		return new ModelAndView("login");
	}

	@RequestMapping(value="/loginPost", method = RequestMethod.POST)
	public @ResponseBody String loginPost(@RequestParam(value = "name") String name,
			@RequestParam(value = "pass") String pass, HttpServletResponse response) {

		if (new CustomerQueries().checkIfCustomerExists(name, pass)) {
			String securityToken = nextSessionId();
			Cookie logedIn = new Cookie(CookieConstants.COOKIE_LOGIN.toString(), securityToken); // add md5 to cookies
			new LoginQueries().addCustomerCookieToken(name, securityToken);
			response.addCookie(logedIn);
			return "yes";
		} else {
			return "no";
		}
	}

	@RequestMapping(value="logOut")
	public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) {
		Optional<Cookie> optionalCookie = CookieUtils.findCookie(request.getCookies(), 
				CookieConstants.COOKIE_LOGIN.toString());

		LoginQueries securityQueries = new LoginQueries();

		if(optionalCookie.isPresent()) {
			Cookie c = optionalCookie.get();
			String cookie = c.getValue();
			Optional<CustomerCookieToken> customerOptional = securityQueries.getCustomerByCookieToken(cookie);

			CustomerCookieToken customer;

			if (customerOptional.isPresent()) {
				customer = customerOptional.get();
				customer.setExpired(true);
				securityQueries.updateCustomerCookieToken(customer);
				c.setMaxAge(0);
				response.addCookie(c);
			} else {
				System.out.println("can't find CustomerCookieToken by it's cookie token");
			}
		} else {
			System.out.println("cookie is not present");
		}

		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value="personalInfo")
	public ModelAndView personalInfo(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("personalInfo");
	}

	@RequestMapping(value="billingInfo")
	public ModelAndView billingInfo(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("billingInfo");
	}

	@RequestMapping(value="orders")
	public ModelAndView orders(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("orders");
	}

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView registerGet() {
		return new ModelAndView("register");
	}

	@RequestMapping(value="/registerPost", method = RequestMethod.POST)
	public ModelAndView registerPost(@RequestParam(value = "email") String email, 
			@RequestParam(value = "name") String name, @RequestParam(value = "pass") String pass,
			HttpServletRequest response) {		
		Customer newCustomer = new Customer(email, name, pass);

		CustomerQueries queries = new CustomerQueries();
		if (!queries.getCustomer(name).isPresent()) {
			queries.addCustomer(newCustomer);
			response.setAttribute(AttributeConstants.ATTRIBUTE_REGISTER.toString(), AttributeConstants.ATTRIBUTE_REGISTER_SUCCESS);
		} else {
			response.setAttribute(AttributeConstants.ATTRIBUTE_REGISTER.toString(), AttributeConstants.ATTRIBUTE_REGISTER_FAILURE);
		}

		return new ModelAndView("register");
	}
}
