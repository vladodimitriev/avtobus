package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.beans.SearchBean;
import mk.mladen.avtobusi.dto.PlaceDto;
import mk.mladen.avtobusi.service.BusLineService;
import mk.mladen.avtobusi.service.PlaceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdminPlacePage extends BaseAdminPage {

    private static final long serialVersionUID = 1L;

    private SearchBean searchBean = new SearchBean();

    @SpringBean
    private PlaceService placeService;

    private PropertyListView<PlaceDto> dataView;

    private WebMarkupContainer wmc;

    public AdminPlacePage(PageParameters parameters) {
        super(parameters);
        AutoCompleteSettings opts = new AutoCompleteSettings();
        opts.setShowListOnEmptyInput(true);

        TextField<String> actf1 = new TextField<String>("place", new PropertyModel(searchBean, "place"));
        actf1.setOutputMarkupId(true);

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

        ModalWindow modalWindow = new ModalWindow("modalWindow");
        modalWindow.setOutputMarkupId(true);
        modalWindow.setResizable(true);
        modalWindow.setInitialHeight(620);
        modalWindow.setContent(new ModalPanelPlaceAdd(modalWindow.getContentId(), modalWindow));
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

    private PropertyListView<PlaceDto> createDataView() {
        List<PlaceDto> places = loadPlaces();
        PropertyListView<PlaceDto> dataView = new PropertyListView<PlaceDto>("rows", places) {
            @Override
            protected void populateItem(ListItem<PlaceDto> item) {
                final PlaceDto placeDto = item.getModelObject();

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
                //item.add(link2);
            }
        };
        return dataView;
    }

    private ModalWindow createModalWindowUpdate(PlaceDto placeDto) {
        ModalWindow modalWindowUpdate = new ModalWindow("modalWindowUpdate");
        modalWindowUpdate.setOutputMarkupId(true);
        modalWindowUpdate.setContent(new ModalPanelPlaceUpdate(modalWindowUpdate.getContentId(), placeDto, modalWindowUpdate));
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

    private ModalWindow createModalWindowDelete(PlaceDto placeDto) {
        ModalWindow modalWindowDelete = new ModalWindow("modalWindowDelete");
        modalWindowDelete.setOutputMarkupId(true);
        modalWindowDelete.setContent(new ModalPanelPlaceDelete(modalWindowDelete.getContentId(), placeDto, modalWindowDelete));
        modalWindowDelete.setResizable(true);
        modalWindowDelete.setInitialHeight(400);
        modalWindowDelete.setInitialWidth(800);
        return modalWindowDelete;
    }

    private List<PlaceDto> loadPlaces() {
        List<PlaceDto> dtos = new ArrayList<PlaceDto>();
        if(placeService != null) {
            String place = searchBean.getPlace();
            if(StringUtils.isNotBlank(place)) {
                dtos = placeService.findPlaces(place);
            } else {
                dtos = placeService.findAllPlaces();
            }
        }
        return dtos;
    }

    @Override
    protected void setResponse(PageParameters params) {
        setResponsePage(AdminPlacePage.class, params);
    }
}
