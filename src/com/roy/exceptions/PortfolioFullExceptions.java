package com.roy.exceptions;

public class PortfolioFullExceptions extends Exception {
	
	public PortfolioFullExceptions(int maxSize){
		super("the portfolio is full! you can only"
				+ " have " +maxSize+ "stocks");
		}

}
