package com.roy.model;

import java.util.Date;

public class Portfolio {
	
	public String title=new String();
	public final static int MAX_PORTFOLIO_SIZE=5;
	
	public Stock[] stocks;
	public StockStatus[] stocksStatus; //inner class below
	private int portfolioSize=0;
	
	//C'tor
	public Portfolio () { 
	 stocks = new Stock[MAX_PORTFOLIO_SIZE];
	 stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	}
	
	public Portfolio (String title,Stock[] stocks,StockStatus[] stocksStatus) {
		setTitle(title); setStocks(stocks); setStocksStatus(stocksStatus);
		
	}
	
	//copy c'tor
	public Portfolio (Portfolio portfolio){
		this(portfolio.getTitle(), portfolio.getStocks(), portfolio.getStocksStatus() );
		
	}
	
	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = new String (title);
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
	
	public Stock[] getStocks() {
		return stocks;
	}
	public void setStocks(Stock[] stocks) {
		for(int i=0; i<stocks.length;  i++)
		{
			this.stocks[portfolioSize++]=new Stock(stocks[i]);
		}
		
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
		 
		 
		 
		 //c'tor
		 
		 public StockStatus(String symbol, float currentBid, float currentAsk, Date date, int recommendation, int stockQuantity)
		 {
			 
				setSymbol(symbol);
				setCurrentBid(currentBid);
				setCurrentAsk(currentAsk);
				setDate(date);
				setRecommendation(recommendation);
				setStockQuantity(stockQuantity);
				
			}
		 
		 //copy c'tor
		 public StockStatus (StockStatus stockStatus){
			 this(
			 stockStatus.getSymbol(),
			 stockStatus.getCurrentBid(),
			 stockStatus.getCurrentAsk(),
			 stockStatus.getDate(),
			 stockStatus.getRecommendation(),
			 stockStatus.getStockQuantity()
				);
		 }
		 
		 
		 public String getSymbol() {
			return symbol;
		}


		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}


		public float getCurrentBid() {
			return currentBid;
		}


		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}


		public float getCurrentAsk() {
			return currentAsk;
		}


		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}


		public Date getDate() {
			return date;
		}


		public void setDate(Date date) {
			this.date = date;
		}


		public int getRecommendation() {
			return recommendation;
		}


		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}


		public int getStockQuantity() {
			return stockQuantity;
		}


		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}


		
	}
	

	
	
	






	}
	
	
	
	


