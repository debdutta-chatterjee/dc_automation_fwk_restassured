package com.utils;

import java.io.BufferedReader;
import java.util.Properties;
import java.io.FileReader;


public class PropertyUtils 
{
	
	public static Properties propertyLoader(String path) 
	{
		Properties prop = new Properties();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			prop.load(reader);
			reader.close();
		}catch(Exception ex) 
		{
			 throw new RuntimeException("Unable to load property from path- " + path);
		}
		return prop;
	}
	
}
