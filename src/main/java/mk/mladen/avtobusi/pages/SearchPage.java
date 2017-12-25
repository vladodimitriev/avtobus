package mk.mladen.avtobusi.pages;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import mk.mladen.avtobusi.WicketApplication;
import mk.mladen.avtobusi.beans.SearchBean;
import mk.mladen.avtobusi.service.PlaceService;
import mk.mladen.avtobusi.service.impl.OperationsUtil;

@SuppressWarnings("unchecked")
public class SearchPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private String ajax1;
	private String ajax2;
	private String ajax3;
	
	private SearchBean searchBean;
	
	@SpringBean
	private PlaceService placeService;
	
	public SearchPage(PageParameters params) {
		super(params);
		searchBean = new SearchBean(params);
		final PropertyModel<String> departureModel = new PropertyModel<String>(searchBean, "departurePlace");
		final PropertyModel<String> destinationModel = new PropertyModel<String>(searchBean, "destinationPlace");
		final PropertyModel<String> dateModel = new PropertyModel<String>(searchBean, "departureDate");

		final AutoCompleteSettings opts = new AutoCompleteSettings();
		opts.setShowListOnEmptyInput(true);

		final AutoCompleteTextField<String> actf1 = new AutoCompleteTextField<String>("departurePlace", departureModel, opts) {
			private static final long serialVersionUID = 1L;
			@Override
			protected Iterator<String> getChoices(String input) {
                List<String> choices = placeService.findAllPlacesNamesByLanguageAndName(lang, input);
				return choices.iterator();
			}
		};
		actf1.add(new OnChangeAjaxBehavior(){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target){
				ajax1 = ((AutoCompleteTextField<String>) getComponent()).getModelObject();
			}
		});
		actf1.setRequired(true);
		actf1.setOutputMarkupId(true);
		actf1.setOutputMarkupPlaceholderTag(true);

		final AutoCompleteTextField<String> actf2 = new AutoCompleteTextField<String>("destinationPlace", destinationModel, opts) {
			private static final long serialVersionUID = 1L;
			@Override
			protected Iterator<String> getChoices(String input) {
                List<String> choices = placeService.findAllPlacesNamesByLanguageAndName(lang, "" + input);
				return choices.iterator();
			}
		};
		actf2.add(new OnChangeAjaxBehavior(){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target){
				ajax2 = ((AutoCompleteTextField<String>) getComponent()).getModelObject();
			}
		});
		actf2.setRequired(true);
		actf2.setOutputMarkupId(true);
		actf2.setOutputMarkupPlaceholderTag(true);
		
		final TextField<String> tf3 = new TextField<String>("departureDate", dateModel);
		tf3.add(new OnChangeAjaxBehavior(){
	        private static final long serialVersionUID = 1L;
			@Override
	        protected void onUpdate(AjaxRequestTarget target){
	        	ajax3 = ((TextField<String>) getComponent()).getModelObject();
	        }
	    });
		tf3.setRequired(true);
		
		final Form<Void> form = new Form<Void>("searchForm") {
			private static final long serialVersionUID = 1L;
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
						
						departureDate = departureDate.replace("/", "-");
						PageParameters params = new PageParameters();
						params.add("lang", lang);
						if(StringUtils.isNotBlank(departurePlace)) {
							params.add("departure", departurePlace);
						}
						
						if(StringUtils.isNotBlank(destinationPlace)) {
							params.add("destination", destinationPlace);
						}
						
						if(StringUtils.isNotBlank(departureDate)) {
							params.add("date", departureDate);
						}
						
						ResultPage resultPage = new ResultPage(params);
						setResponsePage(resultPage);
					} 
				} 
			}
		};

		form.add(actf1);
		form.add(actf2);
		form.add(tf3);
		
		final Model<String> imgSwitchModel = new Model<>();
		final Image imgSwitch = new Image( "switch-img", imgSwitchModel);
		final ResourceReference rrSwitch = new PackageResourceReference(WicketApplication.class, "static/img/switch50x999.jpg");
		imgSwitch.setImageResourceReference(rrSwitch);
		form.add(imgSwitch);

		add(form);
	}

	@Override
	protected void setResponse(PageParameters parameters) {
		final PageParameters params = new PageParameters();
		String language = "mk";
		if(!parameters.get("lang").isEmpty()) {
			language = parameters.get("lang").toString();
		}
		if(language != null) {
			params.add("lang", language);
		}
		
		if(ajax1 != null) {
			String departurePlace = ajax1;
			if(departurePlace != null) {
				if(language.equals("mk")) {
					departurePlace = OperationsUtil.createMacedonianName(departurePlace);
				} else if(language.equals("en")) {
					departurePlace = OperationsUtil.createLatinName(departurePlace);
				}
				params.add("departure", departurePlace);
			}
		} else {
			String departurePlace = searchBean.getDeparturePlace();
			if(departurePlace != null) {
				if(language.equals("mk")) {
					departurePlace = OperationsUtil.createMacedonianName(departurePlace);
				} else if(language.equals("en")) {
					departurePlace = OperationsUtil.createLatinName(departurePlace);
				}
				params.add("departure", departurePlace);
			}
		}

		if(ajax2 != null) {
			String destinationPlace = ajax2;
			if(destinationPlace != null) {
				if(language.equals("mk")) {
					destinationPlace = OperationsUtil.createMacedonianName(destinationPlace);
				} else if(language.equals("en")) {
					destinationPlace = OperationsUtil.createLatinName(destinationPlace);
				}
				params.add("destination", destinationPlace);
			}
		} else if(searchBean.getDestinationPlace() != null) {
			String destinationPlace = searchBean.getDestinationPlace();
			if(destinationPlace != null) {
				if(language.equals("mk")) {
					destinationPlace = OperationsUtil.createMacedonianName(destinationPlace);
				} else if(language.equals("en")) {
					destinationPlace = OperationsUtil.createLatinName(destinationPlace);
				}
				params.add("destination", destinationPlace);
			}
		}

		if(ajax3 != null) {
			String departureDate = ajax3;
			if(StringUtils.isNotBlank(departureDate) && departureDate.contains("/")) {
				departureDate = departureDate.replace("/", "-");
			}
			params.add("date", departureDate);
		} else if(StringUtils.isNotBlank(searchBean.getDepartureDate())) {
			String departureDate = searchBean.getDepartureDate();
			if(StringUtils.isNotBlank(departureDate) && departureDate.contains("/")) {
				departureDate = departureDate.replace("/", "-");
			} else if(StringUtils.isNotBlank(departureDate) && departureDate.contains("-")) {
				departureDate = departureDate.replace("-", "/");
			}
			params.add("date", departureDate);
		}

		final SearchPage searchPage = new SearchPage(params);
		setResponsePage(searchPage);
	}

}
