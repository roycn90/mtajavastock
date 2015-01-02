package com.roy.model;

import java.util.Date;

import com.roy.model.StockStatus;

public class Portfolio {
	
	private String title;
	private final static int MAX_PORTFOLIO_SIZE=5;
	static enum ALGO_RECOMMENDATION	{DO_NOTHING, BUY, SELL};
	private StockStatus[] stocksStatus; 
	private int portfolioSize=0;
	private float balance;
	
	


	//C'tor
	public Portfolio () { 
		
		this.setTitle("");
		this.updateBalance(0);
	 stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	  if(getLogicalSizeStocks(stocksStatus) > 0 && getLogicalSizeStocks(stocksStatus)<= MAX_PORTFOLIO_SIZE-1)//array is not full	
	  { 
			int logicalSizeStocks = getLogicalSizeStocks(stocksStatus);
			for (int i=0 ; i<logicalSizeStocks ; i++)
			{
				this.addStock(stocksStatus[i]) ; 
		
			}
				
	  }
			
	}
	
	public Portfolio (String title,StockStatus[] stocksStatus, float balance) {
		setTitle(title); 
		 
		updateBalance(balance);
		
	}
	
	//copy c'tor
	public Portfolio (Portfolio portfolio){
		this(portfolio.getTitle(), portfolio.getStocksStatus(), portfolio.getBalance() );
		
	}
	
	
	public float getBalance() {
		return balance;
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
			System.out.println("array is full!");
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
		if(portfolioSize >= MAX_PORTFOLIO_SIZE) {
			System.out.println("stock array is full!");
			return;
		}
		
		for (int i=0; i<getPortfolioSize();i++) {
			if(stock.getSymbol().equals(stocksStatus[i].getSymbol())) {
				System.out.println("you already have such stock! cant add new one ");
				return;
			}
		}
		
		
		this.stocksStatus[portfolioSize]=new StockStatus(stock.getBid(), stock.getAsk(), stock.getDate(),stock.getSymbol() ,ALGO_RECOMMENDATION.DO_NOTHING,0);
		portfolioSize++;

		
		
		
	}


	

	
	
	public Boolean removeStock(String symbol){
		
		
		boolean removeFlag=false;
		
		for (int i=0; i < portfolioSize ; i++){
			
			if ((this.stocksStatus[i].getSymbol()).equals(symbol)) //validation, do we have such stock in portfolio?
			{	
				removeFlag=sellStock(this.stocksStatus[i].getSymbol(), -1);
				
				if(removeFlag){
				this.stocksStatus[i]=this.stocksStatus[portfolioSize-1];
				this.stocksStatus[i]=null;
				this.portfolioSize--;
				}
							
				return removeFlag;
				
			}

		}
		return removeFlag;
	}
	

	
	public boolean sellStock(String symbol, int sellQuant){
		
		boolean sellFlag = false;
		
		for(int i=0 ; i<portfolioSize ; i++){
			
			if((this.stocksStatus[i].getSymbol()).equals(symbol)){
				
				if(sellQuant==-1){
					this.updateBalance((this.stocksStatus[i].getStockQuantity())*(this.stocksStatus[i].getBid()));
					stocksStatus[i].setStockQuantity(0);
					sellFlag = true;
				}
				 if (sellQuant>0 && sellQuant<=this.stocksStatus[i].getStockQuantity()){
					this.updateBalance(sellQuant*(this.stocksStatus[i].getBid()));
					stocksStatus[i].setStockQuantity(this.stocksStatus[i].getStockQuantity()-sellQuant);
					sellFlag = true;
				}
			}
		}
		
		if(!sellFlag)
			System.out.println("error! not valid");
		
		return sellFlag;
	}
		
		
	
	
		
		
		 public boolean buyStock (String symbol, int buyQuant){
			 
			 if(buyQuant==0 || buyQuant<-1){
				 System.out.println("error, invalid quantity");
				 return false;
			 }
			 
			 if(buyQuant>0){
		 
		for(int i=0 ; i<portfolioSize ; i++){
		 if ((this.stocksStatus[i].getSymbol()).equals(symbol) && ((this.stocksStatus[i].getAsk())*buyQuant)<=this.getBalance()){
				
				this.updateBalance(-(this.stocksStatus[i].getAsk()*buyQuant));
				stocksStatus[i].setStockQuantity(buyQuant+this.stocksStatus[i].getStockQuantity());
				return true;
				
			}
		}
				
		}
		 if (buyQuant==-1){
			
			for(int i=0 ; i<portfolioSize ; i++){
				if ((this.stocksStatus[i].getSymbol()).equals(symbol) && ((this.stocksStatus[i].getAsk())*buyQuant)<=this.getBalance()){
					
				int tempQuant=(int)(balance/this.stocksStatus[i].getAsk());
				float left = balance%this.stocksStatus[i].getAsk();
				this.updateBalance(left);
				this.stocksStatus[i].setStockQuantity(this.stocksStatus[i].getStockQuantity()+tempQuant);	
				return true;
				
					
				}
			}
			
		}
		
		
			System.out.println("throwed");
			return false;
		
		
	
		 }
	
	
	public float getStocksValue(){
		int i=0;
		float sum=0;
		while(i<this.portfolioSize){
			sum+=this.stocksStatus[i].getBid()*this.stocksStatus[i].getStockQuantity();
			i++;
		}
		return sum;
	}
	
	
	public float getTotalValue(){

		return (getStocksValue()+this.getBalance());
		
	}
	
	
	public Stock[] getStocks(Portfolio portfolio){
		
		return  stocksStatus;
	}
	
	public String getHtmlString(){
	
	
		
		String output =new String();
		
		output += "<h1>"+this.title +"</h1>"+"<br>";
		
		for(int i=0 ; i<portfolioSize ; i++){
			output += "<br>"+stocksStatus[i].getHtmlDescription()+" <b>quantity</b> : "+ this.stocksStatus[i].getStockQuantity()+"<br>"+"<br>";
			
		}
		
		
			output += " <b>Total Portfolio Value:</b> "+ this.getTotalValue()+"$"+", <b>Total Stocks value:</b> "+this.getStocksValue()+"<b>$</b> , <b>Balance:</b> "+this.getBalance()+"<b>$</b>";
			
			return output;

		}
	
	
	
	
	public void setStocks(Stock[] stocks,int size) {
		for(int i=0; i<size;  i++)
		{
			this.addStock(stocks[i]);
			
		}
				
	}
}

	
	

	
	
	






	
	
	
	
	


