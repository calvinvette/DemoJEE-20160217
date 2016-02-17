package com.weasley.web.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ReusableSessionListener
 *
 */
//@WebListener
public class ReusableSessionListener implements HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public ReusableSessionListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		System.out.println("User: " + getUserIdCookie(getRequest(httpSessionEvent)) + " : Restoring Session#" + session.getId());
	}

	public String getUserIdCookie(HttpServletRequest request) {
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("userId")) {
				return cookie.getValue();
			}
		}
		return null;
	}

	//			java.util.UUID.randomUUID();
	
	public HttpServletRequest getRequest(HttpSessionEvent httpSessionEvent) {
		if (httpSessionEvent.getSource() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) httpSessionEvent.getSource();
			return request;
		}
		return null;
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		System.out.println("Session#" + session.getId() + " UserID: " + getRequest(httpSessionEvent));
		while (session.getAttributeNames().hasMoreElements()) {
			// fake DB store
			String attrName = session.getAttributeNames().nextElement();
			System.out.print(attrName + "=" + session.getAttribute(attrName));
			if (session.getAttributeNames().hasMoreElements()) {
				System.out.print("; ");
			} else {
				System.out.println();
			}
		}

	}

}
