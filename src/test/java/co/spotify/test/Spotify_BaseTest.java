package co.spotify.test;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;

public class Spotify_BaseTest 
{
	protected String methodName;
	@BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("STARTING TEST: " + m.getName());
        System.out.println("THREAD ID: " + Thread.currentThread().getId());
        methodName = m.getName();
    }
}
