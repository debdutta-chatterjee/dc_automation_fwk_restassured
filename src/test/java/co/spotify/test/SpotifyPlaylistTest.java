package co.spotify.test;


import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.spotify.api.SpotifyPlaylistApis;
import com.spotify.api.StatusCode;
import com.spotify.jsonmodel.ErrorResponse;
import com.spotify.jsonmodel.Playlist;
import static com.utils.FakerUtils.generateDescription;
import static com.utils.FakerUtils.generateName;

import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SpotifyPlaylistTest extends Spotify_BaseTest
{
	@Test(description = "should be able to create a playlist")
    public void ShouldBeAbleToCreateAPlaylist(ITestContext context){
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = SpotifyPlaylistApis.post(requestPlaylist,methodName);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        Playlist playListRes = response.as(Playlist.class);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
        context.setAttribute("playlistId", playListRes.getId());
        context.setAttribute("playlistName", playListRes.getName());
        context.setAttribute("playlistDesc", playListRes.getDescription());
    }

    @Test(dependsOnMethods = {"ShouldBeAbleToCreateAPlaylist"})
    public void ShouldBeAbleToGetAPlaylist(ITestContext context){
        Playlist requestPlaylist = playlistBuilder(context.getAttribute("playlistName").toString(), context.getAttribute("playlistDesc").toString(), false);
        Response response = SpotifyPlaylistApis.get(context.getAttribute("playlistId").toString(),methodName);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test(dependsOnMethods = {"ShouldBeAbleToCreateAPlaylist"})
    public void ShouldBeAbleToUpdateAPlaylist(ITestContext context){
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = SpotifyPlaylistApis.update(context.getAttribute("playlistId").toString(), requestPlaylist,methodName);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithName(ITestContext context){
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = SpotifyPlaylistApis.post(requestPlaylist,methodName);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertError(response.as(ErrorResponse.class), StatusCode.CODE_400);
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(ITestContext context){
        String invalid_token = "12345";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = SpotifyPlaylistApis.post(invalid_token, requestPlaylist,methodName);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
        assertError(response.as(ErrorResponse.class), StatusCode.CODE_401);
    }

    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }

    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist)
    {
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }

    public void assertStatusCode(int actualStatusCode, StatusCode statusCode)
    {
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

    public void assertError(ErrorResponse responseErr, StatusCode statusCode)
    {
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
    }
	
}
