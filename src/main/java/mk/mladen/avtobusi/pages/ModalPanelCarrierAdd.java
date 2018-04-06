package mk.mladen.avtobusi.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import mk.mladen.avtobusi.dto.CarrierDto;
import mk.mladen.avtobusi.service.CarrierService;

public class ModalPanelCarrierAdd extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private CarrierService carrierService;

    public ModalPanelCarrierAdd(String id, ModalWindow window) {
        super(id);
        final CarrierDto dto = new CarrierDto();
        Form<String> form = new Form<String>("addForm") {
            private static final long serialVersionUID = 1L;
			@Override
            protected void onSubmit() {
                //carrierService.addNewCarrier(dto);
            }
        };

        PropertyModel<String> nameModel = new PropertyModel<String>(dto, "name");
        PropertyModel<String> nameCyrillicModel = new PropertyModel<String>(dto, "nameCyrilic");
        PropertyModel<String> placeModel = new PropertyModel<String>(dto, "place");

        TextField<String> nameTxt = new TextField<String>("nameTxt", nameModel);
        TextField<String> nameCyrillicTxt = new TextField<String>("nameCyrillicTxt", nameCyrillicModel);
        TextField<String> placeTxt = new TextField<String>("placeTxt", placeModel);

        form.add(nameTxt);
        form.add(nameCyrillicTxt);
        form.add(placeTxt);

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
