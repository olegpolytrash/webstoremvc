package com.webstoremvc.model;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.Cookie;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * Class for manipulating shopping cart
 */
public class Cart {
	/**
	 * Ids of the commodities in shopping cart
	 * KEY -  id of the commodity
	 * VALUE - quantity of the commodity
	 */
	Map<String, String> merchandiseIds;

	/**
	 * Default constructor
	 */
	public Cart() {
		merchandiseIds = new TreeMap<>();
	}

	/**
	 * Constructor
	 * @param cookie that represents shopping cart
	 */
	public Cart(Cookie cookie) {
		this(cookie.getValue());
	}

	/**
	 * Constructor
	 * @param cookie string that represents shopping cart
	 */
	public Cart(String cookie) {
		if (cookie != null && !cookie.isEmpty()) {
			merchandiseIds = splitToMap(cookie);
		} else {
			merchandiseIds = Collections.emptyMap();
		}
	}

	/**
	 * Get ids of commodities in shopping cart
	 * @return set with ids
	 */
	public Set<String> getIds() {
		return merchandiseIds.keySet();
	}

	/**
	 * Get quantity of a specific commodity in shopping cart
	 * @param id
	 * @return
	 */
	public int getTimes(String id) {
		return Integer.valueOf(merchandiseIds.get(id));
	}

	/**
	 * Get count of commodities in shopping cart
	 * @return
	 */
	public int getCount() {
		int result = 0;

		for (String v : merchandiseIds.values()) {
			result += Integer.valueOf(v);
		}

		return result;
	}

	/**
	 * Add a commodity to shopping cart
	 * @param id of the commodity
	 */
	public void addCommodity(String id) {
		String curVal = merchandiseIds.get(id);

		if (curVal == null) {
			merchandiseIds.put(id, "1");
		} else {			
			merchandiseIds.put(id, String.valueOf(Integer.valueOf(curVal)+1));
		}
	}

	/**
	 * @see toString
	 */
	@Override
	public String toString() {
		return Joiner.on(" ").withKeyValueSeparator("=").join(merchandiseIds);
	}

	private Map<String, String> splitToMap(String in) {
		return new TreeMap<String, String>(
				Splitter.on(" ").withKeyValueSeparator("=").split(in));
	}
}
