package com.roy.model;

import java.util.Date;
import java.util.Calendar;


/**
 * @author Roi
 *
 */
public class Stock {
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	
	public Stock(){
		String symbol="";
		float ask=0;
		float bid=0;
		date=new Date ();
	}
	
	//c'tor
public Stock (String symbol, float ask, float bid, Date date)
{
	setSymbol(symbol); setAsk(ask); setBid(bid); setDate(date);
}
		//copy c'tor
	public Stock (Stock stock)
	{
		this(stock.getSymbol(), stock.getAsk(), stock.getBid(), stock.getDate());
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getHtmlDescription(){
		
		String stockHtmlDetailsString;
		stockHtmlDetailsString = "<b>Stock symbol</b>: "+getSymbol()+" <b>Bid</b>: "+getBid() +"<b> Ask</b>:" +getAsk()+ "<b> Date</b>:"+getDate() +"<br>" ;
		
		return stockHtmlDetailsString;
				
	}
	

}
