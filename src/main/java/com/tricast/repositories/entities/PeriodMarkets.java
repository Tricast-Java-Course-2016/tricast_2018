package com.tricast.repositories.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tricast.controllers.responses.MarketResponse;
import com.tricast.controllers.responses.OutcomeResponse;
import com.tricast.controllers.responses.PeriodTypeResponse;

public class PeriodMarkets implements Serializable{

	
		/**
	 * 
	 */
	private static final long serialVersionUID = -2350306662021994138L;
		private PeriodTypeResponse period;
		private MarketResponse market;
		List <OutcomeResponse> outcomes;
		
		public PeriodMarkets(){
			period=new PeriodTypeResponse();
			market=new MarketResponse();
			outcomes=new ArrayList<>();	
		}

		public PeriodTypeResponse getPeriod() {
			return period;
		}

		public void setPeriod(PeriodTypeResponse period) {
			this.period = period;
		}

		public MarketResponse getMarket() {
			return market;
		}

		public void setMarket(MarketResponse market) {
			this.market = market;
		}

		public List<OutcomeResponse> getOutcomes() {
			return outcomes;
		}

		public void setOutcomes(List<OutcomeResponse> outcomes) {
			this.outcomes = outcomes;
		}

	}