package com.utils;

import java.util.Properties;

public class ConfigLoaderUtil 
{
	private static Properties properties;
	private static ConfigLoaderUtil configLoader;
	
	private ConfigLoaderUtil()
	{
        properties = PropertyUtils.propertyLoader("./resources/config.properties");
    }
	
	public static ConfigLoaderUtil getInstance()
	{
        if(configLoader == null)
        {
            configLoader = new ConfigLoaderUtil();
        }
        return configLoader;
    }
	
	private static String getConfigProperty(String key)
	{
		return properties.getProperty(key);
	}
	
	public String getClientId()
	{
		String key = "client_id";
        String prop = getConfigProperty(key);
        if(prop != null) return prop;
        else throw new RuntimeException(key+" not found in the config file");
    }

    public String getClientSecret()
    {
        String key = "client_secret";
        String prop = getConfigProperty(key);
        if(prop != null) return prop;
        else throw new RuntimeException(key+" not found in the config file");
    }

    public String getGrantType()
    {
        String key = "grant_type";
        String prop = getConfigProperty(key);
        if(prop != null) return prop;
        else throw new RuntimeException(key+" not found in the config file");
    }

    public String getRefreshToken()
    {
    	String key = "refresh_token";
        String prop = getConfigProperty(key);
        if(prop != null) return prop;
        else throw new RuntimeException(key+" not found in the config file");
    }

    public String getUser()
    {
    	String key = "user_id";
        String prop = getConfigProperty(key);
        if(prop != null) return prop;
        else throw new RuntimeException(key+" not found in the config file");
    }
    
    public String getBaseUri()
    {
    	String key = "base_uri";
        String prop = getConfigProperty(key);
        if(prop != null) return prop;
        else throw new RuntimeException(key+" not found in the config file");
    }
    
    public String getAccountBaseUri()
    {
    	String key = "account_base_uri";
        String prop = getConfigProperty(key);
        if(prop != null) return prop;
        else throw new RuntimeException(key+" not found in the config file");
    }
    
    public String getLogFilePath()
    {
    	String key = "log_file";
        String prop = getConfigProperty(key);
        if(prop != null) return prop;
        else throw new RuntimeException(key+" not found in the config file");
    }
}
