package com.slalom.core.components;

import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUsePojo;

public class SlalomTitle extends WCMUsePojo {
	
	private String message;

	@Override
	public void activate() throws Exception {
		ValueMap properties = getProperties();
		message = ("No title set");
		if (properties.containsKey("title")) {
			message = (properties.get("title", String.class));
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}