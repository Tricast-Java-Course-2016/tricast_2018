package com.tricast.repositories.customs.impl;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.tricast.repositories.customs.TransactionRepositoryCustom;
import com.tricast.repositories.entities.Outcome;


public class OutcomeRepositoryCustomImpl extends QueryDslRepositorySupport  implements OutcomeRepositoryCustom{ 
	public OutcomeRepositoryCustomImpl() {
		super(Outcome.class);		
	}

  
}
