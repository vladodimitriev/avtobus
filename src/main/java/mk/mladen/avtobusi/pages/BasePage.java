package mk.mladen.avtobusi.pages;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.settings.JavaScriptLibrarySettings;
import org.apache.wicket.spring.injection.annot.SpringBean;

import mk.mladen.avtobusi.WicketApplication;
import mk.mladen.avtobusi.service.InsertDataService;

@SuppressWarnings("serial")
public class BasePage extends WebPage {
	
	private final static Logger logger = Logger.getLogger(BasePage.class);
	
	@SpringBean
	private InsertDataService insertDataService;
	
	protected PageParameters parameters;
	
	protected String lang = "EN";
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		insertDataIntoDb();
	}
	
	@SuppressWarnings("rawtypes")
	public BasePage(PageParameters parameters) {
		super(parameters);
		this.parameters = parameters;
		if(parameters != null && parameters.get("lang") != null) {
			lang = parameters.get("lang").toString();
		}
		
		if(StringUtils.isBlank(lang)) {
			lang = "EN";
		}
		changeUserLocaleTo(lang);
		
		Link searchPageLink = new Link("searchPage") {
			@Override
			public MarkupContainer setDefaultModel(IModel model) {
				return null;
			}
			@Override
			public void onClick() {
				setResponsePage(SearchPage.class, parameters);
			}
		};
		add(searchPageLink);
		
		Link<String> aboutPage = new Link<String>("AboutPage") {
			
			@Override
			public MarkupContainer setDefaultModel(IModel model) {
				return null;
			}

			@Override
			public void onClick() {
				setResponsePage(AboutPage.class, parameters);
			}
		};
		add(aboutPage);
		
		Link<String> contactPage = new Link<String>("ContactPage") {
			
			@Override
			public MarkupContainer setDefaultModel(IModel model) {
				return null;
			}

			@Override
			public void onClick() {
				setResponsePage(ContactPage.class, parameters);
			}
		};
		add(contactPage);
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
	
	private void insertDataIntoDb() {
		if(logger.isInfoEnabled()){
			logger.info("insertDataIntoDb()");
		}
		insertDataService.insertDataIntoHsqldbDb();
	}
}


