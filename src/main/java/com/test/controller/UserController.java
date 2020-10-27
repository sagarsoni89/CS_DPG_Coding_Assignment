package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.DBEvent;
import com.test.spring.dao.EventDAOImpl;

//@Controller
@RestController
public class UserController {
	@Autowired
	private EventDAOImpl eventDAOImpl;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcome(Model model) {
       // return("Hello World. This is produced by a method annotated with ResponseBody");

		List<DBEvent> events = eventDAOImpl.getEvents();
		//model.addAttribute("eventList", events);
		
		String html = "<html><body><table border=1>";
		html += "<tr><td>id</td><td>state</td><td>duration</td><td>type</td><td>host</td><td>alert</td></tr>";
		for(DBEvent event : events)
		{
			html += "<tr><td>" + event.getId() + "</td><td>" + event.getState() + "</td><td>" + event.getDuration() + "</td><td>" + event.getType() + "</td><td>" + event.getHost() +"</td><td>" + event.getAlert() + "</td></tr>";
		}
		html += "</table></body></html>";
		
		return html;
	}
}