package com.webstoremvc.utils;

/**
 * Represents a simple reference to an object.
 * @param <T>
 */
public class SimpleReference<T> {
	/**
	 * the referenced object
	 */
	private T reference;
	
	/**
	 * Default constructor
	 */
	public SimpleReference() {
	}
	
	/**
	 * Constructor
	 * @param reference
	 */
	public SimpleReference(T reference) {
		setReference(reference);
	}

	/**
	 * @return the reference
	 */
	public T getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(T reference) {
		this.reference = reference;
	}


}
