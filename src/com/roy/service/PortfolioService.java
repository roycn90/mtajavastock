package com.roy.service;

import java.util.Calendar;

import java.util.Date;

import com.roy.exceptions.BalanceException;
import com.roy.exceptions.PortfolioFullExceptions;
import com.roy.exceptions.StockAlreadyExistsException;
import com.roy.exceptions.StockNotExistException;
import com.roy.model.Portfolio;
import com.roy.model.Stock;
import com.roy.model.StockStatus;

public class PortfolioService {
	
	public Portfolio getPortfolio ()throws BalanceException, PortfolioFullExceptions, StockAlreadyExistsException,StockNotExistException {
		
		Date date=new Date();
		
		Calendar c= Calendar.getInstance();
		c.set(2014, 10, 15);
		date = c.getTime();
		
		 Portfolio myPortfolio =new Portfolio();
		

		
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
		
		Stock stock4 = new Stock();
		stock4.setSymbol("CAAS");
		stock4.setAsk((float)20.0);
		stock4.setBid((float)15.5);
		stock4.setDate(date);
		
		
		try{
		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);
		myPortfolio.addStock(stock4);
		}catch(StockAlreadyExistsException e){
			System.out.println("error! portfolio full exception!");
		}catch (PortfolioFullExceptions e){
			System.out.println("error! stock alreay exist exception!");
		
		}
		
		
		myPortfolio.setTitle("Exercise 7 portfolio - after Ex. 8 modification");
		
		myPortfolio.updateBalance(10000);
		
		try{
		myPortfolio.buyStock("PIH",20);
		myPortfolio.buyStock("AAL",30);
		myPortfolio.buyStock("CAAS",40);
		} catch (BalanceException e){
			System.out.println("error! balance exception");
		}catch (StockNotExistException e){
				System.out.println("error! stock not exist exception!");
			}
			{
		
		try{
		myPortfolio.sellStock("AAL", -1);
		}catch (BalanceException e){
			System.out.println("error! balance exception!");
		}catch (StockNotExistException e){
			System.out.println("error! stock not exist exception!");

			}
		}
		try{
		myPortfolio.removeStock("CAAS");
		}catch(BalanceException e){
			System.out.println("error! balance exception!");
		}catch(StockNotExistException e){
			System.out.println("error! stock not exist exception!");
			
		}
		
		return myPortfolio;
		
	}
	


}
