package com.roy.service;

import java.util.Calendar;
import java.util.Date;

import com.roy.Stock;
import com.roy.model.Portfolio;

public class PortfolioService {
	
	public Portfolio getPortfolio () {
		
		Date date=new Date();
		
		Calendar c= Calendar.getInstance();
		c.set(2014, 10, 15);
		date = c.getTime();
		
		 Portfolio myPortfolio =new Portfolio();

		Stock stock1=new Stock();
		Stock stock2=new Stock();
		Stock stock3=new Stock();
		
		stock1.setSymbol("PIH");
		stock1.setAsk((float)12.4);
		stock1.setBid((float)13.1);
		stock1.setDate(date);
		
		stock2.setSymbol("AAL");
		stock2.setAsk((float)5.5);
		stock2.setBid((float)5.78);
		stock2.setDate(date);
		
		stock3.setSymbol("CAAS");
		stock3.setAsk((float)31.5);
		stock3.setBid((float)31.2);
		stock3.setDate(date);

		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);
		
		return myPortfolio;
		
	}
	


}
