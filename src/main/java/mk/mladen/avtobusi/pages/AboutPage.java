package mk.mladen.avtobusi.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AboutPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	public AboutPage(PageParameters parameters) {
		super(parameters);

	}
	
	@Override
	protected void setResponse(PageParameters params) {
		setResponsePage(new AboutPage(params));
	}
}
