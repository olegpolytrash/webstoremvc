package com.webstoremvc.database.model;

import javax.persistence.*;

/**
 * Hibernate entity.
 * Represents the LoggedInCustomer table in the database.
 */
@Entity
@Table(name="CustomerCookieToken")
public class CustomerCookieToken {
	@Id @GeneratedValue
	@Column(name="id")
	private int id;

	@ManyToOne
	@JoinColumn(name="id_customer")
	private Customer customer;

	@Column(name="login_token")
	private String token;
	
	@Column(name="expired")
	private boolean expired;

	public CustomerCookieToken(int id, Customer customer, String token, boolean expired) {
		this.id = id;
		this.customer = customer;
		this.token = token;
		this.expired = expired;
	}
	
	public CustomerCookieToken(Customer customer, String token, boolean expired) {
		this.customer = customer;
		this.token = token;
		this.expired = expired;
	}
	
	public CustomerCookieToken() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the expired
	 */
	public boolean isExpired() {
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
	
}
