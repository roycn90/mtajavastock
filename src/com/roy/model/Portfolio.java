package com.roy.model;

import java.util.Date;

import com.roy.exceptions.BalanceException;
import com.roy.exceptions.PortfolioFullExceptions;
import com.roy.exceptions.StockAlreadyExistsException;
import com.roy.exceptions.StockNotExistException;
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
	  /*if(getLogicalSizeStocks(stocksStatus) > 0 && getLogicalSizeStocks(stocksStatus)<= MAX_PORTFOLIO_SIZE-1)//array is not full	
	  { 
			int logicalSizeStocks = getLogicalSizeStocks(stocksStatus);
			for (int i=0 ; i<logicalSizeStocks ; i++)
			{
				this.addStock(stocksStatus[i]) ; 
		
			}
				
	  }*/
			
	}
	
	public Portfolio (String title,StockStatus[] stocksStatus, float balance) throws PortfolioFullExceptions/*, StockAlreadyExistsException*/ {
		setTitle(title); 
		 this.stocksStatus=new StockStatus[MAX_PORTFOLIO_SIZE];
		updateBalance(balance);
		/* if(portfolioSize > 0 && portfolioSize<= MAX_PORTFOLIO_SIZE-1)//array is not full
			{
				for (int i = 0; i<portfolioSize; i++){
					this.addStock(stocksStatus[i]) ;  
					//stocksStatus[i] = new StockStatus(stockStatusP[i]) ;//fix it
				}
			}*/
		
	}
	
	//copy c'tor
	public Portfolio (Portfolio portfolio)throws StockAlreadyExistsException,PortfolioFullExceptions {
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

	/*private void copyStocksArray(Stock[] stocks, int size){// combine two next func to one in the copy c'tor
		
		if(size > 0 && size<= MAX_PORTFOLIO_SIZE-1)//array is not full
		{
		for (int i = 0; i<size; i++){//len dont give logical(3) but physical(5)->find func!
			this.addStock(stocks[i]);  
		}
		}
		else{
			
		}
	
	}*/
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
	

	

	public void addStock(Stock stock)throws StockAlreadyExistsException, PortfolioFullExceptions{
	
		
		for (int i=0; i<getPortfolioSize();i++) {
			if(stock.getSymbol().equals(stocksStatus[i].getSymbol())) {
				System.out.println("you already have such stock! cant add new one ");
				throw new StockAlreadyExistsException(stock.getSymbol());				
			}	
		}
			if(portfolioSize >= MAX_PORTFOLIO_SIZE) {
			System.out.println("stock array is full!");
			
			throw new PortfolioFullExceptions(MAX_PORTFOLIO_SIZE);
		}
		
		
		
		this.stocksStatus[portfolioSize]=new StockStatus(stock.getBid(), stock.getAsk(), stock.getDate(),stock.getSymbol() ,ALGO_RECOMMENDATION.DO_NOTHING,0);
		portfolioSize++;

		
		
		
	}


	

	
	
	public void removeStock(String symbol) throws StockNotExistException, BalanceException{
		
		
		/*boolean removeFlag=false;*/
		
		for (int i=0; i < portfolioSize ; i++){
			
			if ((this.stocksStatus[i].getSymbol()).equals(symbol)) //validfation, do we have such stock in portfolio?
			{	
				/*removeFlag=sellStock(this.stocksStatus[i].getSymbol(), -1);*/
				
				sellStock(this.stocksStatus[i].getSymbol(), -1);
				
				/*if(removeFlag){*/
				this.stocksStatus[i]=this.stocksStatus[portfolioSize-1];
				this.stocksStatus[i]=null;
				this.portfolioSize--;
				/*}*/
							
				
				
				
				
			}
			throw new StockNotExistException(symbol);
		}
		
	}
	

	
	public void sellStock(String symbol, int sellQuant) throws BalanceException, StockNotExistException{
		
		boolean sellFlag = false;
		
		if (sellQuant<-1){
			throw new BalanceException();
		}
		
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
		
		int i=0;
		
		for(i=0; i<portfolioSize; i++){
			if (this.stocksStatus[i].getSymbol().equals(symbol)){
				
			}
			if(i==portfolioSize){
				throw new StockNotExistException(symbol);
			}
		}
		
		for(i=0; i<portfolioSize; i++){
			if (sellQuant<=this.stocksStatus[i].getStockQuantity()){
				
			}
			if(i==portfolioSize){
				throw new BalanceException();
			}
		}
		
		
		
		/*if(!sellFlag)
			System.out.println("error! not valid");*/
		
	}
		
		
	
	
		
		
		 public void buyStock (String symbol, int buyQuant)throws BalanceException, StockNotExistException,PortfolioFullExceptions{
			 
			 if(buyQuant==0 || buyQuant<-1){
				 /*System.out.println("error, invalid quantity");*/
				 throw new BalanceException();
			 }
			 
			 if(buyQuant>0){
		 
		for(int i=0 ; i<portfolioSize ; i++){
		 if ((this.stocksStatus[i].getSymbol()).equals(symbol) && ((this.stocksStatus[i].getAsk())*buyQuant)<=this.getBalance()){
				
				this.updateBalance(-(this.stocksStatus[i].getAsk()*buyQuant));
				stocksStatus[i].setStockQuantity(buyQuant+this.stocksStatus[i].getStockQuantity());
				
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
				
					
				}
			}
			
		}
		
		int i=0;
		 for( i=0 ; i<portfolioSize ; i++){
			 if ((this.stocksStatus[i].getSymbol()).equals(symbol)){
				 
			 }
			 if (i==portfolioSize){
				 throw new StockNotExistException(this.stocksStatus[i].getSymbol());
			 }
		 }
		 
			i=0;
			 for( i=0 ; i<portfolioSize ; i++){
				 if(this.stocksStatus[i].getAsk()*buyQuant<=this.getBalance()){
				 }
				 if(i==portfolioSize){
					 throw new BalanceException() ;
				 	}
				 }
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
	
	
	
	
	public void setStocks(Stock[] stocks,int size) throws PortfolioFullExceptions,StockAlreadyExistsException  {
		for(int i=0; i<size;  i++)
		{
			try{
			this.addStock(stocks[i]);
			}catch(PortfolioFullExceptions e){
				System.out.println("portfolio is full!");
			}catch(StockAlreadyExistsException e){
				System.out.println("you already have such stock!");
			}
			}
			
		}
				
	}


	
	

	
	
	






	
	
	
	
	


