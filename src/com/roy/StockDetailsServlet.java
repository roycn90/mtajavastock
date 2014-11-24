package com.roy;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Calendar;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		Date date=new Date();
	
		Calendar c= Calendar.getInstance();
		c.set(2014, 10, 15);
		date = c.getTime();
		
	Stock stock1=new Stock();
	Stock stock2=new Stock();
	Stock stock3=new Stock();
	
	stock1.setSymbol("PIH");
	stock1.setAsk((float)12.4);
	stock1.setBid((float)13.1);
	stock1.setDate(date);
	
	stock2.setSymbol("AAL");
	stock2.setAsk((float)5.5);
	stock2.setBid((float)5.78);
	stock2.setDate(date);
	
	stock3.setSymbol("CAAS");
	stock3.setAsk((float)31.5);
	stock3.setBid((float)31.2);
	stock3.setDate(date);
	
	resp.setContentType("text/html");

	resp.getWriter().println(stock1.getHtmlDescription() +"<br>" +	stock2.getHtmlDescription()+ "<br>"+	stock3.getHtmlDescription());
	
	
	

	}
		}
