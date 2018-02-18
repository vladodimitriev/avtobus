package mk.mladen.avtobusi.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.time.Duration;

import mk.mladen.avtobusi.beans.SearchBean;
import mk.mladen.avtobusi.dto.CarrierDto;
import mk.mladen.avtobusi.service.CarrierService;
import mk.mladen.avtobusi.service.PlaceService;

public class AdminCarrierPage extends BaseAdminPage {
	
	private static final Logger logger = LogManager.getLogger(AdminCarrierPage.class);

    private static final long serialVersionUID = 1L;

    private SearchBean searchBean = new SearchBean();

    @SpringBean
    private PlaceService placeService;

    @SpringBean
    private CarrierService carrierService;

    private PropertyListView<CarrierDto> dataView;

    private WebMarkupContainer wmc;

    public AdminCarrierPage(PageParameters parameters) {
        super(parameters);
        AutoCompleteSettings opts = new AutoCompleteSettings();
        opts.setShowListOnEmptyInput(true);

        TextField<String> actf1 = new TextField<String>("carrier", new PropertyModel<String>(searchBean, "carrier"));
        actf1.setOutputMarkupId(true);

        dataView = createDataView();
        dataView.setOutputMarkupId(true);

        wmc = new WebMarkupContainer("wmc");
        wmc.setOutputMarkupId(true);

        Form<String> form = new Form<String>("resultSearchForm"){
            private static final long serialVersionUID = 1L;
			@Override
            protected void onSubmit() {
                dataView = createDataView();
                dataView.setOutputMarkupId(true);
                wmc.addOrReplace(dataView);
            }
        };
        form.add(actf1);

        ModalWindow modalWindow = new ModalWindow("modalWindow");
        modalWindow.setOutputMarkupId(true);
        modalWindow.setResizable(true);
        modalWindow.setInitialHeight(620);
        modalWindow.setContent(new ModalPanelCarrierAdd(modalWindow.getContentId(), modalWindow));
        modalWindow.showUnloadConfirmation(false);
        modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
        {
            private static final long serialVersionUID = 1L;
			@Override
            public void onClose(AjaxRequestTarget target)
            {
                dataView = createDataView();
                wmc.addOrReplace(dataView);
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

    private PropertyListView<CarrierDto> createDataView() {
        List<CarrierDto> places = loadPlaces();
        PropertyListView<CarrierDto> dataView = new PropertyListView<CarrierDto>("rows", places) {
            private static final long serialVersionUID = 1L;
			@Override
            protected void populateItem(ListItem<CarrierDto> item) {
                final CarrierDto placeDto = item.getModelObject();

                Label labelId = new Label("id", placeDto.getId());
                item.add(labelId);

                Label labelName = new Label("name", placeDto.getName());
                item.add(labelName);

                Label labelNameCyrillic = new Label("nameCyrillic", placeDto.getNameCyrilic());
                item.add(labelNameCyrillic);

                ModalWindow modalWindowUpdate = createModalWindowUpdate(placeDto);
                item.add(modalWindowUpdate);

                ModalWindow modalWindowDelete = createModalWindowDelete(placeDto);
                item.add(modalWindowDelete);

                AjaxLink<Void> link1 = new AjaxLink<Void>("detailsLink") {
                    private static final long serialVersionUID = 1L;
					@Override
                    public void onClick(AjaxRequestTarget target) {
                        modalWindowUpdate.show(target);
                    }
                };
                item.add(link1);
            }
        };
        return dataView;
    }

    private ModalWindow createModalWindowUpdate(CarrierDto placeDto) {
        ModalWindow modalWindowUpdate = new ModalWindow("modalWindowUpdate");
        modalWindowUpdate.setOutputMarkupId(true);
        modalWindowUpdate.setContent(new ModalPanelCarrierUpdate(modalWindowUpdate.getContentId(), placeDto, modalWindowUpdate));
        modalWindowUpdate.setResizable(true);
        modalWindowUpdate.setInitialHeight(620);
        modalWindowUpdate.showUnloadConfirmation(false);
        modalWindowUpdate.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
        {
            private static final long serialVersionUID = 1L;
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

    private ModalWindow createModalWindowDelete(CarrierDto placeDto) {
        ModalWindow modalWindowDelete = new ModalWindow("modalWindowDelete");
        modalWindowDelete.setOutputMarkupId(true);
        modalWindowDelete.setContent(new ModalPanelCarrierDelete(modalWindowDelete.getContentId(), placeDto, modalWindowDelete));
        modalWindowDelete.setResizable(true);
        modalWindowDelete.setInitialHeight(400);
        modalWindowDelete.setInitialWidth(800);
        return modalWindowDelete;
    }

    private List<CarrierDto> loadPlaces() {
        List<CarrierDto> dtos = new ArrayList<CarrierDto>();
        if(carrierService != null) {
            String carrier = searchBean.getCarrier();
            if(StringUtils.isNotBlank(carrier)) {
                dtos = carrierService.getCarriers(carrier);
            } else {
                dtos = carrierService.getAllCarriers();
            }
        }
        return dtos;
    }

    @Override
    protected void setResponse(PageParameters params) {
        setResponsePage(new AdminCarrierPage(params));
    }
}
