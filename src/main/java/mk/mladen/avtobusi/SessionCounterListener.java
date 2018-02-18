package mk.mladen.avtobusi;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionCounterListener implements HttpSessionListener {

	private static Logger logger = LogManager.getLogger(SessionCounterListener.class);

	private static int totalActiveSessions;

	public static int getTotalActiveSession() {
		return totalActiveSessions;
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		totalActiveSessions++;
		System.out.println("sessionCreated - add one session into counter");
		logger.info("sessionCreated - add one session into counter");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		totalActiveSessions--;
		System.out.println("sessionDestroyed - deduct one session from counter");
		logger.info("sessionDestroyed - deduct one session from counter");
	}
	
}