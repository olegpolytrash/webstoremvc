package com.webstoremvc.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Contains operations with collections
 */
public class CollectionUtils {
	/**
	 * Cast collection of some type A to list<A>
	 * @param classType class of the resulting list
	 * @param collection collection to transform
	 * @return a list made from collection
	 */
	public static <T> List<T> castList(Class<? extends T> classType, Collection<?> collection) {
		List<T> listToReturn = new ArrayList<T>(collection.size());
		for(Object o: collection)
			listToReturn.add(classType.cast(o));
		return listToReturn;
	}
}
