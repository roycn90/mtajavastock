package com.roy.exceptions;

public class StockNotExistException extends Exception{
	
	public StockNotExistException(String symbol){
		super("the stock " +symbol+ " does not exist!");
		}

}
