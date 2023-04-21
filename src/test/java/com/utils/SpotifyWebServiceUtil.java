package com.utils;


import static com.utils.SpotifySpecificationBuilder.getAccountRequestSpec;
import static com.utils.SpotifySpecificationBuilder.getRequestSpec;
import static com.utils.SpotifySpecificationBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.config.RestAssuredConfig.config;

import java.util.HashMap;


import com.spotify.endpoints.SpotifyEndpoint;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

public class SpotifyWebServiceUtil 
{
	public static Response post(Object reqBody,String token,String path, String testCaseName)
	{
		return RestAssured.
		given(getRequestSpec(testCaseName)).
			//config(config().logConfig(logConfig().blacklistHeaders(null))).
			body(reqBody).
			auth().oauth2(token).
		when().
			post(path).
		then().
			spec(getResponseSpec()).
			extract().
			response()
		;
	}
		
	 public static Response postAccount(HashMap<String, String> form)
	 {
	        return given(getAccountRequestSpec()).
	                formParams(form).
	        when()
	        	.post(SpotifyEndpoint.tokenEndpoint).
	        then().spec(getResponseSpec()).
	                extract().
	                response();
	    }

	    public static Response get(String path, String token, String testCaseName)
	    {
	        return given(getRequestSpec(testCaseName)).
	                auth().oauth2(token).
	        when().get(path).
	        then().spec(getResponseSpec()).
	                extract().
	                response();
	    }

	    public static Response update(String path, String token, Object requestPlaylist, String testCaseName)
	    {
	        return given(getRequestSpec(testCaseName)).
	                auth().oauth2(token).
	                body(requestPlaylist).
	        when().put(path).
	        then().spec(getResponseSpec()).
	                extract().
	                response();
	    }
}
