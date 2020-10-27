package com.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "findEvents", query = "from DBEvent") })

@Entity
@Table(name = "application_event")
public class DBEvent {

	@Id
	private String id;
	@Column(name = "state")
	private String state;
	@Column(name = "start_duration")
	private Long start_duration;
	@Column(name = "end_duration")
	private Long end_duration;
	@Column(name = "duration")
	private Long duration;
	@Column(name = "type")
	private String type;
	@Column(name = "host")
	private String host;
	@Column(name = "alert")
	private Boolean alert;

	public DBEvent() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getStart_duration() {
		return start_duration;
	}

	public void setStart_duration(Long start_duration) {
		this.start_duration = start_duration;
	}

	public Long getEnd_duration() {
		return end_duration;
	}

	public void setEnd_duration(Long end_duration) {
		this.end_duration = end_duration;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

}
