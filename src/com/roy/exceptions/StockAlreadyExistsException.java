package com.roy.exceptions;

public class StockAlreadyExistsException extends Exception {
	

	public StockAlreadyExistsException(String symbol){
	super("the stock " +symbol+ " already exist!");
	}
	

}
