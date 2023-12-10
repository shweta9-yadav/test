package com.example.product.configuration;

public class MessageStatus<T> {

	private int stausCode;
	private String message;
	
	
	public MessageStatus() {
		super();
	}
	public MessageStatus(String message,int stausCode) {
		super();
		this.stausCode = stausCode;
		this.message = message;
	}
	
	
	public int getStausCode() {
		return stausCode;
	}
	public void setStausCode(int stausCode) {
		this.stausCode = stausCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "MessageStatus [stausCode=" + stausCode + ", message=" + message + "]";
	}
	
}
