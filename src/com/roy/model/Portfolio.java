package com.roy.model;

import java.util.Date;

public class Portfolio {
	
	public String title;
	public final static int MAX_PORTFOLIO_SIZE=5;
	public static enum ALGO_RECOMMENDATION
	{DO_NOTHING, BUY, SELL};
	public Stock[] stocks;
	public StockStatus[] stocksStatus; //inner class below
	private int portfolioSize=0;
	private float balance;
	
	


	//C'tor
	public Portfolio () { 
		this.setTitle("");
		this.setBalance(0);
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
		setTitle(title); 
		setStocks(stocks,getLogicalSizeStocks(stocks)); 
		setBalance(balance);
		
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


	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = new String (title);
	}

	
	public int getPortfolioSize() {
		return portfolioSize;
	}
	

	

	public void addStock(Stock stock){
		for (int i=0; i<getPortfolioSize();i++)
		{
		if((this.stocks[i].getSymbol()).equals(stock.getSymbol()))// checking if theres same stock in the portfolio
		{
			System.out.println("you already have such stock! cant add new one ");
			return;
		
		}
		else if (portfolioSize==MAX_PORTFOLIO_SIZE)//array is full
		{
			System.out.println("Can’t add new stock, portfolio can have only " +MAX_PORTFOLIO_SIZE+ " stocks");
			return ;
		}
		
		else{
			stocks[portfolioSize] = new Stock(stock) ;
			stocksStatus[portfolioSize]=new StockStatus (stock);
			portfolioSize++ ;		
			return ;
		}
		}
	}


	

	
	
	public Boolean removeStock(String symbol){
		
		
		for (int i=0; i < portfolioSize ; i++){
			
			if ((this.stocksStatus[i].symbol).equals(symbol)) //validation, do we have such stock in portfolio?
			{	
				sellStock(this.stocksStatus[i].symbol, -1);
				//
				this.stocks[i]=this.stocks[portfolioSize-1];
				this.stocksStatus[i]=this.stocksStatus[portfolioSize-1];
				this.stocks[i]=null;
				this.stocksStatus[i]=null;
				this.portfolioSize--;
							
				return true;
			}

		}
		return false;
	}
	
	public boolean sellStock(String symbol, int sellQuant){
		for(int i=0 ; i<portfolioSize ; i++){
			if((this.stocksStatus[i].symbol).equals(symbol)){
				if(sellQuant==-1){
					this.updateBalance((this.stocksStatus[i].getStockQuantity())*(this.stocksStatus[i].getCurrentBid()));
					stocksStatus[i].setStockQuantity(0);
					return true;
				}
				else if (sellQuant>0 && sellQuant<=this.stocksStatus[i].getStockQuantity()){
					this.updateBalance(sellQuant*(this.stocksStatus[i].getCurrentBid()));
					stocksStatus[i].setStockQuantity(this.stocksStatus[i].getStockQuantity()-sellQuant);
					return true;
				}
				
												
			}
		}
		System.out.println("error! not valid");
					return false;
	}
		
		
	
	
	public boolean buyStock (String symbol, int buyQuant){
		
		if(buyQuant>0){
		for(int i=0 ; i<portfolioSize ; i++){
			if ((this.stocks[i].getSymbol()).equals(symbol) && ((this.stocksStatus[i].getCurrentAsk())*buyQuant)<=this.getBalance()){
				
				this.updateBalance(-(this.stocksStatus[i].getCurrentAsk()*buyQuant));
				stocksStatus[i].setStockQuantity(buyQuant+this.stocksStatus[i].getStockQuantity());
				return true;
				
			}
		}
				
		}
		else if (buyQuant==-1){
			
			for(int i=0 ; i<portfolioSize ; i++){
				if ((this.stocksStatus[i].getSymbol()).equals(symbol) && ((this.stocksStatus[i].getCurrentAsk())*buyQuant)<=this.getBalance()){
					
				int tempQuant=(int)(balance/this.stocksStatus[i].getCurrentAsk());
				float left = balance%this.stocksStatus[i].getCurrentAsk();
				this.setBalance(left);
				this.stocksStatus[i].setStockQuantity(this.stocksStatus[i].getStockQuantity()+tempQuant);	
				return true;
				
					
				}
			}
			
		}
		
		
		System.out.println("error! cant conduct this transaction!");
		return false;
		
	}
	
	public float getStocksValue(){
		int i=0;
		float sum=0;
		while(i<this.portfolioSize){
			sum+=this.stocksStatus[i].getCurrentBid()*this.stocksStatus[i].getStockQuantity();
			i++;
		}
		return sum;
	}
	
	
	public float getTotalValue(){

		return (getStocksValue()+this.getBalance());
		
	}
	
	
	public Stock[] getStocks(Portfolio portfolio){
		
		return  stocks;
	}
	
	public String getHtmlString(){
	
		
		int i=0;
		String output= title;
		while (i<portfolioSize)
		{
			output+="<br>"+stocks[i].getHtmlDescription()+" quantity : "+ this.stocksStatus[i].getStockQuantity();
		}
		output+=" <br> Balance: "+ this.getBalance()+ " Stocks value: "+this.getStocksValue()+" Total value: "+this.getTotalValue();
		return output;
	}
	
	public Stock[] getStocks() {
		return stocks;
	}
	
	public void setStocks(Stock[] stocks,int size) {
		for(int i=0; i<size;  i++)
		{
			this.addStock(stocks[i]);
			//need to solve Stockstatus
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
	
	

	
	
	






	
	
	
	
	


