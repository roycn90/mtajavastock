package com.roy.model;

import java.util.Date;
import com.roy.model.Portfolio.ALGO_RECOMMENDATION;


	
public class StockStatus extends Stock{
		
				
		private ALGO_RECOMMENDATION recommendation;
		private int stockQuantity;
		
		//c'tor
		public StockStatus(){
			super();
			recommendation= ALGO_RECOMMENDATION.DO_NOTHING;
			stockQuantity=0;
		}
		
		//c'tor
		
		public StockStatus( float bid, float ask, Date date, String symbol, ALGO_RECOMMENDATION recommendation, int stockQuantity  ){
			
			super(symbol, ask, bid, date);
			setSymbol(symbol);
			setBid(bid);
			setAsk(ask);
			setDate(date);
			setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			setStockQuantity(stockQuantity);
	
			
		}
		
		//copy c'tor
		
		public StockStatus (StockStatus stockStatus) {
			this(stockStatus.getBid(), stockStatus.getAsk(), stockStatus.getDate(), stockStatus.getSymbol(),
					stockStatus.getRecommendation(), stockStatus.getStockQuantity() );
		}
		
		public StockStatus(Stock otherStock) {
			super(otherStock);
			setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			setStockQuantity(0);
			setAsk(otherStock.getAsk());
			setBid(otherStock.getBid());
		}
		
	
		 
		


		 
		 //geters and setters

		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
			this.recommendation = recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	
		 
	

}
