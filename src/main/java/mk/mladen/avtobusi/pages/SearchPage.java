package mk.mladen.avtobusi.pages;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;

import mk.mladen.avtobusi.WicketApplication;
import mk.mladen.avtobusi.beans.SearchBean;
import mk.mladen.avtobusi.service.PlaceService;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class SearchPage extends BasePage {
	
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(SearchPage.class);

	private String ajax1;
	private String ajax2;
	private String ajax3;
	
	private SearchBean searchBean;
	
	@SpringBean
	private PlaceService placeService;
	
	public SearchPage(PageParameters params) {
		super(params);
		searchBean = new SearchBean(params);
		PropertyModel departureModel = new PropertyModel(searchBean, "departurePlace");
		PropertyModel destinationModel = new PropertyModel(searchBean, "destinationPlace");
		PropertyModel dateModel = new PropertyModel(searchBean, "departureDate");
		
		AutoCompleteTextField<String> actf1 = new AutoCompleteTextField<String>("departurePlace", departureModel) {
			@Override
			protected Iterator<String> getChoices(String input) {
				if (Strings.isEmpty(input) && input != null && input.length() > 3) {
                    List<String> emptyList = Collections.emptyList();
                    return emptyList.iterator();
                }
                List<String> choices = placeService.findAllPlacesNamesByLanguageAndName(lang, input);
				return choices.iterator();
			}
		};
		actf1.add(new OnChangeAjaxBehavior(){
	        @Override
	        protected void onUpdate(final AjaxRequestTarget target){
	        	ajax1 = ((AutoCompleteTextField<String>) getComponent()).getModelObject();
	        }
	    });
		actf1.setRequired(true);
		actf1.setOutputMarkupId(true);
		
		AutoCompleteTextField<String> actf2 = new AutoCompleteTextField<String>("destinationPlace", destinationModel) {
			@Override
			protected Iterator<String> getChoices(String input) {
				if (Strings.isEmpty(input) && input != null && input.length() > 3) {
                    List<String> emptyList = Collections.emptyList();
                    return emptyList.iterator();
                }
                List<String> choices = placeService.findAllPlacesNamesByLanguageAndName(lang, input);
				return choices.iterator();
			}
		};
		actf2.add(new OnChangeAjaxBehavior(){
	        @Override
	        protected void onUpdate(final AjaxRequestTarget target){
	        	ajax2 = ((AutoCompleteTextField<String>) getComponent()).getModelObject();
	        }
	    });
		actf2.setRequired(true);
		actf2.setOutputMarkupId(true);
		
		TextField tf3 = new TextField<String>("departureDate", dateModel);
		tf3.add(new OnChangeAjaxBehavior(){
	        @Override
	        protected void onUpdate(final AjaxRequestTarget target){
	        	ajax3 = ((TextField<String>) getComponent()).getModelObject();
	        }
	    });
		tf3.setRequired(true);
		
		Form form = new Form("searchForm") {
			@Override
			protected void onSubmit() {
				actf1.validate();
				actf2.validate();
				tf3.validate();
				if(actf1.isValid() && actf2.isValid() && tf3.isValid()) {
				    String departurePlace = searchBean.getDeparturePlace();
				    String destinationPlace = searchBean.getDestinationPlace();
				    String departureDate = searchBean.getDepartureDate();
					if(StringUtils.isNotBlank(departurePlace) &&
							StringUtils.isNotBlank(destinationPlace) &&
							StringUtils.isNotBlank(departureDate)) {
						
						PageParameters params = new PageParameters();
						params.add("departure", departurePlace);
						params.add("destination", destinationPlace);
						params.add("date", departureDate);
						params.add("lang", lang);
						setResponsePage(ResultPage.class, params);
					} 
				} 
			}
		};
		
		form.add(actf1);
		form.add(actf2);
		form.add(tf3);
		
		Model langLabelModel = new Model(lang);
		Label languageLabel = new Label("language_label", langLabelModel);
		add(languageLabel);
		
		Model imgModel = new Model();
		Image img = new Image( "language_img", imgModel);
		
		Model img2Model = new Model();
		Image img2 = new Image( "switch-img", img2Model);
		ResourceReference rr1 = new PackageResourceReference(WicketApplication.class, "static/img/switch50.jpg");
		img2.setImageResourceReference(rr1);
		form.add(img2);
		
		ResourceReference resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/gb.svg");
		if("EN".equalsIgnoreCase(lang)) {
			resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/gb.svg");
		} else if("MK".equalsIgnoreCase(lang)) {
			resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/mk.svg");
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
				setResponsePage(SearchPage.class, getParams("EN"));
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
				setResponsePage(SearchPage.class, getParams("MK"));
			}
		};
		add(link2);
		add(form);
	}
	
	private PageParameters getParams(String language) {
		PageParameters params = new PageParameters();
		if(ajax1 != null) {
			params.add("departure", ajax1);
		} else if(searchBean.getDeparturePlace() != null) {
			params.add("departure", searchBean.getDeparturePlace());
		}
		
		if(ajax2 != null) {
			params.add("destination", ajax2);
		} else if(searchBean.getDestinationPlace() != null) {
			params.add("destination", searchBean.getDestinationPlace());
		}
		
		if(ajax3 != null) {
			params.add("date", ajax3);
		} else if(searchBean.getDepartureDate() != null) {
			params.add("date", searchBean.getDepartureDate());
		}
		
		if(language != null) {
			params.add("lang", language);
		}
		
		return params;
	}
	
}
