package com.test.route;

import javax.transaction.Transactional;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.model.DBEvent;
import com.test.model.FileEvent;
import com.test.spring.dao.EventDAOImpl;

@Repository
@Transactional
public class ProcessData {

	@Autowired
	private EventDAOImpl eventDAOImpl; // = new EventServiceImpl();

	public void processRecords(Exchange exchange) throws Exception {
		FileEvent fileEvent = exchange.getIn().getBody(FileEvent.class);

		System.out.println(fileEvent.getId() + "\n");

		switch (fileEvent.getState()) {
		case "STARTED":
			DBEvent dbEvent = new DBEvent();

			dbEvent.setId(fileEvent.getId());
			dbEvent.setState("STARTED");
			dbEvent.setStart_duration(fileEvent.getTimestamp());
			dbEvent.setType(fileEvent.getType());
			dbEvent.setHost(fileEvent.getHost());

			eventDAOImpl.create(dbEvent);
			break;
		case "FINISHED":
			DBEvent dbEvent2 = eventDAOImpl.getEventById(fileEvent.getId());

			dbEvent2.setState("FINISHED");
			dbEvent2.setEnd_duration(fileEvent.getTimestamp());
			dbEvent2.setDuration(dbEvent2.getEnd_duration() - dbEvent2.getStart_duration());
			dbEvent2.setType(fileEvent.getType());
			dbEvent2.setHost(fileEvent.getHost());

			if (dbEvent2.getDuration() > 4) {
				dbEvent2.setAlert(true);
			} else {
				dbEvent2.setAlert(false);
			}
			eventDAOImpl.update(dbEvent2);

			break;
		}
	}

}
