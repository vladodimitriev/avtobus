package mk.mladen.avtobusi.pages;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.settings.JavaScriptLibrarySettings;

import mk.mladen.avtobusi.WicketApplication;
import mk.mladen.avtobusi.security.AuthenticatedSession;

public abstract class BasePage extends WebPage {
	
	private static Logger logger = LogManager.getLogger(BasePage.class);

	private static final long serialVersionUID = 1L;

	protected String lang = "mk";
	protected String ttmh;
	protected String ttmm;
	
	public BasePage(PageParameters parameters) {
		super(parameters);
		if(!parameters.get("lang").isEmpty()) {
			lang = parameters.get("lang").toString();
		}
		
		if(StringUtils.isBlank(lang)) {
			lang = "mk";
		}
		changeUserLocaleTo(lang);
		
		final Link<Void> searchPageLink = new Link<Void>("searchPage") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				SearchPage searchPage = new SearchPage(parameters);
				setResponsePage(searchPage);
			}
		};
		add(searchPageLink);
		
		final AuthenticatedSession session = (AuthenticatedSession) AuthenticatedSession.get();
        String username = session.getUsername();
        final Model<String> model = new Model<String>(username);
        final Label label = new Label("usernameLbl", model);
        add(label);
		
		final StatelessLink<Void> aboutPage = new StatelessLink<Void>("AboutPage") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				PageParameters newParams = new PageParameters();
				newParams.add("lang", lang);
				setResponsePage(new AboutPage(newParams));
			}
		};
		add(aboutPage);
		
		final StatelessLink<Void> contactPage = new StatelessLink<Void>("ContactPage") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				PageParameters newParams = new PageParameters();
				newParams.add("lang", lang);
				setResponsePage(new ContactPage(newParams));
			}
		};
		add(contactPage);

		final StatelessLink<Void> loginPage = new StatelessLink<Void>("LoginPage") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				PageParameters newParams = new PageParameters();
				newParams.add("lang", lang);
				setResponsePage(new LoginPage(newParams));
			}
		};
		add(loginPage);

		Model<String> langLabelModel = new Model<String>(lang);
		
		Label languageLabel = new Label("language_label", langLabelModel);
		add(languageLabel);

		Model<String> imgModel = new Model<String>();
		Image img = new Image( "language_img", imgModel);

		ResourceReference resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/gb.svg");
		if("en".equalsIgnoreCase(lang)) {
			resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/gb.svg");
		} else if("mk".equalsIgnoreCase(lang)) {
			resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/mk.svg");
		}
		img.setImageResourceReference(resourceReference);
		add(img);

		Link<Void> englishLink = new Link<Void>("english") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponse(getParams("en"));
			}
		};
		
		Model<String> imgEnglishModel = new Model<String>();
		Image imgEnglish = new Image( "language_en_img", imgEnglishModel);
		ResourceReference rr1 = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/gb.svg");
		imgEnglish.setImageResourceReference(rr1);
		englishLink.add(imgEnglish);
		add(englishLink);

		Link<Void> mkLink = new Link<Void>("macedonian") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponse(getParams("mk"));
			}
		};
		
		Model<String> imgMkModel = new Model<String>();
		Image imgMk = new Image( "language_mk_img", imgMkModel);
		ResourceReference rrMk = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/mk.svg");
		imgMk.setImageResourceReference(rrMk);
		mkLink.add(imgMk);
		add(mkLink);
		
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
        
        String departureDateEmptyValidationMsg = getString("avtobusi.departureDate.validationMsg.empty");
        String departurePlaceValidationMsg = getString("avtobusi.departurePlace.validationMsg");
        String destinationPlaceValidationMsg = getString("avtobusi.destinationPlace.validationMsg");
        String departureDateValidationMsg = getString("avtobusi.departureDate.validationMsg");
        
        if("en".equalsIgnoreCase(lang)) {
        	initScript = ";initJQDatepicker('departureDate', 'en-GB', '"+ departureDateValidationMsg + "', '"+ departureDateEmptyValidationMsg + "', '"+ departurePlaceValidationMsg + "', '"+ destinationPlaceValidationMsg + "');";
		} else if("mk".equalsIgnoreCase(lang)) {
			initScript = ";initJQDatepicker('departureDate', 'mk', '"+ departureDateValidationMsg + "', '"+ departureDateEmptyValidationMsg + "', '"+ departurePlaceValidationMsg + "', '"+ destinationPlaceValidationMsg + "');";
		}
        
        response.render(OnLoadHeaderItem.forScript(initScript));
    }
	
	private void changeUserLocaleTo(String localeString) {
        getSession().setLocale(new Locale(localeString));
    }
	
	@Override
	public void onEvent(IEvent<?> event) {
//		}
	}
	
}


