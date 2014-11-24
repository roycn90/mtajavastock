package com.roy;

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
		stockHtmlDetailsString = "<b>Stock symbol</b>: "+getSymbol()+" <b>Bid</b>: "+getBid() +"<b> Ask</b>:" +getAsk()+ "<b> Date</b>:"+getDate() +"" ;
		
		return stockHtmlDetailsString;
				
	}
	

}
