package mk.mladen.avtobusi.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BaseAdminPage extends BasePage {
    private static final long serialVersionUID = 1L;

    public BaseAdminPage(PageParameters parameters) {
        super(parameters);
        add(new AdminMenuPanel("adminMenuPanel"));
    }

}
