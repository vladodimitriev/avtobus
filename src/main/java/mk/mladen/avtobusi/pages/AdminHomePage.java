package mk.mladen.avtobusi.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AdminHomePage extends BaseAdminPage {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(AdminHomePage.class);

    public AdminHomePage(PageParameters parameters) {

        super(parameters);
        logger.debug("Admin home page");
    }

    @Override
    protected void setResponse(PageParameters params) {
        setResponsePage(AdminHomePage.class, params);
    }
}
