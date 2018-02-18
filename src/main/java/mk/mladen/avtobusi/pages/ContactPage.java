package mk.mladen.avtobusi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

import mk.mladen.avtobusi.security.AuthenticatedSession;

public class ContactPage extends BasePage {
	
	private static Logger logger = LogManager.getLogger(ContactPage.class);

	private static final long serialVersionUID = 1L;

	public ContactPage(PageParameters parameters) {
		super(parameters);
		
		add(new AjaxEventBehavior("beforeunload") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				AuthenticatedSession session = (AuthenticatedSession)getSession();
				logger.info("before unload event session = " + session.getId());	
				logger.info("before unload event session username = " + session.getUsername());
			}
		});
		
		add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(10)));
	}
	
	@Override
	protected void setResponse(PageParameters params) {
		setResponsePage(new ContactPage(params));
	}
}
