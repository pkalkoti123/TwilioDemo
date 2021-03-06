package com.twilio.demo.model;

/**
 * Model object for interaction
 * @author pkalkoti003
 *
 */
public class GreetingMessage {

	private String toNumber;
	private String message;

	public GreetingMessage() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToNumber() {
		return toNumber;
	}
	
	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}

	
	@Override
	public String toString() {

		return "message :'"+message+"' toNumber : '"+toNumber+"'";
	}
}
