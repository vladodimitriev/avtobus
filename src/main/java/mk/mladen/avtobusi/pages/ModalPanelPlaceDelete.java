package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.dto.PlaceDto;
import mk.mladen.avtobusi.service.PlaceService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ModalPanelPlaceDelete extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private PlaceService placeService;

    public ModalPanelPlaceDelete(String id, PlaceDto dto, ModalWindow window) {
        super(id);
        PropertyModel<String> nameModel = new PropertyModel<String>(dto, "name");
        Label nameLbl = new Label("nameLbl", nameModel);

        AjaxLink<Void> deleteYesLink = new AjaxLink<Void>("deleteYesLink") {
            private static final long serialVersionUID = 1L;
			@Override
            public void onClick(AjaxRequestTarget target) {
                placeService.deletePlace(dto.getId());
                window.close(target);
            }
        };

        AjaxLink<Void> deleteNoLink = new AjaxLink<Void>("deleteNoLink") {
            private static final long serialVersionUID = 1L;
			@Override
            public void onClick(AjaxRequestTarget target) {
                window.close(target);
            }
        };

        add(nameLbl);
        add(deleteYesLink);
        add(deleteNoLink);
    }
}
