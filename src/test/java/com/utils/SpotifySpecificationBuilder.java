package com.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpotifySpecificationBuilder 
{
	private static ConfigLoaderUtil config = ConfigLoaderUtil.getInstance();
	
	public static RequestSpecification getRequestSpec(String testCaseName)
	{
		PrintStream ps = getLogFile(testCaseName);
        return new RequestSpecBuilder().
        		addFilter(new RequestLoggingFilter(ps)).
        		addFilter(new ResponseLoggingFilter(ps)).
                setBaseUri(config.getBaseUri()).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }
	
	public static RequestSpecification getAccountRequestSpec()
	{
        return new RequestSpecBuilder().
                setBaseUri(config.getAccountBaseUri()).
                setContentType(ContentType.URLENC).
                log(LogDetail.ALL).
                build();
    }
	
	public static ResponseSpecification getResponseSpec()
	{
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
	
	private static PrintStream getLogFile(String testCaseName)
	{
		String pathFormat = config.getLogFilePath();
		String pathFormatDTS = pathFormat.substring(pathFormat.indexOf("<"),pathFormat.indexOf(">"));
		long time = System.currentTimeMillis();
		pathFormat =  pathFormat.replaceFirst(pathFormatDTS, testCaseName+"_"+String.valueOf(time)).replaceAll(">", "");
		try 
		{
			return new PrintStream(new File(pathFormat));
		} 
		catch (FileNotFoundException e) 
		{
			throw new RuntimeException("Unable to create log file.");
		}
	}
}
