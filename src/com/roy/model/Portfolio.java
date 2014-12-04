package com.roy.model;

import java.util.Date;

import com.roy.Stock;

public class Portfolio {
	
	public String title=new String();
	public final static int MAX_PORTFOLIO_SIZE=5;
	
	public Stock[] stocks;
	public StockStatus[] stocksStatus; //inner class below
	public int portfolioSize=0;
	
	//Contractor
	public Portfolio () { 
	 stocks = new Stock[MAX_PORTFOLIO_SIZE];
	 stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}
	
	public int getPortfolioSize() {
		return portfolioSize;
	}
	
	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}
	

	public void addStock(Stock stock){
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}
	
	public Stock[] getStocks(Portfolio portfolio){
		
		return  stocks;
	}
	
	public String getHtmlDescription(){
	
		title=	"<h1>portfolio title</h1>";
		title=title+stocks[0].getHtmlDescription()+stocks[1].getHtmlDescription()+stocks[2].getHtmlDescription();
		
		return title;
	}
	
	public class StockStatus{
		static final int DO_NOTHING=0;
		final int BUY = 1;
		final int SELL = 2;
		
		 String symbol;
		 float currentBid;
		 float currentAsk;
		 Date date;
		 int recommendation;
		 int stockQuantity;
	}

	public Stock[] getStocks() {
		return stocks;
	}
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}




	}
	
	
	
	


