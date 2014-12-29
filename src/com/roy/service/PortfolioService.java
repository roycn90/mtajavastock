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
		
/*
		Stock stock1=new Stock("PIH",(float)10.0,(float)8.5,date);
		Stock stock2=new Stock("AAL",(float)30.0,(float)25.5,date);
		Stock stock3=new Stock("CAAS",(float)20,(float)15.5,date);
	*/
		
		Stock stock1 = new Stock();
		stock1.setSymbol("PIH");
		stock1.setAsk((float)10.0);
		stock1.setBid((float)8.5);
		stock1.setDate(date);
		
		Stock stock2 = new Stock();
		stock2.setSymbol("AAL");
		stock2.setAsk((float)30.0);
		stock2.setBid((float)25.5);
		stock2.setDate(date);
		
		Stock stock3 = new Stock();
		stock3.setSymbol("CAAS");
		stock3.setAsk((float)20.0);
		stock3.setBid((float)15.5);
		stock3.setDate(date);
		
		
		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);
		
		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000);
		myPortfolio.buyStock("PIH",20);
		myPortfolio.buyStock("AAL",30);
		myPortfolio.buyStock("CAAS",40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
		
	}
	


}
