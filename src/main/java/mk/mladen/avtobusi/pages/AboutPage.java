package mk.mladen.avtobusi.pages;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

@SuppressWarnings({ "serial" })
public class AboutPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AboutPage(PageParameters parameters) {
		super(parameters);

	}
	
	@Override
	protected void setResponse(PageParameters params) {
		setResponsePage(AboutPage.class, params);
	}
}
