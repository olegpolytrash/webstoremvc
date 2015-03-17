package com.webstoremvc.constant;

/**
 * Constants for security values
 */
public enum SecurityConstants {
	/**
	 * Length of the generated cookie
	 */
	LOGIN_COOKIE_LENGTH(130), 
	
	/**
	 * radix used for transforming the cookie
	 */
	LOGIN_COOKIE_RADIX(32);

	
	/**
	 * value of a constant
	 */
	private int value;

	/**
	 * Constructor
	 * @param constantValue value of the constant
	 */
	SecurityConstants(int constantValue) {
		setValue(constantValue);
	}

	/**
	 * @return the constantValue
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param constantValue the constantValue to set
	 */
	public void setValue(int constantValue) {
		this.value = constantValue;
	}


}
