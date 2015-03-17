package com.webstoremvc.database;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.hibernate.Session;

import com.google.common.base.Optional;
import com.webstoremvc.database.model.Customer;
import com.webstoremvc.database.model.DBTableNames;

/**
 * Contains all queries for operations with the customer table.
 */
public class CustomerQueries extends QueryBase {

	/**
	 * Add customer to the database
	 * @param customer
	 */
public void addCustomer(Customer customer) {
	checkNotNull(customer, "userName cannot be null");

	try {
		super.beginDbAction();

		Session session = getSession().get();

		session.save(customer);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		super.endDbAction();
	}
}

	/**
	 * Get customer by name.
	 * @param userName name of the user
	 * @return an optional with user if found or null if not found.
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public Optional<Customer> getCustomer(String userName) {
		checkNotNull(userName, "userName cannot be null");
		checkArgument(!userName.isEmpty(), "userName cannot be empty");

		Customer customer = null;

		try {
			super.beginDbAction();

			Session session = getSession().get();

			customer = (Customer) session.createQuery("FROM " + DBTableNames.Customer
					+ " WHERE username = :userName").
					setString("userName", userName).
					uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return Optional.fromNullable(customer);
	}

	/**
	 * Check i f customer exists.
	 * @param userName userName of the customer
	 * @param userPassword customer's password
	 * @return true if exists, false otherwise
	 * @throws NullPointerException if a parameter is null
	 * @throws IllegalArgumentException if a parameter is incorrect
	 */
	public boolean checkIfCustomerExists(String userName, String userPassword) {
		// check userName
		checkNotNull(userName, "userName cannot be null");
		checkArgument(!userName.isEmpty(), "userName cannot be empty");

		// check userPassword
		checkNotNull(userPassword, "userPassword cannot be null");
		checkArgument(!userPassword.isEmpty(), "userPassword cannot be empty");

		boolean exists = false; // default value

		try {
			super.beginDbAction();

			Session session = getSession().get();

			// find the user
			Long userCount = (Long) session.createQuery("SELECT count(1) FROM " + DBTableNames.Customer + " c "
					+ " WHERE c.username = :username AND c.password = :password"). 
					setString("username", userName).
					setString("password", userPassword).
					uniqueResult();

			if (userCount != null) {
				// if the user has been found
				if (userCount == 1) {
					exists = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return exists;
	}
}
