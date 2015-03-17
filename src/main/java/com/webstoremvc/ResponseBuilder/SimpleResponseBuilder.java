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
 * Response builder for simple pages
 */
public class SimpleResponseBuilder implements ResponseBuilder {

	private ModelMap responseMap;
	
	public SimpleResponseBuilder(ModelMap responseMap) {
		setResponseMap(responseMap);
	}

	@Override
	public void addCategories() {
		Set<Category> categories = new GeneralQueries().getAllCategories();
		getResponseMap().addAttribute("categories", categories);
	}

	@Override
	public void addCommodities() {
		List<Commodity> commodities = new CommodityQueries().getAllCommodities();
		getResponseMap().addAttribute("commodities", commodities);
	}

	@Override
	public void addMinMaxPrices() {
		getResponseMap().addAttribute("maxPrice", new PriceQueries().getMaxPrice());
		getResponseMap().addAttribute("minPrice", new PriceQueries().getMinPrice());
	}

	@Override
	public void addMinMaxFilterPrices() {
		// not needed here
	}

	/**
	 * @return the responseMap
	 */
	public ModelMap getResponseMap() {
		return responseMap;
	}

	/**
	 * @param responseMap the responseMap to set
	 */
	public void setResponseMap(ModelMap responseMap) {
		this.responseMap = responseMap;
	}
}
