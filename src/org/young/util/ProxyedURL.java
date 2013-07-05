package org.young.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 * Java程序，使用代理连接外网
 *
 * @author by Young.ZHU
 *		on 2013-7-3
 *
 * Package&FileName: org.young.util.ProxyedURL
 */
public class ProxyedURL {
	
	private static final String PROXY_HOST = "PROXY_HOST";
	private static final String PROXY_PORT = "PROXY_PORT";
	private static final String PROXY_USERNAME = "PROXY_USERNAME";
	private static final String PROXY_PASSWORD = "PROXY_PASSWORD";

	public void readStream(String urlStr) {
		setProxy(true);
		
		InputStream is = null;
		BufferedReader br = null;
		String line = null;
		try {
			URL url = new URL(urlStr);
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is, "utf8"));
			
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * 
	 * @param isNeedAuthenticated - 是否需要用户名/密码
	 */
	private void setProxy(boolean isNeedAuthenticated) {
		System.setProperty("proxyHost", PROXY_HOST);
		System.setProperty("proxyPort", PROXY_PORT);
		
		/*
		 * 需要用户名/密码时，下面的方法是没有用的
		 * System.setProperty("proxyUser", PROXY_USERNAME);
         * System.setProperty("proxyPassword", PROXY_PASSWORD);
		 */
		
		if (isNeedAuthenticated) {
			Authenticator.setDefault(new BasicAuthenticator(PROXY_USERNAME, PROXY_PASSWORD));
		}
	}
	
	class BasicAuthenticator extends Authenticator {  
	    private String userName;  
	    private String password;  
	  
	    public BasicAuthenticator(String userName, String password) {  
	        this.userName = userName;  
	        this.password = password;  
	    }  
	  
	    /** 
	     * Called when password authorization is needed.  Subclasses should 
	     * override the default implementation, which returns null. 
	     * 
	     * @return The PasswordAuthentication collected from the 
	     *         user, or null if none is provided. 
	     */  
	    @Override  
	    protected PasswordAuthentication getPasswordAuthentication() {  
	        return new PasswordAuthentication(userName, password.toCharArray());  
	    }  
	} 

}
