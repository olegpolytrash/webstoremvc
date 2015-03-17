package com.webstoremvc.utils;

import javax.servlet.http.Cookie;

import com.google.common.base.Optional;

public class CookieHelper {
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
