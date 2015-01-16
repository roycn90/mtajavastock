package com.roy.servlet;

import java.io.IOException;

import javax.servlet.http.*;

import com.roy.exceptions.BalanceException;
import com.roy.exceptions.StockNotExistException;
import com.roy.exceptions.PortfolioFullExceptions;
import com.roy.exceptions.StockAlreadyExistsException;
import com.roy.model.Portfolio;
import com.roy.model.Stock;
import com.roy.service.PortfolioService;


@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//create instance
		PortfolioService portfolioService = new PortfolioService(); 
		//take the stocks
		Portfolio portfolio;

		try{
		 portfolio = portfolioService.getPortfolio();
		}
		catch(Exception e)
		{
			resp.getWriter().println(e.getMessage());
		}

		//insert the stock
		


		resp.setContentType("text/html");
		/*resp.getWriter().println(portfolio.getHtmlString());*/
	}
	

}