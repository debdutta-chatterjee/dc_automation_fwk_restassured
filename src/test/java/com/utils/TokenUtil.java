package com.utils;

import java.time.Instant;
import java.util.HashMap;

import io.restassured.response.Response;

public class TokenUtil {
	private static ConfigLoaderUtil config = ConfigLoaderUtil.getInstance();
	private static String access_token;
    private static Instant expiry_time;

	public synchronized static String getToken() {
		try {
			if (access_token == null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Token not valid. renewing!");
				Response response = renewToken();
				access_token = response.path("access_token");
				int expiryDurationInSeconds = response.path("expires_in");
				expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 480);
			} else {
				System.out.println("Token is alive");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get a new token.");
		}
		return access_token;
	}

	private static Response renewToken() {
		HashMap<String, String> formParams = new HashMap<String, String>();
		formParams.put("client_id", config.getClientId());
		formParams.put("client_secret", config.getClientSecret());
		formParams.put("refresh_token", config.getRefreshToken());
		formParams.put("grant_type", config.getGrantType());

		Response response = SpotifyWebServiceUtil.postAccount(formParams);

		if (response.statusCode() != 200) {
			throw new RuntimeException("Token couldnot be renewed");
		}
		return response;
	}
}
