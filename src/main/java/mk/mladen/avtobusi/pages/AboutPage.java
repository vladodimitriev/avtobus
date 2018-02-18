package mk.mladen.avtobusi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

public class AboutPage extends BasePage {
	
	private static Logger logger = LogManager.getLogger(AboutPage.class);

	private static final long serialVersionUID = 1L;
	
	protected static final String EVENT_ON_UNLOAD = "unload"; 
	
	public AboutPage(PageParameters parameters) {
		super(parameters);

		add(new AjaxEventBehavior("beforeunload") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				logger.info("before unload event");	
				logger.info("before unload event session id = " + getSession().getId());
			}
		});
		
		add(new AjaxEventBehavior("unload") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				logger.info("unload event");
				logger.info("unload event session id = " + getSession().getId());
			}
		});
		
		add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(10)));
	}
	
	@Override
	protected void setResponse(PageParameters params) {
		setResponsePage(new AboutPage(params));
	}
}
