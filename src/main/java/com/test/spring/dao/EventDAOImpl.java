package com.test.spring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.test.model.DBEvent;

@Repository
@Transactional
public class EventDAOImpl {
	@PersistenceContext
	private EntityManager entityManager;// = (EntityManager) DatabaseConfig.entityManagerFactory();

	public void create(DBEvent event) {
		entityManager.persist(event);
	}

	public void update(DBEvent event) {
		entityManager.merge(event);
	}

	public DBEvent getEventById(String id) {
		return entityManager.find(DBEvent.class, id);
	}

	public List<DBEvent> getEvents() {
		TypedQuery<DBEvent> events = entityManager.createNamedQuery("findEvents", DBEvent.class);
		List<DBEvent> eventlist = new ArrayList<DBEvent>();
		for (int i = 0; i < events.getResultList().size(); i++) {
			eventlist.add(events.getResultList().get(i));
		}
		return eventlist;
	}
}
