package com.spotify.api;

import io.restassured.response.Response;

import com.spotify.endpoints.SpotifyEndpoint;
import com.spotify.jsonmodel.Playlist;
import com.utils.ConfigLoaderUtil;
import com.utils.SpotifyWebServiceUtil;
import com.utils.TokenUtil;

public class SpotifyPlaylistApis 
{
	private static ConfigLoaderUtil config = ConfigLoaderUtil.getInstance();
	
	public static Response post(Playlist requestPlaylist, String testCaseName)
	{
        return SpotifyWebServiceUtil.post(requestPlaylist, TokenUtil.getToken(),SpotifyEndpoint.postPlaylistsEndpoint(config.getUser()),testCaseName);
    }

    public static Response post(String token, Playlist requestPlaylist, String testCaseName)
    {
    	return SpotifyWebServiceUtil.post(requestPlaylist, token, SpotifyEndpoint.postPlaylistsEndpoint(config.getUser()),testCaseName);
    }

    public static Response get(String playlistId, String testCaseName)
    {
    	return SpotifyWebServiceUtil.get(SpotifyEndpoint.getPlaylistsEndpoint(playlistId), TokenUtil.getToken(),testCaseName);
    }

    public static Response update(String playlistId, Playlist requestPlaylist, String testCaseName)
    {
        return SpotifyWebServiceUtil.update(SpotifyEndpoint.getPlaylistsEndpoint(playlistId), TokenUtil.getToken(), requestPlaylist,testCaseName);
    }
}
