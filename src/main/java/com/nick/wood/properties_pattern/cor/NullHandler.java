package com.nick.wood.properties_pattern.cor;

import com.nick.wood.properties_pattern.PropertyMetaData;

public class NullHandler extends Handler {

	private final Handler sub;

	public NullHandler(Handler sub) {
		this.sub = sub;
	}

	@Override
	public Object handleRequest(Request request) {
		if (request.getPropertiesMap().get(request.getName()) == null ||
				request.getPropertiesMap().get(request.getName()).getPropertyMetaDataList().contains(PropertyMetaData.NULL)) {
			return null;
		} else {
			if (sub != null) {
				return sub.handleRequest(request);
			}
		}
		return request.getPropertiesMap().get(request.getName());
	}
}
