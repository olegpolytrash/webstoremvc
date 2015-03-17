package com.webstoremvc.ResponseBuilder;

/**
 * Interface that defines methods for building a response.
 */
public interface ResponseBuilder {
	void addCategories();
	void addCommodities();
	void addMinMaxPrices();
	void addMinMaxFilterPrices();
}
