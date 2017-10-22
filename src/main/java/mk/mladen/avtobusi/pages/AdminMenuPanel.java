package mk.mladen.avtobusi.pages;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class AdminMenuPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public AdminMenuPanel(String id) {
        super(id);
        add(new BookmarkablePageLink<String>("adminBusLinesPage", AdminBusLinePage.class));
        add(new BookmarkablePageLink<String>("adminPlacesPage", AdminPlacePage.class));
        add(new BookmarkablePageLink<String>("adminCarriersPage", AdminCarrierPage.class));
        add(new Link<String>("adminHomePage") {
            private static final long serialVersionUID = 5357812371802339220L;

            @Override
            public void onClick() {//iipaxIpvDataervice.getIpvDataTypes();
                setResponsePage(AdminHomePage.class);
            }
        });
    }
}
