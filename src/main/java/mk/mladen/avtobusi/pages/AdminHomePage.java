package mk.mladen.avtobusi.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

public class AdminHomePage extends BaseAdminPage {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(AdminHomePage.class);

    public AdminHomePage(PageParameters parameters) {

        super(parameters);
        logger.debug("Admin home page");
        
        add(new AjaxEventBehavior("beforeunload") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				logger.info("before unload event");	
				logger.info("session is invalidated = " + getSession().isSessionInvalidated());
			}
		});
		
		add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(10)));
    }

    @Override
    protected void setResponse(PageParameters params) {
        setResponsePage(new AdminHomePage(params));
    }
}
