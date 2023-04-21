package com.spotify.endpoints;

public class SpotifyEndpoint 
{
	public static final String getPlaylistsEndpoint(String playlist_id) 
	{
		return "/v1/playlists/"+playlist_id+"";
	}
	
	public static final String postPlaylistsEndpoint(String user_id) 
	{
		return "v1/users/"+user_id+"/playlists";
	}
	
	
	public static final String tokenEndpoint ="/api/token";
	
	public static final String userEndpoint (String user_id)
	{
		return "/v1/users/"+user_id+"/playlists";
	}
}
