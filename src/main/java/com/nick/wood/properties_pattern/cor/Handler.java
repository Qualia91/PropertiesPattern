package com.nick.wood.properties_pattern.cor;

public abstract class Handler {
	protected Handler successor;
	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}
	public abstract Object handleRequest(Request request);
}
