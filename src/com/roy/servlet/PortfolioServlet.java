package com.roy.servlet;

import java.io.IOException;

import javax.servlet.http.*;

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
		Portfolio portfolio = portfolioService.getPortfolio();
		//insert the stock
		Stock[] stocks = portfolio.getStocksStatus();

		resp.setContentType("text/html");
		resp.getWriter().println(portfolio.getHtmlString());
	}
	

}