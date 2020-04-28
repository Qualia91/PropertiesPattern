package com.nick.wood.properties_pattern.cor;

import com.nick.wood.properties_pattern.PropertyMetaData;

public class FinalHandler extends Handler {

	private final Handler sub;

	public FinalHandler(Handler sub) {
		this.sub = sub;
	}

	@Override
	public Object handleRequest(Request request) {
		if (request.getPropertiesMap().get(request.getName()).getPropertyMetaDataList().contains(PropertyMetaData.FINAL)) {
			return sub.handleRequest(request);
		} else {
			return sub.handleRequest(request);
		}
	}

}
