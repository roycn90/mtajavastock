package com.roy.service;

import java.util.Calendar;
import java.util.Date;

import com.roy.model.Portfolio;
import com.roy.model.Stock;

public class PortfolioService {
	
	public Portfolio getPortfolio () {
		
		Date date=new Date();
		
		Calendar c= Calendar.getInstance();
		c.set(2014, 10, 15);
		date = c.getTime();
		
		 Portfolio myPortfolio =new Portfolio();

		Stock stock1=new Stock("PIH",(float)10.0,(float)8.5,date);
		Stock stock2=new Stock("AAL",(float)30.0,(float)25.5,date);
		Stock stock3=new Stock("CAAS",(float)20,(float)15.5,date);
		
		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);
		
		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.setBalance(10000);
		myPortfolio.buyStock("PIH",20);
		myPortfolio.buyStock("AAL",30);
		myPortfolio.buyStock("CAAS",40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
		
	}
	


}
