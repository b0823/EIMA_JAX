package com.EIMA.models;

public class EIMAAlert {
	private String message;
	private String sender;
	private long timestamp;

	public EIMAAlert(String message, String sender, long timestamp) {
		super();
		this.message = message;
		this.sender = sender;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
