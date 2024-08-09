package com.nidhi.ThirdPartyIntegration.Exceptions;

public class ResourceNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(int id) {
		super("User not found for userId = "+id);
	}
}
