package com.webstoremvc.ResponseBuilder;

import java.util.List;
import java.util.Set;

import org.springframework.ui.ModelMap;

import com.webstoremvc.database.CommodityQueries;
import com.webstoremvc.database.PriceQueries;
import com.webstoremvc.database.GeneralQueries;
import com.webstoremvc.database.model.Category;
import com.webstoremvc.database.model.Commodity;

/**
 * Response builder for a specific category
 */
public class CategorySpecificResponseBuilder implements ResponseBuilder {

	ModelMap response;
	String category;
	
	public CategorySpecificResponseBuilder(ModelMap response, String category) {
		setResponse(response);
		setCategory(category);
	}

	@Override
	public void addCategories() {
		Set<Category> categories = new GeneralQueries().getAllCategories();
		response.addAttribute("categories", categories);
	}

	@Override
	public void addCommodities() {
		List<Commodity> commodities = new CommodityQueries().getAllCommoditiesFromCategory(getCategory());
		response.addAttribute("commodities", commodities);
	}

	@Override
	public void addMinMaxPrices() {
		response.addAttribute("maxPrice", new PriceQueries().getMaxPrice(category));
		response.addAttribute("minPrice", new PriceQueries().getMinPrice(category));
	}

	@Override
	public void addMinMaxFilterPrices() {
		// not needed here
	}

	/**
	 * @return the response
	 */
	public ModelMap getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(ModelMap response) {
		this.response = response;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}
