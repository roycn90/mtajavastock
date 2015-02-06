package com.roy.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roy.exceptions.PortfolioFullException;
import com.roy.exceptions.StockAlreadyExistsException;

/**
 * @author hanan.gitliz@gmail.com
 * @since Jan 12, 2015
 */
@SuppressWarnings("serial")
public class CronServlet extends AbstractAlgoServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		try {
			portfolioService.update();
		} catch (StockAlreadyExistsException e) {
			resp.getWriter().print(e.getMessage());
		} catch (PortfolioFullException e) {
			resp.getWriter().print(e.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
}