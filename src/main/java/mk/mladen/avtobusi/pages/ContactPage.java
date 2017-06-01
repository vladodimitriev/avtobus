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
public class ContactPage extends BasePage {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ContactPage(PageParameters parameters) {
		super(parameters);
		Model langLabelModel = new Model(lang);
		Label languageLabel = new Label("language_label", langLabelModel);
		add(languageLabel);
		
		Model imgModel = new Model();
		Image img = new Image( "language_img", imgModel);
		
		ResourceReference resourceReference = new ContextRelativeResourceReference("static/flags/4x3/gb.svg");
		if("EN".equalsIgnoreCase(lang)) {
			resourceReference = new ContextRelativeResourceReference("static/flags/4x3/gb.svg");
		} else if("MK".equalsIgnoreCase(lang)) {
			resourceReference = new ContextRelativeResourceReference("static/flags/4x3/mk.svg");
		}
		img.setImageResourceReference(resourceReference);
		add(img);
		
		Link link1 = new Link("english") {
			
			@Override
			public MarkupContainer setDefaultModel(IModel model) {
				return null;
			}

			@Override
			public void onClick() {
				setResponsePage(ContactPage.class, getParams("EN"));
			}
		};
		add(link1);
		
		Link link2 = new Link("macedonian") {

			@Override
			public MarkupContainer setDefaultModel(IModel model) {
				return null;
			}

			@Override
			public void onClick() {
				setResponsePage(ContactPage.class, getParams("MK"));
			}
		};
		add(link2);
	}
	
	private PageParameters getParams(String language) {
		PageParameters params = new PageParameters();
		if(language != null) {
			params.add("lang", language);
		}
		
		return params;
	}
}
