package mk.mladen.avtobusi.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
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
import mk.mladen.avtobusi.dto.BusLineDto;
import mk.mladen.avtobusi.service.BusLineService;
import mk.mladen.avtobusi.service.PlaceService;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class ResultPage extends BasePage {

	private String ajax1;
	private String ajax2;
	private String ajax3;
	
	private SearchBean searchBean = new SearchBean();
	
	@SpringBean
	private BusLineService busLineService;
	
	@SpringBean
	private PlaceService placeService;

	private ResourceReference busResourceReference;

	private PropertyListView<BusLineDto> dataView;

	public ResultPage(PageParameters params) {

		super(params);

		PageParameters params2 = new PageParameters();
		params2.add("departure", "Skopje");
		params2.add("destination", "Negotino");
		params2.add("date", "03/07/2017");
		params2.add("lang", "EN");
		searchBean = new SearchBean(params);

		busResourceReference = new PackageResourceReference(WicketApplication.class, "static/img/bus21x21x999.jpg");

		Model langLabelModel = new Model(lang);
		Label languageLabel = new Label("language_label", langLabelModel);
		add(languageLabel);
		
		Model imgModel = new Model();
		Image img = new Image( "language_img", imgModel);

		
		ResourceReference resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/gb.svg");
		if("EN".equalsIgnoreCase(lang)) {
			resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/gb.svg");
		} else if("MK".equalsIgnoreCase(lang)) {
			resourceReference = new PackageResourceReference(WicketApplication.class, "static/flags/4x3/mk.svg");
		}
		img.setImageResourceReference(resourceReference);
		add(img);

		Model imgSwitchModel = new Model();
		Image imgSwitch = new Image( "switch-img", imgSwitchModel);
		ResourceReference rr1 = new PackageResourceReference(WicketApplication.class, "static/img/switch50x999.jpg");
		imgSwitch.setImageResourceReference(rr1);

		Link link1 = new Link("english") {
			@Override
			public MarkupContainer setDefaultModel(IModel model) {
				return null;
			}
			@Override
			public void onClick() {
				setResponsePage(ResultPage.class, getParams("EN"));
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
				setResponsePage(ResultPage.class, getParams("MK"));
			}
		};
		add(link2);
		
		AutoCompleteTextField<String> actf1 = new AutoCompleteTextField<String>("departurePlace", new PropertyModel(searchBean, "departurePlace")) {
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
		
		AutoCompleteTextField<String> actf2 = new AutoCompleteTextField<String>("destinationPlace", new PropertyModel(searchBean, "destinationPlace")) {
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
		
		TextField tf3 = new TextField<String>("departureDate", new PropertyModel(searchBean, "departureDate"));
		tf3.add(new OnChangeAjaxBehavior(){
	        @Override
	        protected void onUpdate(final AjaxRequestTarget target){
	        	ajax3 = ((TextField<String>) getComponent()).getModelObject();
	        }
	    });

		dataView = createDataView();
		dataView.setOutputMarkupId(true);

		Form form = new Form("resultSearchForm"){
			@Override
			protected void onSubmit() {
				dataView = createDataView();
				this.addOrReplace(dataView);
			}
		};
		form.add(actf1);
		form.add(actf2);
		form.add(tf3);
		form.add(imgSwitch);
		form.addOrReplace(dataView);
		add(form);
	}
	
	private PropertyListView<BusLineDto> createDataView() {
		List<BusLineDto> busLines = loadRelations();
		dataView = new PropertyListView<BusLineDto>("rows", busLines) {
			  @Override
			  protected void populateItem(ListItem<BusLineDto> item) {
				  final BusLineDto busLine = item.getModelObject();
				  Model imgModel = new Model();
				  Image bus_img = new Image( "bus_img", imgModel);
				  bus_img.setImageResourceReference(busResourceReference);

				  item.add(bus_img);
				  if("EN".equalsIgnoreCase(lang)) {
					  final Label label = new Label("carrier", busLine.getCarrier());
					  label.add(new AttributeModifier("style", "text-align: left"));
					  item.add(label);
				  } else if("MK".equalsIgnoreCase(lang)) {
					  final Label label = new Label("carrier", busLine.getCarrierCyrilic());
					  label.add(new AttributeModifier("style", "text-align: left"));
					  item.add(label);
				  }

				  String departureTime = busLine.getDepartureTime();
				  String arrivalTime = busLine.getArrivalTime();
				  String allTime = departureTime + " - " + arrivalTime;
				  Label label1 = new Label("departureArrivalTime", allTime);
				  label1.add(new AttributeModifier("style", "text-align: left"));
				  item.add(label1);

				  String allPlace = searchBean.getDeparturePlace() + " - " + searchBean.getDestinationPlace();
				  label1 = new Label("departureArrivalPlace", allPlace);
				  label1.add(new AttributeModifier("style", "text-align: left"));
				  item.add(label1);

//				  Label label3 = new Label("travelTime", busLine.getTravelTime());
//				  label3.add(new AttributeModifier("style", "text-align: left"));
//				  item.add(label3);

//				  Label label4 = new Label("details", "details");
//				  label4.add(new AttributeModifier("style", "text-align: left"));
//				  item.add(label4);

//				  Label label5 = new Label("travelDistance", busLine.getDistance());
//				  label5.add(new AttributeModifier("style", "text-align: left"));
//				  item.add(label5);

				  //item.addOrReplace(repeatingView);
			  }
		};
		return dataView;
	}

	private List<BusLineDto> loadRelations() {
		List<BusLineDto> dtos = new ArrayList<BusLineDto>();
		if(busLineService != null) {
			String departurePlace = searchBean.getDeparturePlace();
			String destinationPlace = searchBean.getDestinationPlace();
			String date = searchBean.getDepartureDate();
			dtos = busLineService.getRelation(departurePlace, destinationPlace, date);
		} 
		return dtos;
	}
	
	public class PurchasePanel extends Panel {
		
		private static final long serialVersionUID = 1L;
		
		public PurchasePanel(String id) {
			super(id);
			add(new Button(id, new Model("Purchase")));
		}
		
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
