package com.webstoremvc.database;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.hibernate.Session;

import com.webstoremvc.database.model.DBTableNames;

/**
 * Contains all queries for price operations.
 */
public class PriceQueries extends QueryBase  {
	/**
	 * Get the maximum price of commodity from the category
	 * @param category to search in
	 * @return the price value
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public int getMaxPrice(String category) {
		// check category
		checkNotNull(category, "category cannot be null");
		checkArgument(!category.isEmpty(), "category cannot be empty");

		super.beginDbAction();

		Session session = getSession().get();

		int maxPrice = (int) session.createQuery("SELECT max(price) FROM " + DBTableNames.Commodity
				+ " WHERE category.name = :category").
				setString("category", category).
				uniqueResult();

		super.endDbAction();

		return maxPrice;
	}

	/**
	 * Get the maximum price of commodity from all categories
	 * @return the price value
	 */
	public int getMaxPrice() {
		super.beginDbAction();

		Session session = getSession().get();

		int maxPrice = (int) session.createQuery("SELECT max(price) FROM " + DBTableNames.Commodity).uniqueResult();

		super.endDbAction();

		return maxPrice;
	}

	/**
	 * Get the minimum price of commodity from the category
	 * @param category to search in
	 * @return the price value
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public int getMinPrice(String category) {
		// check category
		checkNotNull(category, "category cannot be null");
		checkArgument(!category.isEmpty(), "category cannot be empty");

		super.beginDbAction();

		Session session = getSession().get();

		int minPrice = (int) session.createQuery("SELECT min(price) FROM " + DBTableNames.Commodity
				+ " WHERE category.name = :category").
				setString("category", category).
				uniqueResult();

		super.endDbAction();

		return minPrice;
	}

	/**
	 * Get the minimum price of commodity from all categories
	 * @param category to search in
	 * @return the price value
	 */
	public int getMinPrice() {
		super.beginDbAction();

		Session session = getSession().get();

		int minPrice = (int) session.createQuery("SELECT min(price) FROM " + DBTableNames.Commodity).uniqueResult();

		super.endDbAction();

		return minPrice;
	}

	/**
	 * Get the maximum price of commodities that contain the namePart in it's name
	 * @param namePart part of the needed commodity's name
	 * @return the price value
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public int getMaxPriceByNamePart(String namePart) {
		// check category
		checkNotNull(namePart, "namePart cannot be null");
		checkArgument(!namePart.isEmpty(), "namePart cannot be empty");

		super.beginDbAction();

		Session session = getSession().get();

		int maxPrice = (int) session.createQuery("SELECT max(price) FROM " + DBTableNames.Commodity
				+ " WHERE name LIKE :name").
				setString("name", namePart).
				uniqueResult();

		super.endDbAction();

		return maxPrice;
	}

	/**
	 * Get the minimum price of commodities that contain the namePart in it's name
	 * @param namePart part of the needed commodity's name
	 * @return the price value
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public int getMinPriceByNamePart(String namePart) {
		// check category
		checkNotNull(namePart, "namePart cannot be null");
		checkArgument(!namePart.isEmpty(), "namePart cannot be empty");

		super.beginDbAction();

		Session session = getSession().get();

		int minPrice = (int) session.createQuery("SELECT min(price) FROM " + DBTableNames.Commodity
				+ " WHERE name LIKE :name").
				setString("name", namePart).
				uniqueResult();

		super.endDbAction();

		return minPrice;
	}

	/**
	 * Get the minimum price of commodities that contain the namePart in it's name and are in the category 
	 * passed as the parameter
	 * @param namePart part of the needed commodity's name
	 * @return the price value
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public int getMaxPriceFromCategoryByNamePart(String namePart, String category) {
		// check category
		checkNotNull(namePart, "namePart cannot be null");
		checkArgument(!namePart.isEmpty(), "namePart cannot be empty");

		// check category
		checkNotNull(category, "category cannot be null");
		checkArgument(!category.isEmpty(), "category cannot be empty");

		super.beginDbAction();

		Session session = getSession().get();

		int maxPrice = (int) session.createQuery("SELECT max(price) FROM " + DBTableNames.Commodity
				+ " WHERE name LIKE :name "
				+ " AND category.name = :category").
				setString("name", "%"+namePart+"%").
				setString("category", category).
				uniqueResult();

		super.endDbAction();

		return maxPrice;
	}

	/**
	 * Get the minimum price of commodities that contain the namePart in it's name and are in the category 
	 * passed as the parameter
	 * @param namePart part of the needed commodity's name
	 * @return the price value
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public int getMinPriceFromCategoryByNamePart(String namePart, String category) {
		// check category
		checkNotNull(namePart, "namePart cannot be null");
		checkArgument(!namePart.isEmpty(), "namePart cannot be empty");

		// check category
		checkNotNull(category, "category cannot be null");
		checkArgument(!category.isEmpty(), "category cannot be empty");

		int minPrice = 0;
		
		try {
			super.beginDbAction();

			Session session = getSession().get();

			minPrice = (int) session.createQuery("SELECT min(price) FROM " + DBTableNames.Commodity
					+ " WHERE name LIKE :name "
					+ " AND category.name = :category").
					setString("name", "%"+namePart+"%").
					setString("category", category).
					uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return minPrice;
	}
}
