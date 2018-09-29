package com.wesleyegberto.microprofile.repository;

public class IntegrationRepository {

	/**
	 * Simulate a ping to an unstable API.
	 */
	public boolean canPing() {
		return Math.random() > 0.3;
	}

}
