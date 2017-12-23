package mk.mladen.avtobusi.pages;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.settings.JavaScriptLibrarySettings;
import org.apache.wicket.spring.injection.annot.SpringBean;

import mk.mladen.avtobusi.WicketApplication;
import mk.mladen.avtobusi.service.InsertDataService;

public abstract class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;

	@SpringBean
	private InsertDataService insertDataService;

	protected PageParameters parameters;
	
	protected String lang = "MK";
	protected String ttmh;
	protected String ttmm;
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
	}
	
	public BasePage(PageParameters parameters) {
		super(parameters);
		this.parameters = parameters;
		if(parameters != null && parameters.get("lang") != null) {
			lang = parameters.get("lang").toString();
		}
		
		if(StringUtils.isBlank(lang)) {
			lang = "MK";
		}
		changeUserLocaleTo(lang);
		
		Link<Void> searchPageLink = new Link<Void>("searchPage") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new SearchPage(parameters));
			}
		};
		add(searchPageLink);
		
		Link<Void> aboutPage = new Link<Void>("AboutPage") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new AboutPage(parameters));
			}
		};
		add(aboutPage);
		
		Link<Void> contactPage = new Link<Void>("ContactPage") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new ContactPage(parameters));
			}
		};
		add(contactPage);

		Link<Void> adminPage = new Link<Void>("LoginPage") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new LoginPage(parameters));
			}
		};
		add(adminPage);

		Model<String> langLabelModel = new Model<String>(lang);
		
		Label languageLabel = new Label("language_label", langLabelModel);
		add(languageLabel);

		Model<String> imgModel = new Model<String>();
		Image img = new Image( "language_img", imgModel);

		ResourceReference resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/gb.svg");
		if("EN".equalsIgnoreCase(lang)) {
			resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/gb.svg");
		} else if("MK".equalsIgnoreCase(lang)) {
			resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/mk.svg");
		}
		img.setImageResourceReference(resourceReference);
		add(img);

		add(new Link<Void>("english") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponse(getParams("EN"));
			}
		});

		add(new Link<Void>("macedonian") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponse(getParams("MK"));
			}
		});
		
		ResourceReference resourceReferenceFavicon = new PackageResourceReference(WicketApplication.class, "static/img/bus16x16.png");
		ResourceLink<ResourceReference> favicon = new ResourceLink<ResourceReference>("favicon", resourceReferenceFavicon);
		add(favicon);
		ttmh = new StringResourceModel("avtobusi.resultpage.traveltime.hour").getString();
		ttmm = new StringResourceModel("avtobusi.resultpage.traveltime.min").getString();
	}

	protected abstract void setResponse(PageParameters params);

	private PageParameters getParams(String language) {
		PageParameters params = new PageParameters();
		if(language != null) {
			params.add("lang", language);
		}

		return params;
	}

	@SuppressWarnings("unused")
	@Override
    public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
        
        // -- JavaScript
        JavaScriptLibrarySettings javaScriptSettings = getApplication().getJavaScriptLibrarySettings();
		response.render(JavaScriptHeaderItem.forReference(javaScriptSettings.getJQueryReference()));
		
		ResourceReference jqueryJsRef = new PackageResourceReference(WicketApplication.class, "static/js/jquery.min.js");
        JavaScriptHeaderItem jqueryJsItem = JavaScriptHeaderItem.forReference(jqueryJsRef);
		
		ResourceReference jqueryUiJsRef = new PackageResourceReference(WicketApplication.class, "static/js/jquery-ui.min.js");
        JavaScriptHeaderItem jqueryUiJsItem = JavaScriptHeaderItem.forReference(jqueryUiJsRef);
        
        ResourceReference jqueryUii18nJsRef = new PackageResourceReference(WicketApplication.class, "static/js/jquery-ui-i18n.min.js");
        JavaScriptHeaderItem jqueryUii18nJsItem = JavaScriptHeaderItem.forReference(jqueryUii18nJsRef);
        
        ResourceReference bootstrapJsRef = new PackageResourceReference(WicketApplication.class, "static/js/bootstrap.min.js");
        JavaScriptHeaderItem bootstrapJsItem = JavaScriptHeaderItem.forReference(bootstrapJsRef);
        
        ResourceReference datepickerMkJsRef = new PackageResourceReference(WicketApplication.class, "static/js/datepicker-mk.js");
        JavaScriptHeaderItem datepickerMkJsItem = JavaScriptHeaderItem.forReference(datepickerMkJsRef);
        
        ResourceReference datepickerEnJsRef = new PackageResourceReference(WicketApplication.class, "static/js/datepicker-en-GB.js");
        JavaScriptHeaderItem datepickerEnJsItem = JavaScriptHeaderItem.forReference(datepickerEnJsRef);
        
        ResourceReference avtobusDpJsRef = new PackageResourceReference(WicketApplication.class, "static/js/avtobus.js");
        JavaScriptHeaderItem avtobusDpJsItem = JavaScriptHeaderItem.forReference(avtobusDpJsRef);
        
        ResourceReference jqueryDateFormatDpJsRef = new PackageResourceReference(WicketApplication.class, "static/js/jquery.dateFormat.js");
        JavaScriptHeaderItem jqueryDateFormatDpJsItem = JavaScriptHeaderItem.forReference(jqueryDateFormatDpJsRef);
        
        ResourceReference jqueryValidateJsRef = new PackageResourceReference(WicketApplication.class, "static/js/jquery.validate.min.js");
        JavaScriptHeaderItem jqueryValidateJsItem = JavaScriptHeaderItem.forReference(jqueryValidateJsRef);
        
        // -- CSS
        ResourceReference bootstrapCssRef = new PackageResourceReference(WicketApplication.class, "static/css/bootstrap.min.css");
        CssHeaderItem bootstrapCssItem = CssHeaderItem.forReference(bootstrapCssRef);
        
        ResourceReference fontAwesomeCssRef = new PackageResourceReference(WicketApplication.class, "static/css/font-awesome.min.css");
        CssHeaderItem fontAwesomeCssItem = CssHeaderItem.forReference(fontAwesomeCssRef);
        
        ResourceReference jQueryUiCssRef = new PackageResourceReference(WicketApplication.class, "static/css/jquery-ui.min.css");
        CssHeaderItem jQueryUiCssItem = CssHeaderItem.forReference(jQueryUiCssRef);
        
        ResourceReference flagIconCssRef = new PackageResourceReference(WicketApplication.class, "static/css/flag-icon.min.css");
        CssHeaderItem flagIconCssItem = CssHeaderItem.forReference(flagIconCssRef);
        
        ResourceReference avtobusCssRef = new PackageResourceReference(WicketApplication.class, "static/css/avtobus.css");
        CssHeaderItem avtobusCssItem = CssHeaderItem.forReference(avtobusCssRef);
        
        response.render(bootstrapCssItem);
        response.render(fontAwesomeCssItem);
        response.render(jQueryUiCssItem);
        response.render(flagIconCssItem);
        response.render(avtobusCssItem);
        
        response.render(jqueryUiJsItem);
        response.render(jqueryUii18nJsItem);
        response.render(bootstrapJsItem);
        response.render(datepickerMkJsItem);
        response.render(datepickerEnJsItem);
        response.render(avtobusDpJsItem);
        response.render(jqueryDateFormatDpJsItem);
        response.render(jqueryValidateJsItem);
        
        String initScript = "";
        
        String departureDateValidationMsg = getString("avtobusi.departureDate.validationMsg");
        String departureDateEmptyValidationMsg = getString("avtobusi.departureDate.validationMsg.empty");
        String departurePlaceValidationMsg = getString("avtobusi.departurePlace.validationMsg");
        String destinationPlaceValidationMsg = getString("avtobusi.destinationPlace.validationMsg");
        
        if("EN".equalsIgnoreCase(lang)) {
        	initScript = ";initJQDatepicker('departureDate', 'en-GB', '"+ departureDateValidationMsg + "', '"+ departureDateEmptyValidationMsg + "', '"+ departurePlaceValidationMsg + "', '"+ destinationPlaceValidationMsg + "');";
		} else if("MK".equalsIgnoreCase(lang)) {
			initScript = ";initJQDatepicker('departureDate', 'mk', '"+ departureDateValidationMsg + "', '"+ departureDateEmptyValidationMsg + "', '"+ departurePlaceValidationMsg + "', '"+ destinationPlaceValidationMsg + "');";
		}
        
        response.render(OnLoadHeaderItem.forScript(initScript));
    }
	
	private void changeUserLocaleTo(String localeString) {
        getSession().setLocale(new Locale(localeString));
    }
	
}


