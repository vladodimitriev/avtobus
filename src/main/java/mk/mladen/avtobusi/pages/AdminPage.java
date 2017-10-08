package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.WicketApplication;
import mk.mladen.avtobusi.beans.DeleteBean;
import mk.mladen.avtobusi.beans.SearchBean;
import mk.mladen.avtobusi.beans.UpdateBean;
import mk.mladen.avtobusi.dto.BusLineDto;
import mk.mladen.avtobusi.service.BusLineService;
import mk.mladen.avtobusi.service.PlaceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AdminPage extends BasePage {

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

    private WebMarkupContainer wmc;

    public AdminPage(PageParameters parameters) {
        super(parameters);

        Model langLabelModel = new Model(lang);
        Label languageLabel = new Label("language_label", langLabelModel);
        add(languageLabel);

        Model imgModel = new Model();
        Image img = new Image( "language_img", imgModel);

        busResourceReference = new PackageResourceReference(WicketApplication.class, "static/img/bus21x21x999.jpg");

        ResourceReference resourceReference = new ContextRelativeResourceReference("static/flags/4x3/gb.svg");
        if("EN".equalsIgnoreCase(lang)) {
            resourceReference = new ContextRelativeResourceReference("static/flags/4x3/gb.svg");
        } else if("MK".equalsIgnoreCase(lang)) {
            resourceReference = new ContextRelativeResourceReference("static/flags/4x3/mk.svg");
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

        AutoCompleteSettings opts = new AutoCompleteSettings();
        opts.setShowListOnEmptyInput(true);

        AutoCompleteTextField<String> actf1 = new AutoCompleteTextField<String>("departurePlace", new PropertyModel(searchBean, "departurePlace"), opts) {
            @Override
            public Iterator<String> getChoices(String input) {
                List<String> choices = placeService.findAllPlacesNamesByLanguageAndName(lang, input);
                return choices.iterator();
            }
        };
        actf1.setRequired(true);
        actf1.setOutputMarkupId(true);

        AutoCompleteTextField<String> actf2 = new AutoCompleteTextField<String>("destinationPlace", new PropertyModel(searchBean, "destinationPlace"), opts) {
            @Override
            protected Iterator<String> getChoices(String input) {
                List<String> choices = placeService.findAllPlacesNamesByLanguageAndName(lang, input);
                return choices.iterator();
            }
        };
        actf2.setRequired(true);
        actf2.setOutputMarkupId(true);

        dataView = createDataView();
        dataView.setOutputMarkupId(true);

        wmc = new WebMarkupContainer("wmc");
        wmc.setOutputMarkupId(true);

        Form form = new Form("resultSearchForm"){
            @Override
            protected void onSubmit() {
                dataView = createDataView();
                dataView.setOutputMarkupId(true);
                wmc.addOrReplace(dataView);
            }
        };
        form.add(actf1);
        form.add(actf2);

        ModalWindow modalWindow = new ModalWindow("modalWindow");
        modalWindow.setOutputMarkupId(true);
        modalWindow.setResizable(true);
        modalWindow.setInitialHeight(620);
        modalWindow.setContent(new ModalPanelAdd(modalWindow.getContentId(), modalWindow));
        modalWindow.showUnloadConfirmation(false);
        modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
        {
            @Override
            public void onClose(AjaxRequestTarget target)
            {
                dataView = createDataView();
                wmc.addOrReplace(dataView);
                target.add(wmc);
            }
        });
        add(modalWindow);

        AjaxLink<String> link = new AjaxLink<String>("addLink") {
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

    private PageParameters getParams(String language) {
        PageParameters params = new PageParameters();
        if(language != null) {
            params.add("lang", language);
        }

        return params;
    }

    private PropertyListView<BusLineDto> createDataView() {
        List<BusLineDto> busLines = loadRelations();
        PropertyListView<BusLineDto> dataView = new PropertyListView<BusLineDto>("rows", busLines) {
            @Override
            protected void populateItem(ListItem<BusLineDto> item) {
                final BusLineDto busLine = item.getModelObject();

                Model imgModel = new Model();
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

                if("EN".equalsIgnoreCase(lang)) {
                    final Label label = new Label("carrier", busLine.getCarrier());
                    label.add(new AttributeModifier("style", "text-align: left"));
                    item.add(label);
                } else if("MK".equalsIgnoreCase(lang)) {
                    final Label label = new Label("carrier", busLine.getCarrierCyrilic());
                    label.add(new AttributeModifier("style", "text-align: left"));
                    item.add(label);
                }

                Label label5 = new Label("travelDistance", busLine.getDistance());
                label5.add(new AttributeModifier("style", "text-align: left"));
                item.add(label5);

                UpdateBean updateBean = createUpdateBean(busLine);
                ModalWindow modalWindowUpdate = createModalWindowUpdate(updateBean);
                item.add(modalWindowUpdate);

                DeleteBean deleteBean = createDeleteBean(busLine);
                deleteBean.setId(String.valueOf(busLine.getId()));
                ModalWindow modalWindowDelete = createModalWindowDelete(deleteBean);
                item.add(modalWindowDelete);

                AjaxLink<String> link1 = new AjaxLink<String>("detailsLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        modalWindowUpdate.show(target);
                    }
                };
                item.add(link1);

                AjaxLink<String> link2 = new AjaxLink<String>("deleteLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        modalWindowDelete.show(target);
                    }
                };
                item.add(link2);
            }
        };
        return dataView;
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

    private ModalWindow createModalWindowUpdate(UpdateBean updateBean) {
        ModalWindow modalWindowUpdate = new ModalWindow("modalWindowUpdate");
        modalWindowUpdate.setOutputMarkupId(true);
        modalWindowUpdate.setContent(new ModalPanelUpdate(modalWindowUpdate.getContentId(), updateBean, modalWindowUpdate));
        modalWindowUpdate.setResizable(true);
        modalWindowUpdate.setInitialHeight(620);
        modalWindowUpdate.showUnloadConfirmation(false);
        modalWindowUpdate.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
        {
            @Override
            public void onClose(AjaxRequestTarget target)
            {
                dataView = createDataView();
                wmc.addOrReplace(dataView);
                target.add(wmc);
            }
        });
        return modalWindowUpdate;
    }

    private ModalWindow createModalWindowDelete(DeleteBean deleteBean) {
        ModalWindow modalWindowDelete = new ModalWindow("modalWindowDelete");
        modalWindowDelete.setOutputMarkupId(true);
        modalWindowDelete.setContent(new ModalPanelDelete(modalWindowDelete.getContentId(), deleteBean, modalWindowDelete));
        modalWindowDelete.setResizable(true);
        modalWindowDelete.setInitialHeight(400);
        modalWindowDelete.setInitialWidth(800);
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

}
