package mk.mladen.avtobusi.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.time.Duration;

import mk.mladen.avtobusi.WicketApplication;
import mk.mladen.avtobusi.beans.SearchBean;
import mk.mladen.avtobusi.dto.BusLineDto;
import mk.mladen.avtobusi.service.BusLineService;
import mk.mladen.avtobusi.service.PlaceService;
import mk.mladen.avtobusi.service.impl.OperationsUtil;

@SuppressWarnings("unchecked")
public class ResultPage extends BasePage {

	private static Logger logger = LogManager.getLogger(ResultPage.class);
	
	private static final long serialVersionUID = 1L;

	private SearchBean searchBean = new SearchBean();
	
	@SpringBean
	private BusLineService busLineService;
	
	@SpringBean
	private PlaceService placeService;

	private ResourceReference busResourceReference;

	private PropertyListView<BusLineDto> dataView;
	
	private String ajax1;
	
	private String ajax2;
	
	private String ajax3;

	public ResultPage(PageParameters params) {
		super(params);
		
		searchBean = new SearchBean(params);
		busResourceReference = new PackageResourceReference(WicketApplication.class, "static/img/bus21x21x999.jpg");

		final Model<String> imgSwitchModel = new Model<>();
		final Image imgSwitch = new Image( "switch-img", imgSwitchModel);
		final ResourceReference rrSwitch = new PackageResourceReference(WicketApplication.class, "static/img/switch50x999.jpg");
		imgSwitch.setImageResourceReference(rrSwitch);

		final AutoCompleteSettings opts = new AutoCompleteSettings();
		opts.setShowListOnEmptyInput(true);
		
		final AutoCompleteTextField<String> actf1 = new AutoCompleteTextField<String>("departurePlace", new PropertyModel<String>(searchBean, "departurePlace"), opts) {
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
	        protected void onUpdate(final AjaxRequestTarget target){
	        	ajax1 = ((AutoCompleteTextField<String>) getComponent()).getModelObject();
	        }
	    });
		actf1.setRequired(true);
		actf1.setOutputMarkupId(true);
		
		final AutoCompleteTextField<String> actf2 = new AutoCompleteTextField<String>("destinationPlace", new PropertyModel<String>(searchBean, "destinationPlace"), opts) {
			private static final long serialVersionUID = 1L;
			@Override
			protected Iterator<String> getChoices(String input) {
				List<String> choices = placeService.findAllPlacesNamesByLanguageAndName(lang, input);
				return choices.iterator();
			}
		};
		actf2.add(new OnChangeAjaxBehavior(){
	        private static final long serialVersionUID = 1L;
			@Override
	        protected void onUpdate(final AjaxRequestTarget target){
	        	ajax2 = ((AutoCompleteTextField<String>) getComponent()).getModelObject();
	        }
	    });
		actf2.setRequired(true);
		actf2.setOutputMarkupId(true);
		
		final TextField<String> tf3 = new TextField<String>("departureDate", new PropertyModel<String>(searchBean, "departureDate"));
		tf3.add(new OnChangeAjaxBehavior(){
	        private static final long serialVersionUID = 1L;
			@Override
	        protected void onUpdate(final AjaxRequestTarget target){
	        	ajax3 = ((TextField<String>) getComponent()).getModelObject();
	        }
	    });

		createDataView();
		dataView.setOutputMarkupId(true);
		
		final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
		dataContainer.setOutputMarkupId(true);
		dataContainer.addOrReplace(dataView);

		final Form<String> form = new Form<String>("resultSearchForm"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit() {
				setResponse(params);
			}
		};
		form.setOutputMarkupId(true);
		form.add(actf1);
		form.add(actf2);
		form.add(tf3);
		form.add(imgSwitch);
		form.add(dataContainer);

		add(form);
		
		add(new AjaxEventBehavior("beforeunload") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				logger.info("before unload event");	
				logger.info("session is invalidated = " + getSession().isSessionInvalidated());
			}
		});
		
		add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(10)));
	}

	private void createDataView() {
		logger.info("create data view");
		final List<BusLineDto> busLines = loadRelations();
		dataView = new PropertyListView<BusLineDto>("rows", busLines) {
			  private static final long serialVersionUID = 1L;
			  @Override
			  protected void populateItem(ListItem<BusLineDto> item) {
				  final BusLineDto busLine = item.getModelObject();
				  List<String> placesList = createSmallPlaces(busLine);
				  Model<String> imgModel = new Model<>();
				  Image imgBus = new Image( "bus_img", imgModel);
				  imgBus.setImageResourceReference(busResourceReference);

				  item.add(imgBus);
				  if("en".equalsIgnoreCase(lang)) {
					  final Label label = new Label("carrier", busLine.getCarrier());
					  label.add(new AttributeModifier("style", "text-align: left"));
					  item.add(label);
				  } else if("mk".equalsIgnoreCase(lang)) {
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

				  Label label3 = new Label("travelTime", generateTravelTime(busLine.getTravelTime()));
				  label3.add(new AttributeModifier("style", "text-align: left"));
				  item.add(label3);

				  Label label4 = new Label("details", new StringResourceModel("avtobusi.searchpage.btn.details", this));
				  item.add(label4);

				  Label label5 = new Label("travelDistance", busLine.getDistance());
				  label5.add(new AttributeModifier("style", "text-align: left"));
				  item.add(label5);
				  
				  StringResourceModel priceModel = new StringResourceModel("avtobusi.price.denar.symbol", this);
				  String priceBL = busLine.getPrice() == null ? "?" : busLine.getPrice();
				  Label label6 = new Label("price", " " + priceBL);
				  label6.add(new AttributeModifier("style", "text-align: left"));
				  item.add(label6);
				  
				  label6 = new Label("priceSymbol", priceModel);
				  label6.add(new AttributeModifier("style", "text-align: left"));
				  item.add(label6);
				  
				  StringResourceModel priceReturnModel = new StringResourceModel("avtobusi.price.denar.return.symbol", this);
				  StringResourceModel returnModel = new StringResourceModel("avtobusi.price.return", this);
				  
				  String returnPriceBL = busLine.getPriceReturn() == null ? "?" : busLine.getPriceReturn();
				  //String returnPrice = " " + returnPriceBL;
				  String returnPrice = " " + returnPriceBL + " " + (returnModel == null ? "" : returnModel.getObject());
				  
				  Label label7 = new Label("priceReturn", returnPrice);
				  label7.add(new AttributeModifier("style", "text-align: left"));
				  item.add(label7);
				  
				  label7 = new Label("priceReturnSymbol", priceReturnModel);
				  label7.add(new AttributeModifier("style", "text-align: left"));
				  item.add(label7);

				  final WebMarkupContainer detailsPanel = new WebMarkupContainer("detailsPanel");
				  detailsPanel.setOutputMarkupPlaceholderTag(true);
				  detailsPanel.setOutputMarkupId(true);
				  ListView<String> circles = new ListView<String>("circles", placesList.subList(0, placesList.size() - 1)) {
					  private static final long serialVersionUID = 1L;
					  @Override
					  protected void populateItem(ListItem<String> item) {

					  }
				  };
				  detailsPanel.add(circles);

				  ListView<String> places = new ListView<String>("places", placesList) {
					  private static final long serialVersionUID = 1L;
					  @Override
					  protected void populateItem(ListItem<String> item2) {
						  String object = item2.getModelObject();
						  String[] arr = object.split("=");
						  String data = arr[0] + " " + convertTime(arr[1]);
						  Label label = new Label("segment-place", data);
					      item2.add(label);
					  }
				  };
				  detailsPanel.add(places);
				  
				  //Do not use StringResourceModel!!
				  String imeNaLinijaSram = Application.get().getResourceSettings().getLocalizer().getString("avtobusi.resultPage.table.imenalinija", detailsPanel); 
				  Label imeNaLinijaLbl = new Label("imeNaLinija", imeNaLinijaSram);
				  imeNaLinijaLbl.setOutputMarkupId(true);
				  
				  Label lineNameLbl = new Label("lineName", busLine.getLineName());
				  lineNameLbl.setOutputMarkupId(true);
				  
				  //Do not use StringResourceModel!! 
				  String redenBrojSram = Application.get().getResourceSettings().getLocalizer().getString("avtobusi.resultPage.table.redenbrojnalinija", detailsPanel);
				  Label redenBrojLbl = new Label("redenBrojNaLinija", redenBrojSram);
				  redenBrojLbl.setOutputMarkupId(true);
				  
				  Label lineOrderLbl = new Label("lineOrder", busLine.getRedenBroj());
				  lineOrderLbl.setOutputMarkupId(true);
				  
				  String komentar = busLine.getComment();
				  Label commentLbl = new Label("comment", komentar);
				  commentLbl.setOutputMarkupId(true);
				  
				  detailsPanel.add(imeNaLinijaLbl);
				  detailsPanel.add(lineNameLbl);
				  
				  detailsPanel.add(redenBrojLbl);
				  detailsPanel.add(lineOrderLbl);
				  
				  detailsPanel.add(commentLbl);
				  
				  detailsPanel.setVisible(false);
				  item.add(detailsPanel);

				  Label glyphicon = new Label("glyphicon", "");
				  glyphicon.add(getAjaxBehavior(detailsPanel, glyphicon));
				  label4.add(getAjaxBehavior(detailsPanel, glyphicon));
				  item.add(glyphicon);
			  }
		};
	}

	private String convertTime(String time) {
		String newTime = time;
		if(time != null && (time.length() == 4)) {
			newTime = "0"+time;
		} else if(time != null && (time.length() == 7)) {
			newTime = "0"+time;
			newTime = newTime.substring(0, 5);
		} else if(time != null && (time.length() == 8)) {
			newTime = newTime.substring(0, 5);
		}

		if(newTime != null && newTime.contains(".")) {
			newTime = newTime.replace(".", ":");
		}

		return newTime;
	}

	private List<String> createSmallPlaces(BusLineDto busLine) {
		String[] array = new String[0];
		if("mk".equalsIgnoreCase(lang) && StringUtils.isNotBlank(busLine.getSmallPlaces())) {
			array = busLine.getSmallPlaces().split(",");
		} else if(StringUtils.isNotBlank(busLine.getSmallPlacesLatin())){
			array = busLine.getSmallPlacesLatin().split(",");
		}
		return Arrays.asList(array);
	}

	private AjaxEventBehavior getAjaxBehavior(WebMarkupContainer detailsPanel, Label glyphicon) {
		return new AjaxEventBehavior("click") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				if (detailsPanel.isVisible()) {
					detailsPanel.setVisible(false);
					glyphicon.add(AttributeModifier.replace("class", "glyphicon glyphicon-plus-sign"));
				} else {
					detailsPanel.setVisible(true);
					glyphicon.add(AttributeModifier.replace("class", "glyphicon glyphicon-minus-sign"));
				}
				target.add(detailsPanel, glyphicon);
			}
		};
	}

	private String generateTravelTime(String travelTime) {
		String result;
		try {
			String[] tta = travelTime.split(":");
			String tth = tta[0] + " " + ttmh;
			String ttm = "";
			if(hasMinutes(tta[1])) {
				ttm = tta[1] + " " + ttmm;
			}
			result = tth + " " + ttm;
		} catch(Exception e) {
			result = travelTime;
		}
		return result;
	}

	private boolean hasMinutes(String s) {
		try {
			Integer min = Integer.valueOf(s);
			if(min > 0) {
				return true;
			}
			return false;
		}catch(Exception e) {
			return false;
		}
	}

	private List<BusLineDto> loadRelations() {
		List<BusLineDto> dtos = new ArrayList<BusLineDto>();
		if(busLineService != null) {
			String departurePlace = searchBean.getDeparturePlace();
			String destinationPlace = searchBean.getDestinationPlace();
			String date = searchBean.getDepartureDate();
			if(StringUtils.isNoneBlank(departurePlace, destinationPlace, date)) {
				dtos = busLineService.getRelation(departurePlace, destinationPlace, date);
			}
		} 
		return dtos;
	}
	
	public class PurchasePanel extends Panel {
		private static final long serialVersionUID = 1L;
		public PurchasePanel(String id) {
			super(id);
			add(new Button(id, new Model<>("Purchase")));
		}
	}

	@Override
	protected void setResponse(PageParameters parameters) {
		PageParameters params = new PageParameters();
		String language = "mk";
		if(!parameters.get("lang").isEmpty()) {
			language = parameters.get("lang").toString();
		}
		if(language != null) {
			params.add("lang", language);
		}
		
		if(ajax1 != null) {
			String departurePlace = ajax1;
			if(StringUtils.isNotBlank(departurePlace)) {
				if(language.equals("mk")) {
					departurePlace = OperationsUtil.createMacedonianName(departurePlace);
				} else if(language.equals("en")) {
					departurePlace = OperationsUtil.createLatinName(departurePlace);
				}
				params.add("departure", departurePlace);
			}
		} else {
			String departurePlace = searchBean.getDeparturePlace();
			if(StringUtils.isNotBlank(departurePlace)) {
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
			if(StringUtils.isNotBlank(destinationPlace)) {
				if(language.equals("mk")) {
					destinationPlace = OperationsUtil.createMacedonianName(destinationPlace);
				} else if(language.equals("en")) {
					destinationPlace = OperationsUtil.createLatinName(destinationPlace);
				}
				params.add("destination", destinationPlace);
			}
		} else if(searchBean.getDestinationPlace() != null) {
			String destinationPlace = searchBean.getDestinationPlace();
			if(StringUtils.isNotBlank(destinationPlace)) {
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
		} else if(searchBean.getDepartureDate() != null) {
			String departureDate = searchBean.getDepartureDate();
			if(StringUtils.isNotBlank(departureDate) && departureDate.contains("/")) {
				departureDate = departureDate.replace("/", "-");
			} 
			params.add("date", departureDate);
		}

		logger.info("result page response params = " + params);
		logger.info("result page response parameters = " + parameters);
		ResultPage page = new ResultPage(params);
		setResponsePage(page);
	}
	
}
