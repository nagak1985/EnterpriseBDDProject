package com.cucumber.helper;

import java.util.HashMap;
import java.util.Map;

public class ParameterService {

	private static Map<String, String> mapName = new HashMap<String, String>();  

	public static void setParameter(String parameterName, String parameterValue) {  
		mapName.put(parameterName, parameterValue);
	}

	public static String getParameter(String parameterName) {
		return mapName.get(parameterName);
	}
}