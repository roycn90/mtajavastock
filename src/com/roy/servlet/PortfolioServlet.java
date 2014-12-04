package com.roy.servlet;

import java.io.IOException;

import javax.servlet.http.*;

import com.roy.Stock;
import com.roy.model.Portfolio;
import com.roy.service.PortfolioService;


@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//create instance
		PortfolioService portfolioService = new PortfolioService(); 
		//take the stocks
		Portfolio portfolio = portfolioService.getPortfolio();
		//insert the stock
		Stock[] stocks = portfolio.getStocks();

		resp.setContentType("text/html");
		resp.getWriter().println(portfolio.getHtmlDescription());
	}
	

}