package com.webstoremvc.database;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.hibernate.Session;

import com.google.common.base.Optional;
import com.webstoremvc.database.model.Customer;
import com.webstoremvc.database.model.CustomerCookieToken;
import com.webstoremvc.database.model.DBTableNames;

/**
 * Contains all queries for login operations, like saving a login cookie value in the database, etc.
 */
public class LoginQueries extends QueryBase {
	/**
	 * Check if security token is correct, i.e present in the database and not expired. 
	 * @param securityToken to check
	 * @return true if correct, false otherwise
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public boolean checkIfSecurityTokenCorrect(String securityToken) {
		// check securityToken
		checkNotNull(securityToken, "securityToken cannot be null");
		checkArgument(!securityToken.isEmpty(), "securityToken cannot be empty");

		Long count = null;

		try {
			super.beginDbAction();

			Session session = getSession().get();

			count = (Long)session.createQuery("SELECT count(*) FROM " + DBTableNames.CustomerCookieToken
					+ " WHERE token = :token AND expired = 0 ").
					setString("token", securityToken).
					uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		if (count == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Get customer by cookie token. 
	 * @param securityToken associated with customer. This function does not check 
	 * SecurityToken for correctness.
	 * @return optional of the user or of null if not found
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public Optional<CustomerCookieToken> getCustomerByCookieToken(String securityToken) {
		// check securityToken
		checkNotNull(securityToken, "securityToken cannot be null");
		checkArgument(!securityToken.isEmpty(), "securityToken cannot be empty");

		CustomerCookieToken customer = null;

		try {
			super.beginDbAction();

			Session session = getSession().get();

			customer = (CustomerCookieToken)session.createQuery("FROM " + DBTableNames.CustomerCookieToken
					+ " WHERE token = :token AND expired = 0").
					setString("token", securityToken).
					uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return Optional.fromNullable(customer);
	}

	/**
	 * Updates the CustomerCookieToken entry in the database.
	 * @param customerCookieToken to update
	 * @throws NullPointerException if parameter is null
	 */
	public void updateCustomerCookieToken(CustomerCookieToken customerCookieToken) {
		// check customerCookieToken
		checkNotNull(customerCookieToken, "customerCookieToken cannot be null");

		try {
			super.beginDbAction();

			Session session = getSession().get();
			session.update(customerCookieToken);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}
	}

	/**
	 * Add the CustomerCookieToken entry to the database
	 * @param customerCookieToken to the database
	 * @throws NullPointerException if parameter is null
	 */
	public void addCustomerCookieToken(CustomerCookieToken customerCookieToken) {
		// check customerCookieToken
		checkNotNull(customerCookieToken, "customerCookieToken cannot be null");

		try {
			super.beginDbAction();

			Session session = getSession().get();
			session.save(customerCookieToken);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}
	}

	/**
	 * Add the CustomerCookieToken entry to the database
	 * @param customerName of the customer associated with the securityToken
	 * @param securityToken associated with the customer
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public void addCustomerCookieToken(String customerName, String securityToken) {
		// check securityToken
		checkNotNull(customerName, "customerName cannot be null");
		checkArgument(!customerName.isEmpty(), "customerName cannot be empty");

		// check securityToken
		checkNotNull(securityToken, "securityToken cannot be null");
		checkArgument(!securityToken.isEmpty(), "securityToken cannot be empty");

		try {
			super.beginDbAction();

			Session session = getSession().get();

			// get the customer by it's name
			Customer c = (Customer) session.createQuery("FROM " + DBTableNames.Customer
					+ " WHERE username = :name").
					setString("name", customerName).
					uniqueResult();

			// create the CustomerCookieToken entry
			CustomerCookieToken customer = new CustomerCookieToken(c, securityToken, false);

			// save the entry
			session.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}
	}
}
