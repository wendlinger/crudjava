package com.crudjava.util;

import com.crudjava.enums.SeverityEnum;

public class MessageAPI {

	public String text;
	public SeverityEnum severity;

	public MessageAPI() {
		super();
	}

	public MessageAPI(String text, SeverityEnum severity) {
		super();
		this.text = text;
		this.severity = severity;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public SeverityEnum getSeverity() {
		return severity;
	}

	public void setSeverity(SeverityEnum severity) {
		this.severity = severity;
	}
}
