package mk.mladen.avtobusi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import mk.mladen.avtobusi.security.AuthenticatedSession;

public class AdminMenuPanel extends Panel {

    private static final long serialVersionUID = 1L;
    
    private static Logger logger = LogManager.getLogger(AdminMenuPanel.class);

    public AdminMenuPanel(String id) {
        super(id);
        add(new Link<Void>("adminBusLinesPage") {
        	private static final long serialVersionUID = 1L;
			@Override
            public void onClick() {
                setResponsePage(AdminBusLinePage.class);
            }
        });
        add(new Link<Void>("adminPlacesPage") {
        	private static final long serialVersionUID = 1L;
			@Override
            public void onClick() {
                setResponsePage(AdminPlacePage.class);
            }
        });
        add(new Link<Void>("adminCarriersPage") {
        	private static final long serialVersionUID = 1L;
			@Override
            public void onClick() {
                setResponsePage(AdminCarrierPage.class);
            }
        });
        add(new Link<Void>("adminHomePage") {
            private static final long serialVersionUID = 5357812371802339220L;
            @Override
            public void onClick() {
                setResponsePage(AdminHomePage.class);
            }
        });
        add(new Link<Void>("signOut") {
            private static final long serialVersionUID = 5357812371802339220L;
            @Override
            public void onClick() {
                AuthenticatedSession.get().signOut();
                setResponsePage(LoginPage.class);
            }
        });
        
        AuthenticatedSession session = (AuthenticatedSession) AuthenticatedSession.get();
        String username = session.getUsername();
        logger.debug("username = {}", username);
        Model<String> model = new Model<String>(username);
        Label label = new Label("username", model);
        add(label);
    }
}
