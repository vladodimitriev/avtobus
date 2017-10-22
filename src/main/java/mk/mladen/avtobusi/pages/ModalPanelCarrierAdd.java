package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.dto.CarrierDto;
import mk.mladen.avtobusi.dto.PlaceDto;
import mk.mladen.avtobusi.service.CarrierService;
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

public class ModalPanelCarrierAdd extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private CarrierService carrierService;

    public ModalPanelCarrierAdd(String id, ModalWindow window) {
        super(id);
        CarrierDto dto = new CarrierDto();
        Form form = new Form("addForm") {
            @Override
            protected void onSubmit() {
                //carrierService.addNewCarrier(placeDto);
            }
        };

        PropertyModel nameModel = new PropertyModel(dto, "name");
        PropertyModel nameCyrillicModel = new PropertyModel(dto, "nameCyrilic");
        PropertyModel placeModel = new PropertyModel(dto, "place");

        TextField<String> nameTxt = new TextField<String>("nameTxt", nameModel);
        TextField<String> nameCyrillicTxt = new TextField<String>("nameCyrillicTxt", nameCyrillicModel);
        TextField<String> placeTxt = new TextField<String>("placeTxt", placeModel);

        form.add(nameTxt);
        form.add(nameCyrillicTxt);
        form.add(placeTxt);

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
