package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.dto.PlaceDto;
import mk.mladen.avtobusi.service.PlaceService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ModalPanelPlaceUpdate extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private PlaceService placeService;

    public ModalPanelPlaceUpdate(String id, PlaceDto placeDto, ModalWindow window) {
        super(id);
        Form<Void> form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;
			@Override
            protected void onSubmit() {
                placeService.updatePlace(placeDto);
            }
        };

        PropertyModel<String> nameModel = new PropertyModel<String>(placeDto, "name");
        PropertyModel<String> nameCyrillicModel = new PropertyModel<String>(placeDto, "nameCyrilic");
        PropertyModel<String> countryModel = new PropertyModel<String>(placeDto, "country");
        PropertyModel<Integer> importanceModel = new PropertyModel<Integer>(placeDto, "importance");

        TextField<String> nameTxt = new TextField<String>("nameTxt", nameModel);
        TextField<String> nameCyrillicTxt = new TextField<String>("nameCyrillicTxt", nameCyrillicModel);
        TextField<String> countryTxt = new TextField<String>("countryTxt", countryModel);
        TextField<Integer> importanceTxt = new TextField<Integer>("importanceTxt", importanceModel);

        form.add(nameTxt);
        form.add(nameCyrillicTxt);
        form.add(countryTxt);
        form.add(importanceTxt);

        AjaxLink<Void> cancelLink = new AjaxLink<Void>("cancelLink") {
			private static final long serialVersionUID = 1L;
			@Override
            public void onClick(AjaxRequestTarget target) {
                window.close(target);
            }
        };

        AjaxButton saveBtn = new AjaxButton("saveBtn") {
        	private static final long serialVersionUID = 1L;
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                window.close(target);
            }
        };

        form.add(cancelLink);
        form.add(saveBtn);
        add(form);
    }
}
