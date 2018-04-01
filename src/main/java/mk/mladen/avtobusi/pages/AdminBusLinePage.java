package mk.mladen.avtobusi.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import mk.mladen.avtobusi.WicketApplication;
import mk.mladen.avtobusi.beans.DeleteBean;
import mk.mladen.avtobusi.beans.SearchBean;
import mk.mladen.avtobusi.beans.UpdateBean;
import mk.mladen.avtobusi.dto.BusLineDto;
import mk.mladen.avtobusi.service.BusLineService;
import mk.mladen.avtobusi.service.PlaceService;

public class AdminBusLinePage extends BaseAdminPage {

    private static final long serialVersionUID = 1L;
    
    private static Logger logger = LogManager.getLogger(AdminBusLinePage.class);

    private SearchBean searchBean = new SearchBean();

    @SpringBean
    private BusLineService busLineService;

    @SpringBean
    private PlaceService placeService;

    private ResourceReference busResourceReference;

    private PropertyListView<BusLineDto> dataView;

    private WebMarkupContainer wmc;
    
    private String dataItemId;

    public AdminBusLinePage(PageParameters parameters) {
        super(parameters);
        Model<String> imgSwitchModel = new Model<String>();
        Image imgSwitch = new Image( "switch-img", imgSwitchModel);
        ResourceReference rr1 = new PackageResourceReference(WicketApplication.class, "static/img/switch50x999.jpg");
        imgSwitch.setImageResourceReference(rr1);
        
        busResourceReference = new PackageResourceReference(WicketApplication.class, "static/img/bus21x21x999.jpg");

        AutoCompleteSettings opts = new AutoCompleteSettings();
        opts.setShowListOnEmptyInput(true);

        AutoCompleteTextField<String> actf1 = new AutoCompleteTextField<String>("departurePlace", new PropertyModel<String>(searchBean, "departurePlace"), opts) {
			private static final long serialVersionUID = 1L;
			@Override
            public Iterator<String> getChoices(String input) {
                List<String> choices = placeService.findAllPlacesNamesByLanguageAndName(lang, input);
                return choices.iterator();
            }
        };
        actf1.setRequired(true);
        actf1.setOutputMarkupId(true);

        AutoCompleteTextField<String> actf2 = new AutoCompleteTextField<String>("destinationPlace", new PropertyModel<String>(searchBean, "destinationPlace"), opts) {
			private static final long serialVersionUID = 1L;
			@Override
            protected Iterator<String> getChoices(String input) {
                List<String> choices = placeService.findAllPlacesNamesByLanguageAndName(lang, input);
                return choices.iterator();
            }
        };
        actf2.setRequired(true);
        actf2.setOutputMarkupId(true);

        createDataView(null);

        wmc = new WebMarkupContainer("wmc");
        wmc.setOutputMarkupId(true);

        Form<Void> form = new Form<Void>("resultSearchForm"){
			private static final long serialVersionUID = 1L;
			@Override
            protected void onSubmit() {
                updateDataView();
            }
        };
        form.setOutputMarkupId(true);
        form.add(actf1);
        form.add(actf2);
        form.add(imgSwitch);

        ModalWindow modalWindow = new ModalWindow("modalWindowAdd");
        modalWindow.setOutputMarkupId(true);
        modalWindow.setResizable(true);
        modalWindow.setInitialHeight(620);
        ModalPanelBusLineAdd modalPanelAdd = new ModalPanelBusLineAdd(modalWindow.getContentId(), modalWindow);
		modalWindow.setContent(modalPanelAdd);
        modalWindow.showUnloadConfirmation(false);
        modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
        {
			private static final long serialVersionUID = 1L;
			@Override
            public void onClose(AjaxRequestTarget target)
            {
                updateDataView();
                target.add(wmc);
            }
        });
        add(modalWindow);

        AjaxLink<Void> link = new AjaxLink<Void>("addLink") {
			private static final long serialVersionUID = 1L;
			@Override
            public void onClick(AjaxRequestTarget target) {
                modalWindow.show(target);
            }
        };
        form.add(link);
        wmc.add(dataView);
        add(form);
        add(wmc);
    }

    private void updateDataView() {
    	List<BusLineDto> busLines = loadRelations();
    	dataView.setList(busLines);
    };	
    
    private void createDataView(String itemId) {
        List<BusLineDto> busLines = loadRelations();
        dataView = new PropertyListView<BusLineDto>("rows", busLines) {
			private static final long serialVersionUID = 1L;
			@Override
            protected void populateItem(ListItem<BusLineDto> item) {
                final BusLineDto busLine = item.getModelObject();

                Model<String> imgModel = new Model<String>();
                Image bus_img = new Image( "bus_img", imgModel);
                bus_img.setImageResourceReference(busResourceReference);
                item.add(bus_img);

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

                if("en".equalsIgnoreCase(lang)) {
                    final Label label = new Label("carrier", busLine.getCarrier());
                    label.add(new AttributeModifier("style", "text-align: left"));
                    item.add(label);
                } else if("mk".equalsIgnoreCase(lang)) {
                    final Label label = new Label("carrier", busLine.getCarrierCyrilic());
                    label.add(new AttributeModifier("style", "text-align: left"));
                    item.add(label);
                }

                Label label3 = new Label("travelTime", generateTravelTime(busLine.getTravelTime()));
                label3.add(new AttributeModifier("style", "text-align: left"));
                item.add(label3);

                Label label5 = new Label("travelDistance", busLine.getDistance());
                label5.add(new AttributeModifier("style", "text-align: left"));
                item.add(label5);

                UpdateBean updateBean = createUpdateBean(busLine);
                ModalWindow modalWindowUpdate = createModalWindowUpdate(updateBean, item.getId());
                item.add(modalWindowUpdate);

                DeleteBean deleteBean = createDeleteBean(busLine);
                deleteBean.setId(String.valueOf(busLine.getId()));
                ModalWindow modalWindowDelete = createModalWindowDelete(deleteBean);
                item.add(modalWindowDelete);

                AjaxLink<Void> link1 = new AjaxLink<Void>("detailsLink") {
					private static final long serialVersionUID = 1L;
					@Override
                    public void onClick(AjaxRequestTarget target) {
                        modalWindowUpdate.show(target);
                    }
                };
                item.add(link1);

                AjaxLink<Void> link2 = new AjaxLink<Void>("deleteLink") {
					private static final long serialVersionUID = 1L;
					@Override
                    public void onClick(AjaxRequestTarget target) {
                        modalWindowDelete.show(target);
                    }
                };
                item.add(link2);
                if(StringUtils.isNotBlank(dataItemId) && dataItemId.equalsIgnoreCase(item.getId())) {
                	item.add(AttributeModifier.replace("class", "row article-row-visited"));
                }
                
                item.setOutputMarkupId(true);
            }
        };
        dataView.setOutputMarkupId(true);
    }

    private String generateTravelTime(String travelTime) {
    	if(StringUtils.isBlank(travelTime)) {
    		return StringUtils.EMPTY;
    	}
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
        	logger.error(e);
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
        	logger.error(e);
            return false;
        }
    }

    private DeleteBean createDeleteBean(BusLineDto busLine) {
        DeleteBean deleteBean = new DeleteBean();
        deleteBean.setId(String.valueOf(busLine.getId()));
        deleteBean.setArrivalPlace(busLine.getDestinationPlace());
        deleteBean.setArrivalTime(busLine.getArrivalTime());
        deleteBean.setDeparturePlace(busLine.getDeparturePlace());
        deleteBean.setDepartureTime(busLine.getDepartureTime());
        deleteBean.setCarrier(busLine.getCarrier());
        return deleteBean;
    }

    private ModalWindow createModalWindowUpdate(UpdateBean updateBean, String itemId) {
        ModalWindow modalWindowUpdate = new ModalWindow("modalWindowUpdate");
        modalWindowUpdate.setOutputMarkupId(true);
        modalWindowUpdate.setContent(new ModalPanelBusLineUpdate(modalWindowUpdate.getContentId(), updateBean, modalWindowUpdate));
        modalWindowUpdate.setResizable(true);
        modalWindowUpdate.setInitialHeight(620);
        modalWindowUpdate.showUnloadConfirmation(false);
        modalWindowUpdate.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			private static final long serialVersionUID = 1L;
			@Override
            public void onClose(AjaxRequestTarget target)
            {
				dataItemId = itemId;
				updateDataView();
                target.add(wmc);
            }
        });
        return modalWindowUpdate;
    }

    private ModalWindow createModalWindowDelete(DeleteBean deleteBean) {
        ModalWindow modalWindowDelete = new ModalWindow("modalWindowDelete");
        modalWindowDelete.setOutputMarkupId(true);
        ModalPanelBusLineDelete modalPanel = new ModalPanelBusLineDelete(modalWindowDelete.getContentId(), deleteBean, modalWindowDelete);
		modalWindowDelete.setContent(modalPanel);
        modalWindowDelete.setResizable(true);
        modalWindowDelete.setInitialHeight(400);
        modalWindowDelete.setInitialWidth(800);
        modalWindowDelete.showUnloadConfirmation(false);
        return modalWindowDelete;
    }

    private UpdateBean createUpdateBean(BusLineDto busLine) {
        UpdateBean updateBean = new UpdateBean();
        updateBean.setId(String.valueOf(busLine.getId()));
        updateBean.setArrivalPlace(busLine.getDestinationPlace());
        updateBean.setArrivalTime(busLine.getArrivalTime());
        updateBean.setCarrier(busLine.getCarrier());
        updateBean.setComment(busLine.getComment());
        updateBean.setHasPrice(busLine.getPrice());
        updateBean.setOperationDays(busLine.getOperationDays());
        updateBean.setOperationMonths(busLine.getOperationMonths());
        updateBean.setOperationPeriod(busLine.getOperationPeriod());
        updateBean.setDeparturePlace(busLine.getDeparturePlace());
        updateBean.setDepartureTime(busLine.getDepartureTime());
        updateBean.setPrice(busLine.getPrice());
        updateBean.setLineNumber(busLine.getLineNumber());
        updateBean.setPriceReturn(busLine.getPriceReturn());
        updateBean.setTravelTime(busLine.getTravelTime());
        updateBean.setDeparturePlaceId(busLine.getDeparturePlaceId());
        updateBean.setDestinationPlaceId(busLine.getDestinationPlaceId());
        updateBean.setCarrierId(busLine.getCarrierId());
        updateBean.setRedenBroj(busLine.getRedenBroj());
        updateBean.setLineName(busLine.getLineName());
        updateBean.setSmallPlaces(busLine.getSmallPlaces());
        updateBean.setSmallPlacesLatin(busLine.getSmallPlacesLatin());
        return updateBean;
    }

    private List<BusLineDto> loadRelations() {
        List<BusLineDto> dtos = new ArrayList<BusLineDto>();
        if(busLineService != null) {
            String departurePlace = searchBean.getDeparturePlace();
            String destinationPlace = searchBean.getDestinationPlace();
            if(StringUtils.isNotBlank(departurePlace) && StringUtils.isNotBlank(destinationPlace)) {
                dtos = busLineService.getRelation(departurePlace, destinationPlace);
            }
        }
        return dtos;
    }

    @Override
    protected void setResponse(PageParameters params) {
        setResponsePage(new AdminBusLinePage(params));
    }
}
