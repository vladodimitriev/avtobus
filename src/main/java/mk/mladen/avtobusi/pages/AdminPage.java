package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.WicketApplication;
import mk.mladen.avtobusi.beans.AddBean;
import mk.mladen.avtobusi.beans.SearchBean;
import mk.mladen.avtobusi.dto.BusLineDto;
import mk.mladen.avtobusi.service.BusLineService;
import mk.mladen.avtobusi.service.PlaceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
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

        AutoCompleteTextField<String> actf1 = new AutoCompleteTextField<String>("departurePlace", new PropertyModel(searchBean, "departurePlace")) {
            @Override
            public Iterator<String> getChoices(String input) {
                if (StringUtils.isBlank(input)) {
                    List<String> emptyList = placeService.findCommonPlaces(lang);
                    return emptyList.iterator();
                }
                List<String> choices = placeService.findAllPlacesNamesByLanguageAndName(lang, input);
                return choices.iterator();
            }
        };
        actf1.setRequired(true);
        actf1.setOutputMarkupId(true);
        actf1.add(new AjaxEventBehavior("click") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                //setResponsePage(MyWebPage.class);
                searchBean.setDeparturePlace("Skopje");
                target.add(actf1);
            }
        });

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
        actf2.setRequired(true);
        actf2.setOutputMarkupId(true);
        actf2.add(new AjaxEventBehavior("click") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                searchBean.setDestinationPlace("Negotino");
                target.add(actf2);
            }
        });

        //PropertyListView<BusLineDto> dataView = createDataView();
        dataView = createDataView();
        dataView.setOutputMarkupId(true);

        Form form = new Form("resultSearchForm"){
            @Override
            protected void onSubmit() {
                System.out.println("submit btn");
                dataView = createDataView();
                dataView.setOutputMarkupId(true);
                this.addOrReplace(dataView);
            }
        };
        form.add(actf1);
        form.add(actf2);

        ModalWindow modal1 = new ModalWindow("modal1");
        modal1.setOutputMarkupId(true);
        modal1.setResizable(true);
        modal1.setInitialHeight(400);
        //modal1.setInitialWidth(400);
        add(modal1);

        modal1.setContent(new ModalPanel1(modal1.getContentId(), null));
        //modal1.setTitle("Modal window\n'panel\" content.");

        modal1.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
            @Override
            public boolean onCloseButtonClicked(AjaxRequestTarget target) {
                //setResult("Modal window 2 - close button");
                return true;
            }
        });

        modal1.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
            @Override
            public void onClose(AjaxRequestTarget target) {
                //target.add(result);
            }
        });

        Button btn = new Button("addBtn", new Model<>("Add"));
        btn.setOutputMarkupId(true);
        btn.add(new AjaxEventBehavior("click") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                System.out.println("add btn");
                modal1.show(target);
            }
        });

        AjaxLink<String> link = new AjaxLink<String>("addLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                System.out.println("details link");
                modal1.show(target);
            }
        };
        form.add(link);
        form.add(dataView);
        add(form);
    }

    private PageParameters getParams(String language) {
        PageParameters params = new PageParameters();
        if(language != null) {
            params.add("lang", language);
        }

        return params;
    }

    private PropertyListView<BusLineDto> createDataView() {
        System.out.println("create data view");
        List<BusLineDto> busLines = loadRelations();
        //ListDataProvider<BusLineDto> listDataProvider = new ListDataProvider<BusLineDto>(busLines);
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

                AddBean addBean = new AddBean();
                addBean.setId(String.valueOf(busLine.getId()));

                ModalWindow modal2 = new ModalWindow("modal2");
                modal2.setOutputMarkupId(true);
                modal2.setContent(new ModalPanel1(modal2.getContentId(), addBean));
                modal2.setResizable(true);
                modal2.setInitialHeight(400);
                item.add(modal2);
                Button btn1 = new Button("detailsBtn", new Model<>("Details"));
                btn1.add(new AjaxEventBehavior("click") {
                    @Override
                    protected void onEvent(AjaxRequestTarget target) {
                        System.out.println("details btn");
                        modal2.show(target);
                    }
                });


                ModalWindow modal3 = new ModalWindow("modal3");
                modal3.setOutputMarkupId(true);
                modal3.setContent(new ModalPanel1(modal3.getContentId(), addBean));
                modal3.setResizable(true);
                modal3.setInitialHeight(400);
                item.add(modal3);
                Button btn2 = new Button("deleteBtn", new Model<>("Delete"));
                btn2.add(new AjaxEventBehavior("click") {
                    @Override
                    protected void onEvent(AjaxRequestTarget target) {
                        System.out.println("delete btn");
                        modal3.show(target);
                    }
                });


                AjaxLink<String> link1 = new AjaxLink<String>("detailsLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        System.out.println("details link");
                        modal2.show(target);
                    }
                };
                item.add(link1);

                AjaxLink<String> link2 = new AjaxLink<String>("deleteLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        System.out.println("delete link");
                        modal3.show(target);
                    }
                };
                item.add(link2);

            }
        };
        return dataView;
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
