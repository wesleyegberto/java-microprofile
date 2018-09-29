package com.wesleyegberto.microprofile.repository;

public class ProductRepository {
	/**
	 * Simulate a connection test to database.
	 */
	public boolean canPingAndConnect() {
		return Math.random() > 0.3;
	}
}
