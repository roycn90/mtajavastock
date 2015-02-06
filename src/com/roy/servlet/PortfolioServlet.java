package com.roy.servlet;

import com.roy.dto.PortfolioDto;
import com.roy.dto.PortfolioTotalStatus;
import com.roy.exceptions.PortfolioFullException;
import com.roy.exceptions.StockAlreadyExistsException;
import com.roy.model.StockStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PortfolioServlet extends AbstractAlgoServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
		
		PortfolioTotalStatus[] totalStatus = null;
		try {
			totalStatus = portfolioService.getPortfolioTotalStatus();
		} catch (StockAlreadyExistsException e) {
		resp.getWriter().print(e.getMessage());
		} catch (PortfolioFullException e) {
			resp.getWriter().print(e.getMessage());
		}
		StockStatus[] stockStatusArray = null;
		try {
			stockStatusArray = portfolioService.getPortfolio().getStocksStatus();
		} catch (StockAlreadyExistsException e) {
			resp.getWriter().print(e.getMessage());;
		} catch (PortfolioFullException e) {
			resp.getWriter().print(e.getMessage());
		}
		List<StockStatus> stockStatusList = new ArrayList<>();
		for (StockStatus ss : stockStatusArray) {
			if(ss != null)
				stockStatusList.add(ss);
		}
		
		PortfolioDto pDto = new PortfolioDto();
		try {
			pDto.setTitle(portfolioService.getPortfolio().getTitle());
		} catch (StockAlreadyExistsException e) {
			resp.getWriter().print(e.getMessage());
			e.printStackTrace();
		} catch (PortfolioFullException e) {
			resp.getWriter().print(e.getMessage());
		}
		pDto.setTotalStatus(totalStatus);
		pDto.setStockTable(stockStatusList);
		resp.getWriter().print(withNullObjects().toJson(pDto));
	}
}