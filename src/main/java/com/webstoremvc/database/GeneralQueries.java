package com.webstoremvc.database;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.webstoremvc.database.model.Category;
import com.webstoremvc.database.model.DBTableNames;
import com.webstoremvc.utils.CollectionUtils;

import static com.google.common.base.Preconditions.*;

/**
 * Contains all queries for general operations that are not included in any other queries class.
 */
public class GeneralQueries extends QueryBase {
	/**
	 * Get all categories
	 * @return set filled with category entries or an empty set if nothing was found 
	 */
	public Set<Category> getAllCategories( ){
		Set<Category> categoriesSet = null;
		
		try {
			super.beginDbAction();

			Session session = getSession().get();

			List<?> querieResult = session.createQuery("FROM " + DBTableNames.Category).list();

			if (querieResult == null) {
				categoriesSet = Collections.emptySet();
			} else {
				categoriesSet = new HashSet<>(CollectionUtils.castList(Category.class, querieResult));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.endDbAction();
		}

		return categoriesSet;
	}

	/**
	 * Return the path to the commodity's image.
	 * @param commodityName
	 * @return the path of the image
	 * @throws NullPointerException if parameter is null
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	public String getImagePath(String commodityName) {		
		checkNotNull(commodityName, "commodityName cannot be null");
		checkArgument(!commodityName.isEmpty(), "commodityName cannot be empty");

		String path;

		super.beginDbAction();

		Session session = getSession().get();

		path = (String) session.createQuery("SELECT image_path FROM " + DBTableNames.Commodity
				+ " WHERE name = :name").
				setString("name", commodityName).
				uniqueResult();

		super.endDbAction();

		return path;
	}
}
