package com.webstoremvc.ResponseBuilder;

/**
 * Builder pattern director.
 */
public class ResponseBuildDirector {
	static public void build(ResponseBuilder concreteBuilder) {
		concreteBuilder.addCategories();
		concreteBuilder.addCommodities();
		concreteBuilder.addMinMaxPrices();
		concreteBuilder.addMinMaxFilterPrices();
	}
}
