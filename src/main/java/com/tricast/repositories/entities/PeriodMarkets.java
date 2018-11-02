package com.tricast.repositories.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PeriodMarkets implements Serializable{

	
		/**
	 * 
	 */
	private static final long serialVersionUID = -2350306662021994138L;
		private PeriodType period;
		private Market market;
		List <Outcome> outcomes;
		
		public PeriodMarkets(){
			period=new PeriodType();
			market=new Market();
			outcomes=new ArrayList<Outcome>();	
		}

		public PeriodType getPeriod() {
			return period;
		}

		public void setPeriod(PeriodType period) {
			this.period = period;
		}

		public Market getMarket() {
			return market;
		}

		public void setMarket(Market market) {
			this.market = market;
		}

		public List<Outcome> getOutcomes() {
			return outcomes;
		}

		public void setOutcomes(List<Outcome> outcomes) {
			this.outcomes = outcomes;
		}
		
	}