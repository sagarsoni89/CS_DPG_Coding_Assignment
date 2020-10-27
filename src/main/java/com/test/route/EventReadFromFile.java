package com.test.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.test.model.FileEvent;

@Component
public class EventReadFromFile extends RouteBuilder {
	@Autowired
	private Environment env;
	
	@Override
	public void configure() throws Exception {
		//Processor myProcessor = new ProcessData();
		String fileName = env.getProperty("fileName");
		from("stream:file?fileName=" + fileName + "&scanStream=true")
				.unmarshal().json(JsonLibrary.Jackson, FileEvent.class) 
				.bean(ProcessData.class,"processRecords")
			.to("stream:out");

	}
}
