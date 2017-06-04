package mk.mladen.avtobusi.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import mk.mladen.avtobusi.beans.SearchBean;
import mk.mladen.avtobusi.dto.BusLineDto;
import mk.mladen.avtobusi.service.BusLineService;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class ResultPage extends BasePage {

	private String ajax1;
	private String ajax2;
	private String ajax3;
	
	private SearchBean searchBean = new SearchBean();
	
	@SpringBean
	private BusLineService busLineService;
	
	private List<BusLineDto> busLines;
	
	public ResultPage(PageParameters params) {
		super(params);
		
		searchBean = new SearchBean(params);
		
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
		
		TextField tf1 = new TextField<String>("departurePlace", new PropertyModel(searchBean, "departurePlace"));
		tf1.add(new OnChangeAjaxBehavior(){
	        @Override
	        protected void onUpdate(final AjaxRequestTarget target){
	        	ajax1 = ((TextField<String>) getComponent()).getModelObject();
	        }
	    });
		
		TextField tf2 = new TextField<String>("destinationPlace", new PropertyModel(searchBean, "destinationPlace"));
		tf2.add(new OnChangeAjaxBehavior(){
	        @Override
	        protected void onUpdate(final AjaxRequestTarget target){
	        	ajax2 = ((TextField<String>) getComponent()).getModelObject();
	        }
	    });
		
		TextField tf3 = new TextField<String>("departureDate", new PropertyModel(searchBean, "departureDate"));
		tf3.add(new OnChangeAjaxBehavior(){
	        @Override
	        protected void onUpdate(final AjaxRequestTarget target){
	        	ajax3 = ((TextField<String>) getComponent()).getModelObject();
	        }
	    });
		
		Form form = new Form("resultSearchForm"){
			@Override
			protected void onSubmit() {
				busLines = loadRelations();
				ListDataProvider<BusLineDto> listDataProvider = new ListDataProvider<BusLineDto>(busLines);
				DataView<BusLineDto> dataView = new DataView<BusLineDto>("rows", listDataProvider) {
					  @Override
					  protected void populateItem(Item<BusLineDto> item) {
						  BusLineDto busLine = item.getModelObject();
						  RepeatingView repeatingView = new RepeatingView("dataRow");
						  repeatingView.add(new Label(repeatingView.newChildId(), busLine.getDepartureTime()));
						  repeatingView.add(new Label(repeatingView.newChildId(), busLine.getArrivalTime()));
						  if("EN".equalsIgnoreCase(lang)) {
							  repeatingView.add(new Label(repeatingView.newChildId(), busLine.getCarrier()));   
						  } else if("MK".equalsIgnoreCase(lang)) {
							  repeatingView.add(new Label(repeatingView.newChildId(), busLine.getCarrierCyrilic()));   
						  }
						   
						  repeatingView.add(new Label(repeatingView.newChildId(), busLine.getPrice()));
						  item.add(repeatingView); 
					  }
				};
				Form form2 = new Form("purchaseForm"){
					@Override
					protected void onSubmit() {}
				};
				form2.add(dataView);
				ResultPage.this.replace(form2);
				setResponsePage(getPage());
			}
		};
		
		//ResourceReference iconReference = new ContextRelativeResourceReference("static/img/info.png");
		//form.add(new Image("switchIcon", iconReference));
		
		form.add(tf1);
		form.add(tf2);
		form.add(tf3);
		add(form);
		
		busLines = loadRelations();
		ListDataProvider<BusLineDto> listDataProvider = new ListDataProvider<BusLineDto>(busLines);
		
		DataView<BusLineDto> dataView = new DataView<BusLineDto>("rows", listDataProvider) {
			  @Override
			  protected void populateItem(Item<BusLineDto> item) {
				  BusLineDto busLine = item.getModelObject();
				  RepeatingView repeatingView = new RepeatingView("dataRow");
				  repeatingView.add(new Label(repeatingView.newChildId(), busLine.getDepartureTime()));
				  repeatingView.add(new Label(repeatingView.newChildId(), busLine.getArrivalTime()));
				  repeatingView.add(new Label(repeatingView.newChildId(), busLine.getCarrier()));    
				  repeatingView.add(new Label(repeatingView.newChildId(), busLine.getPrice()));
				  item.add(repeatingView);
			  }
		};
		Form form2 = new Form("purchaseForm"){
			@Override
			protected void onSubmit() {}
		};
		form2.add(dataView);
		add(form2);
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