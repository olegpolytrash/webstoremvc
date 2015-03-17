package com.webstoremvc.database;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import com.google.common.base.Optional;
import com.webstoremvc.database.model.Commodity;
import com.webstoremvc.database.model.DBTableNames;
import com.webstoremvc.utils.CollectionUtils;

public class CommodityQueries extends QueryBase {
	/**
	 * Get all commodities from a specific category
	 * @param categoryName
	 * @return
	 * @throws NullPointerException if a parameter is null
	 * @throws IllegalArgumentException if a parameter is incorrect
	 */
	public List<Commodity> getAllCommoditiesFromCategory(String categoryName){
		// check category
		checkNotNull(categoryName, "categoryName cannot be null");
		checkArgument(!categoryName.isEmpty(), "categoryName cannot be empty");

		// the list to return
		List<Commodity> commodities = null;

		try{
			super.beginDbAction();

			Session session = getSession().get();

			// execute query
			List<?> querieResult = session.createQuery("FROM " + DBTableNames.Commodity + " as c "
					+ " WHERE c.category.name = :smartphones").
					setString("smartphones", categoryName).
					list(); 

			if (querieResult == null) {
				commodities = Collections.emptyList();
			} else {
				commodities = CollectionUtils.castList(Commodity.class, querieResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return commodities;
	}

	/**
	 * Get all commodities from all categories.
	 * @return
	 */
	public List<Commodity> getAllCommodities(){
		List<Commodity> commodities = null;

		try {
			super.beginDbAction();

			Session session = getSession().get();

			List<?> querieResult = session.createQuery("FROM " + DBTableNames.Commodity).list(); 

			if (querieResult == null) {
				commodities = Collections.emptyList();
			} else {
				commodities = CollectionUtils.castList(Commodity.class, querieResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return commodities;
	}

	/**
	 * Get a commodity by id.
	 * @param id of the commodity
	 * @return optional of the commodity
	 * @see Optional
	 */
	public Optional<Commodity> getCommodity(int id) {	
		Object commodity = null;

		try {
			super.beginDbAction();

			Session session = getSession().get();

			commodity = session.get(Commodity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return Optional.fromNullable((Commodity)commodity);
	}


	/**
	 * Get commodities by their name and category names 
	 * @param commodityName
	 * @param categoryName
	 * @return list of the found commodities
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if a parameter is incorrect
	 */
	public List<Commodity> getCommodities(String commodityName, String categoryName) {
		// check commodityName
		checkNotNull(commodityName, "categoryName cannot be null");
		checkArgument(!commodityName.isEmpty(), "categoryName cannot be empty");

		// check category
		checkNotNull(categoryName, "categoryName cannot be null");
		checkArgument(!categoryName.isEmpty(), "categoryName cannot be empty");

		List<Commodity> commodities = null;

		try {
			super.beginDbAction();

			Session session = getSession().get();

			List<?> querieResult = session.createQuery("FROM " + DBTableNames.Commodity + " c "
					+ " WHERE c.name LIKE :name AND c.category.name = :category").
					setString("name", "%"+commodityName+"%").
					setString("category", categoryName).
					list();

			if (querieResult == null) {
				commodities = Collections.emptyList();
			} else {
				commodities = CollectionUtils.castList(Commodity.class, querieResult);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return commodities;
	}

	/**
	 * Get commodities by a part of their name and category names that are within the price range.
	 * @param commodityNamePart part of the commodity's name
	 * @param categoryName  category's
	 * @param minPrice start of the price range
	 * @param maxPrice end of the price range
	 * @return list of the found commodities
	 * @throws NullPointerException if a parameter is null
	 * @throws IllegalArgumentException if a parameter is incorrect
	 */
	public List<Commodity> getCommodities(String commodityNamePart, String categoryName, 
			String minPrice, String maxPrice) {
		// check commodityName
		checkNotNull(commodityNamePart, "commodityName cannot be null");
		checkArgument(!commodityNamePart.isEmpty(), "commodityName cannot be empty");

		// check categoryName
		checkNotNull(categoryName, "categoryName cannot be null");
		checkArgument(!categoryName.isEmpty(), "categoryName cannot be empty");

		// check minPrice
		checkNotNull(minPrice, "minPrice cannot be null");
		checkArgument(!minPrice.isEmpty(), "minPrice cannot be empty");

		// check maxPrice
		checkNotNull(maxPrice, "maxPrice cannot be null");
		checkArgument(!maxPrice.isEmpty(), "maxPrice cannot be empty");

		List<Commodity> commodities = null;

		try {
			super.beginDbAction();

			Session session = getSession().get();

			List<?> querieResult = session.createQuery("FROM " + DBTableNames.Commodity + " c "
					+ " WHERE c.name LIKE :name AND"
					+ " c.category.name = :category AND "
					+ " c.price >= minPrice AND "
					+ " c.price <= maxPrice").
					setString("name", "%"+commodityNamePart+"%").
					setString("category", categoryName).
					list();

			if (querieResult == null) {
				commodities = Collections.emptyList();
			} else {
				commodities = CollectionUtils.castList(Commodity.class, querieResult);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return commodities;
	}

	/**
	 * Get commodities by a part of their name that are within the price range.
	 * @param commodityNamePart part of the commodity's name
	 * @param minPrice start of the price range
	 * @param maxPrice end of the price range
	 * @return list of the found commodities
	 * @throws NullPointerException if a parameter is null
	 * @throws IllegalArgumentException if a parameter is incorrect
	 */
	public List<Commodity> getCommoditiesByPriceRange(String commodityName, String minPrice, String maxPrice) {
		// check commodityName
		checkNotNull(commodityName, "commodityName cannot be null");
		checkArgument(!commodityName.isEmpty(), "commodityName cannot be empty");

		// check minPrice
		checkNotNull(minPrice, "minPrice cannot be null");
		checkArgument(!minPrice.isEmpty(), "minPrice cannot be empty");

		// check maxPrice
		checkNotNull(maxPrice, "maxPrice cannot be null");
		checkArgument(!maxPrice.isEmpty(), "maxPrice cannot be empty");

		List<Commodity> commodities = null;

		try {
			super.beginDbAction();

			Session session = getSession().get();

			List<?> qurieResult = session.createQuery("FROM" + DBTableNames.Commodity + " c "
					+ " WHERE c.name LIKE :name AND "
					+ " c.price >= minPrice AND "
					+ " c.price <= maxPrice").
					setString("name", "%"+commodityName+"%").
					list();

			if (qurieResult == null) {
				commodities = Collections.emptyList();
			} else {
				commodities = CollectionUtils.castList(Commodity.class, qurieResult);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return commodities;
	}

	/**
	 * Get commodities that are within the price range
	 * @param minPrice start of the price range
	 * @param maxPrice end of the price range
	 * @return list of the found commodities
	 * @throws NullPointerException if a parameter is null
	 * @throws IllegalArgumentException if a parameter is incorrect
	 */
	public List<Commodity> getCommoditiesByPriceRange(String minPrice, String maxPrice) {
		// check minPrice
		checkNotNull(minPrice, "minPrice cannot be null");
		checkArgument(!minPrice.isEmpty(), "minPrice cannot be empty");

		// check maxPrice
		checkNotNull(maxPrice, "maxPrice cannot be null");
		checkArgument(!maxPrice.isEmpty(), "maxPrice cannot be empty");

		List<Commodity> commodities = null;

		try {
			super.beginDbAction();

			Session session = getSession().get();

			List<?> querieResult = session.createQuery("FROM " + DBTableNames.Commodity
					+ " WHERE "
					+ " price >= :minPrice AND "
					+ " price <= :maxPrice").
					setString("minPrice", minPrice).
					setString("maxPrice", maxPrice).
					list();
			
			if (querieResult == null) {
				commodities = Collections.emptyList();
			} else {
				commodities = CollectionUtils.castList(Commodity.class, querieResult);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return commodities;
	}

}
