package mk.mladen.avtobusi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ISessionListener;
import org.apache.wicket.Session;

public class MySessionListener implements ISessionListener {
	
	private static Logger logger = LogManager.getLogger(MySessionListener.class);

	@Override
	public void onUnbound(String sessionId) {
		logger.info("unbound session {}", sessionId);
	}
	
	@Override
	public void onCreated(Session session) {
		logger.info("created session {}", session.getId());
	}
}
