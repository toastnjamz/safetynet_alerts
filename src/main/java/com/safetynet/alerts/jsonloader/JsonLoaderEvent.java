package com.safetynet.alerts.jsonloader;

import org.springframework.context.ApplicationEvent;

public class JsonLoaderEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private String eventType;
	private JsonLoader jsonLoader;
	
	//FIXME: Is it possible to send "this" once?
	public JsonLoaderEvent(Object source, String eventType, JsonLoader jsonLoader) {
		super(source);
		this.eventType = eventType;
		this.jsonLoader = jsonLoader;
	}
	
	public String getEventType() {
		return eventType;
	}
	
	public JsonLoader getJsonLoader() {
		return jsonLoader;
	}
}