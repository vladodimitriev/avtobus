package mk.mladen.avtobusi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.time.Duration;

import mk.mladen.avtobusi.security.AuthenticatedSession;
import mk.mladen.avtobusi.service.InsertDataService;

public class AdminMenuPanel extends Panel {

    private static final long serialVersionUID = 1L;
    
    private static Logger logger = LogManager.getLogger(AdminMenuPanel.class);
    
    @SpringBean
    private InsertDataService insertDataService;

    public AdminMenuPanel(String id, PageParameters parameters) {
        super(id);
        add(new StatelessLink<Void>("adminBusLinesPage") {
        	private static final long serialVersionUID = 1L;
			@Override
            public void onClick() {
                setResponsePage(new AdminBusLinePage(parameters));
            }
        });
        add(new StatelessLink<Void>("adminPlacesPage") {
        	private static final long serialVersionUID = 1L;
			@Override
            public void onClick() {
                setResponsePage(new AdminPlacePage(parameters));
            }
        });
        add(new StatelessLink<Void>("adminCarriersPage") {
        	private static final long serialVersionUID = 1L;
			@Override
            public void onClick() {
                setResponsePage(new AdminCarrierPage(parameters));
            }
        });
        add(new StatelessLink<Void>("adminHomePage") {
            private static final long serialVersionUID = 5357812371802339220L;
            @Override
            public void onClick() {
                setResponsePage(new AdminHomePage(parameters));
            }
        });
        add(new StatelessLink<Void>("insertDataIntoDb") {
            private static final long serialVersionUID = 5357812371802339220L;
            @Override
            public void onClick() {
                //setResponsePage(new AdminHomePage(parameters));
            	logger.info("inserting data into db");
            	insertDataService.insertDataIntoDb();
            	logger.info("inserted data into db");
            }
        });
        add(new Link<Void>("signOut") {
            private static final long serialVersionUID = 5357812371802339220L;
            @Override
            public void onClick() {
                AuthenticatedSession.get().signOut();
                setResponsePage(new SearchPage(parameters));
            }
        });
        
        AuthenticatedSession session = (AuthenticatedSession) AuthenticatedSession.get();
        String username = session.getUsername();
        Model<String> model = new Model<String>(username);
        Label label = new Label("username", model);
        add(label);
        
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
}
