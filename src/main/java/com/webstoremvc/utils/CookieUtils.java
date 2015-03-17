package com.webstoremvc.utils;

import javax.servlet.http.Cookie;

import com.google.common.base.Optional;

/**
 * Contains functions for manipulating cookie.
 */
public class CookieUtils {
	/**
	 * Find cookie by name
	 * @param cookies array to look in
	 * @param name of the cookie to find
	 * @return optional of cookie
	 */
	static public Optional<Cookie> findCookie(Cookie[] cookies, String name) {
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return Optional.of(cookie);
				}
			}
		}
		
		return Optional.absent();
	}
}
