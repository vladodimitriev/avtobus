package mk.mladen.avtobusi.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
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

import mk.mladen.avtobusi.beans.SearchBean;
import mk.mladen.avtobusi.dto.PlaceDto;
import mk.mladen.avtobusi.service.PlaceService;

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

        TextField<String> actf1 = new TextField<String>("place", new PropertyModel<String>(searchBean, "place"));
        actf1.setOutputMarkupId(true);

        createDataView();

        wmc = new WebMarkupContainer("wmc");
        wmc.setOutputMarkupId(true);

        Form<Void> form = new Form<Void>("resultSearchForm"){
			private static final long serialVersionUID = 1L;
			@Override
            protected void onSubmit() {
                updateDataView();
            }
        };
        form.add(actf1);

        ModalWindow modalWindow = new ModalWindow("modalWindow");
        modalWindow.setOutputMarkupId(true);
        modalWindow.setResizable(true);
        modalWindow.setInitialHeight(620);
        modalWindow.setContent(new ModalPanelPlaceAdd(modalWindow.getContentId(), modalWindow));
        modalWindow.showUnloadConfirmation(false);
        modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
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
    	List<PlaceDto> places = loadPlaces();
    	dataView.setList(places);
    }
    
    private void createDataView() {
        List<PlaceDto> places = loadPlaces();
        dataView = new PropertyListView<PlaceDto>("rows", places) {
			private static final long serialVersionUID = 1L;
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

                AjaxLink<Void> link1 = new AjaxLink<Void>("detailsLink") {
					private static final long serialVersionUID = 1L;
					@Override
                    public void onClick(AjaxRequestTarget target) {
                        modalWindowUpdate.show(target);
                    }
                };
                
                AjaxLink<Void> link2 = new AjaxLink<Void>("deleteLink") {
					private static final long serialVersionUID = 1L;
					@Override
                    public void onClick(AjaxRequestTarget target) {
                        modalWindowDelete.show(target);
                    }
                };
                item.add(link2);
                item.add(link1);
                item.setOutputMarkupId(true);
            }
        };
        dataView.setOutputMarkupId(true);
    }

    private ModalWindow createModalWindowUpdate(PlaceDto placeDto) {
        ModalWindow modalWindowUpdate = new ModalWindow("modalWindowUpdate");
        modalWindowUpdate.setOutputMarkupId(true);
        modalWindowUpdate.setContent(new ModalPanelPlaceUpdate(modalWindowUpdate.getContentId(), placeDto, modalWindowUpdate));
        modalWindowUpdate.setResizable(true);
        modalWindowUpdate.setInitialHeight(620);
        modalWindowUpdate.showUnloadConfirmation(false);
        modalWindowUpdate.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			private static final long serialVersionUID = 1L;
			@Override
            public void onClose(AjaxRequestTarget target) {
                updateDataView();
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
        if(placeService != null && searchBean != null) {
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
        setResponsePage(new AdminPlacePage(params));
    }
}
