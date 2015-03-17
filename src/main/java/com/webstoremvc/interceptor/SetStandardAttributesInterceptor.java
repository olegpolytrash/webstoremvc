package com.webstoremvc.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Optional;
import com.webstoremvc.constant.AttributeConstants;
import com.webstoremvc.constant.CookieConstants;
import com.webstoremvc.database.LoginQueries;
import com.webstoremvc.model.Cart;
import com.webstoremvc.utils.CookieUtils;

/**
 * Interceptor for setting attribute values that are needed for every page
 */
public class SetStandardAttributesInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
					throws Exception {}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {}

	/**
	 * Set attributes values that are needed for every page
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1,
			Object arg2) throws Exception {
		setShoopingCartAttribute(request);
		setLoginAttribute(request);
		return true;
	}

	/**
	 * Add an attribute with info about shopping cart to the request
	 * @param request
	 */
	public void setShoopingCartAttribute(HttpServletRequest request) {
		Cart c = null;

		// find needed cookie
		Optional<Cookie> optionalCookie = CookieUtils.findCookie(request.getCookies(), 
				CookieConstants.COOKIE_SHOPPING_CART.toString());

		int commoditiesInCart = 0;
		
		// get size of the user's cart if cart is not empty
		if (optionalCookie.isPresent()) {
			Cookie cartCookie = optionalCookie.get();
			c = new Cart(cartCookie);
			commoditiesInCart = c.getCount();
		}

		request.setAttribute(AttributeConstants.ATTRIBUTE_SHOPPING_CART.toString(), commoditiesInCart);
	}

	/**
	 * Add an attribute with info about login state to the request
	 * @param request
	 */
	public void setLoginAttribute(HttpServletRequest request) {
		// find needed cookie
		Optional<Cookie> optionalCookie = CookieUtils.findCookie(request.getCookies(), 
				CookieConstants.COOKIE_LOGIN.toString());

		// if cookie is absent set the login attribute to false
		if (!optionalCookie.isPresent()) {
			request.setAttribute(AttributeConstants.ATTRIBUTE_LOGIN.toString(), AttributeConstants.LOGIN_FALSE);
			return;
		}

		// get the cookie's value
		Cookie cookie = optionalCookie.get();
		String cookieValue = cookie.getValue();

		// check if cookie value corresponds to a logged in user
		boolean loggedIn = new LoginQueries().checkIfSecurityTokenCorrect(cookieValue);

		// set the attribute value
		if (loggedIn) {
			request.setAttribute(AttributeConstants.ATTRIBUTE_LOGIN.toString(), AttributeConstants.LOGIN_TRUE);
		} else {
			request.setAttribute(AttributeConstants.ATTRIBUTE_LOGIN.toString(), AttributeConstants.LOGIN_FALSE);
		}
	}

}
