package mk.mladen.avtobusi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.wicket.session.ISessionStore.UnboundListener;


public class SessionUnbound implements UnboundListener {
	
	private static final Logger logger = LogManager.getLogger(SessionUnbound.class);

	@Override
	public void sessionUnbound(String sessionId) {
		logger.info("session unbound " + sessionId);
		
	}

}
