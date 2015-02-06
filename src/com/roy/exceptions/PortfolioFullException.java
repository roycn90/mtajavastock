package com.roy.exceptions;

public class PortfolioFullException extends Exception {

	private static final long serialVersionUID = 1L;

	public PortfolioFullException() {
		super("You had reached maximum portfolio size!");
	}
}
