package com.roy.model;

import java.util.Date;

public class Portfolio {
	
	public String title=new String();
	public final static int MAX_PORTFOLIO_SIZE=5;
	public static enum ALGO_RECOMMENDATION
	{DO_NOTHING, BUY, SELL};
	public Stock[] stocks;
	public StockStatus[] stocksStatus; //inner class below
	private int portfolioSize=0;
	private float balance;
	
	


	//C'tor
	public Portfolio () { 
		
	 stocks = new Stock[MAX_PORTFOLIO_SIZE];
	 stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	  if(getLogicalSizeStocks(stocks) > 0 && getLogicalSizeStocks(stocks)<= MAX_PORTFOLIO_SIZE-1)//array is not full	
	  { 
			int logicalSizeStocks = getLogicalSizeStocks(stocks);
			for (int i=0 ; i<logicalSizeStocks ; i++)
			{
				this.addStock(stocks[i]) ; 
				stocksStatus[i]=new StockStatus();
			}
				
	  }
			
	}
	
	public Portfolio (String title,Stock[] stocks,StockStatus[] stocksStatus, float balance) {
		setTitle(title); setStocks(stocks); setStocksStatus(stocksStatus); setBalance(balance);
		
	}
	
	//copy c'tor
	public Portfolio (Portfolio portfolio){
		this(portfolio.getTitle(), portfolio.getStocks(), portfolio.getStocksStatus(), portfolio.getBalance() );
		
	}
	
	
	public float getBalance() {
		return balance;
	}
	
	

	public void setBalance(float balance) {
		this.balance = balance;
	}

	private int getLogicalSizeStocks(Stock[] stocks){//add description later
		int i =0 ;
		while(stocks[i]!=null && i<MAX_PORTFOLIO_SIZE){
			i++;
		}
		return i;//-1;//else i would be returning the spot AFTER last item position(on array)
	}
	private int getLogicalSizeStatus(StockStatus[] OtherstockStatus){//add description later
		int i =0 ;
		while(OtherstockStatus[i]!=null && i<MAX_PORTFOLIO_SIZE){
			i++;
		}
		return i;//-1;
	}
	private void copyStocksArray(Stock[] stocks, int size){// combine two next func to one in the copy c'tor
		
		if(size > 0 && size<= MAX_PORTFOLIO_SIZE-1)//array is not full
		{
		for (int i = 0; i<size; i++){//len dont give logical(3) but physical(5)->find func!
			this.addStock(stocks[i]);  
		}
		}
		else{
			
		}
		//this.stocks[i] = new Stock(stocks[i]) ;
		  //this.portfolioSize++; 
		  // does the same
	}
public void copyStockStatusArray(StockStatus[] stockStatus,int size)
{
		
		if(size >= 0 && size<= MAX_PORTFOLIO_SIZE-1)//array is not full and not pointing to null
		{
			for(int i =0 ;i <=size ;i++)
			{	
				stocksStatus[i] = new StockStatus(stockStatus[i]);
			}
		}
		else//array is full
			{
			//system.out.print("array is full!");
			return ;
			}
		
}

public void updateBalance(float amount){
	balance = balance+amount;
}


		
	
	public StockStatus[] getStocksStatus() {
		return this.stocksStatus;
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
		for (int i=0; i<getPortfolioSize(); i++)
		{
		if((this.stocks[i].getSymbol()).equals(stock.getSymbol()))// checking if theres same stock in the portfolio
		{
			System.out.println("you already have such stock! cant add new one ");
			return;
		
		}
		else if (getPortfolioSize()<portfolioSize)//array is full
		{
			System.out.println("Can’t add new stock, portfolio can have only " +MAX_PORTFOLIO_SIZE+ " stocks");
			return ;
		}
		
		else{
			stocks[portfolioSize] = new Stock(stock) ;
			addStockStatus();
			portfolioSize++ ;
			this.setStocksStatus(stocksStatus);
			
			return ;
		}
		}
	}

	private void addStockStatus(){
		
			this.stocksStatus[getLogicalSizeStocks(this.stocks)] = new StockStatus() ;
		}
	

	
	
	public Boolean removeStock(Stock symbol){
		
		
		for (int i=0; i < portfolioSize ; i++){
			
			if ((this.stocksStatus[i].symbol).equals(symbol)) //validation, do we have such stock in portfolio?
			{	
				sellStock(this.stocksStatus[i].symbol, -1);
				//
				this.stocks[i]=this.stocks[portfolioSize-1];
				this.stocksStatus[i]=this.stocksStatus[portfolioSize-1];
				this.portfolioSize--;
							
				return true;
			}

		}
		return false;
	}
	
	public boolean sellStock(String symbol, int sellQuant){
		if (sellQuant==-1){
			for (int i=0; i < portfolioSize ; i++){
				
				if ((this.stocksStatus[i].symbol).equals(symbol)){
					//add the money earned from selling in this transaction
					
					this.updateBalance((this.stocksStatus[i].getCurrentBid())*(this.stocksStatus[i].getStockQuantity()));
					return true;
				}
				else{
					System.out.println("there is no such stock");
					return false;
			}
		}
		}
			
			if (sellQuant>0 && sellQuant<this.stocksStatus[i].getStockQuantity()){
				for (int i=0; i < portfolioSize ; i++){
					
					if ((this.stocksStatus[i].symbol).equals(symbol))
					{	
						this.updateBalance((this.stocksStatus[i].getCurrentBid())*sellQuant);//add the money earned from selling in this transaction
						return true;
					}
					else
						System.out.println("there is no such stock");
						return false;				
			}
			}
			
			System.out.println("error! invalid value");
			return false;
		
	}
	
	public boolean buyStock (String symbol, int buyQuant){
		
		if(buyQuant>0){
		for(int i=0 ; i<portfolioSize ; i++){
			if ((this.stocksStatus[i].getSymbol()).equals(symbol) && ((this.stocksStatus[i].getCurrentAsk())*buyQuant)<balance){
				
				this.updateBalance(-(this.stocksStatus[i].getCurrentAsk()*buyQuant));
				return true;
				
			}
		}
				
		}
		if (buyQuant==-1){
			
		//איך לחשב האם אפשר לקנות ללא שארית וגם עד הבאלאנס?
			
		}
		
		return false;
	}
	
	
	
	
	public Stock[] getStocks(Portfolio portfolio){
		
		return  stocks;
	}
	
	public String getHtmlString(){
	
		title=	"<h1>The portfolio</h1>";
		String output= new String();
		int i=0;
		//title=title+stocks[0].getHtmlDescription()+stocks[1].getHtmlDescription()+stocks[2].getHtmlDescription();
		while (stocks[i] !=null && i<portfolioSize)
		{
			output=title+"<br>"+stocks[i].getHtmlDescription();
		}
		return output;
	}
	
	public Stock[] getStocks() {
		return stocks;
	}
	
	public void setStocks(Stock[] stocks) {
		for(int i=0; i<stocks.length;  i++)
		{
			this.stocks[portfolioSize++]=new Stock(stocks[i]);
			i++;
		}
				
	}
	public class StockStatus{
		
		
		//static final int DO_NOTHING=0;//enum canceled it
		//final int BUY = 1;
		//final int SELL = 2;
		
		
		  String symbol;
		  float currentBid;
		 float currentAsk;
		 Date date;
		  ALGO_RECOMMENDATION recommendation;
		 int stockQuantity;
	
		 
		 
		 //c'tor
		 
		 public  StockStatus (String symbol, float currentBid, float currentAsk, Date date,ALGO_RECOMMENDATION recommendation, int stockQuantity)
		 {
			 
				setSymbol(symbol);
				setCurrentBid(currentBid);
				setCurrentAsk(currentAsk);
				setDate(date);
				setRecommendation(recommendation);
				setStockQuantity(stockQuantity);
				
			}
		 
		 public StockStatus (Stock stock){
			 this.setCurrentAsk(stock.getAsk());
			 this.setCurrentBid(stock.getBid());
			 this.setDate(new Date(stock.getDate().getDate())); 
			 this.setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			 this.setStockQuantity(0);
			 this.setSymbol(stock.getSymbol());
			 
		 }
		 
		 public  StockStatus ()
		 {
			 
				setSymbol("");
				setCurrentBid(0);
				setCurrentAsk(0);
				setDate(new Date (date.getDate()));
				setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
				setStockQuantity(0);
				
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


		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}


		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
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
	
	

	
	
	






	
	
	
	
	


