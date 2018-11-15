package com.tricast.repositories.customs.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.tricast.repositories.customs.EventRepositoryCustom;
import com.tricast.repositories.entities.Event;
import com.tricast.repositories.entities.EventStatusTypes;
import com.tricast.repositories.entities.QEvent;

@Repository
public class EventRepositoryCustomImpl extends QueryDslRepositorySupport implements EventRepositoryCustom {

    public EventRepositoryCustomImpl() {
        super(Event.class);
    }

    @Override
    public List<Event> filter(String search, Long sportId, Long leagueId, Calendar fromDate, Calendar toDate) {

        QEvent event = QEvent.event;

        BooleanExpression dateFilter = event.startTime.between(fromDate, toDate);

        JPQLQuery<Event> query = from(event).where(dateFilter);

        if (StringUtils.hasText(search)) {
            BooleanExpression descriptionFilter = event.description.likeIgnoreCase("%" + search + "%");
            query = query.where(descriptionFilter);
        }

        if (sportId != null) {
            BooleanExpression sportFilter = event.league().sportId.eq(sportId);
            query = query.where(sportFilter);
        }

        if (leagueId != null) {
            BooleanExpression leagueFilter = event.league().id.eq(leagueId);
            query = query.where(leagueFilter);
        }

        return query.fetch();
    }
    
    @Override
    public List <Event> listOpen(){
    	QEvent event = QEvent.event;


        JPQLQuery<Event> query = from(event);
        
        BooleanExpression statusFilter = event.status.eq(EventStatusTypes.OPEN);
        query = query.where(statusFilter);
        
        return query.fetch();
        		
    }
}
