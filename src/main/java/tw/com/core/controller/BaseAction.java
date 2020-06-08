package tw.com.core.controller;

import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseAction {
	private final static Logger logger = LoggerFactory.getLogger(BaseAction.class);
	Properties properties = new Properties();
	String configFile = "config.properties";
	
	
	public String getPropertis(String name, String defaultValue) {
		String reData = "";
		try {
//			properties.load(new FileInputStream(configFile));
			properties.load(getClass().getClassLoader().getResourceAsStream(configFile));
			reData = properties.getProperty(name, defaultValue);
		} catch (Exception e) {
			logger.error("Class exception: " + e);
		}
		return reData;
	}
	public String getPropertis(String name) {
		return getPropertis(name, "");
	}
}
