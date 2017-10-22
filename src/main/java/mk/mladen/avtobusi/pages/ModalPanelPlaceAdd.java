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

public class ModalPanelPlaceAdd extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private PlaceService placeService;

    public ModalPanelPlaceAdd(String id, ModalWindow window) {
        super(id);
        PlaceDto placeDto = new PlaceDto();
        Form form = new Form("addForm") {
            @Override
            protected void onSubmit() {
                //placeService.addNewPlace(placeDto);
            }
        };

        PropertyModel nameModel = new PropertyModel(placeDto, "name");
        PropertyModel nameCyrillicModel = new PropertyModel(placeDto, "nameCyrilic");
        PropertyModel countryModel = new PropertyModel(placeDto, "country");
        PropertyModel importanceModel = new PropertyModel(placeDto, "importance");

        TextField<String> nameTxt = new TextField<String>("nameTxt", nameModel);
        TextField<String> nameCyrillicTxt = new TextField<String>("nameCyrillicTxt", nameCyrillicModel);
        TextField<String> countryTxt = new TextField<String>("countryTxt", countryModel);
        TextField<Integer> importanceTxt = new TextField<Integer>("importanceTxt", importanceModel);

        form.add(nameTxt);
        form.add(nameCyrillicTxt);
        form.add(countryTxt);
        form.add(importanceTxt);

        AjaxLink<String> cancelLink = new AjaxLink<String>("cancelLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                window.close(target);
            }
        };

        AjaxButton saveBtn = new AjaxButton("saveBtn") {
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
