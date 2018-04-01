package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.beans.DeleteBean;
import mk.mladen.avtobusi.service.BusLineService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ModalPanelBusLineDelete extends Panel {

    private static final long serialVersionUID = 1L;
    
    private static Logger logger = LogManager.getLogger(ModalPanelBusLineDelete.class);

    @SpringBean
    private BusLineService busLineService;

    public ModalPanelBusLineDelete(String id, final DeleteBean bean, ModalWindow window) {
        super(id);

        PropertyModel<String> departureModel = new PropertyModel<String>(bean, "departurePlace");
        PropertyModel<String> arrivalModel = new PropertyModel<String>(bean, "arrivalPlace");
        PropertyModel<String> departureTimeModel = new PropertyModel<String>(bean, "departureTime");
        PropertyModel<String> arrivalTimeModel = new PropertyModel<String>(bean, "arrivalTime");
        PropertyModel<String> carrierModel = new PropertyModel<String>(bean, "carrier");

        Label departurePlaceLbl = new Label("departurePlace", departureModel);
        Label destinationPlaceLbl = new Label("arrivalPlace", arrivalModel);
        Label departureTimeLbl = new Label("departureTime", departureTimeModel);
        Label arrivalTimeLbl = new Label("arrivalTime", arrivalTimeModel);
        Label carrierLbl = new Label("carrier", carrierModel);

        AjaxLink<Void> deleteYesLink = new AjaxLink<Void>("deleteYesLink") {
            private static final long serialVersionUID = 1L;
			@Override
            public void onClick(AjaxRequestTarget target) {
				if(bean != null) {
					busLineService.deleteBusLine(bean.getId());
				} else {
					logger.debug("delete bean == null");
				}
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

        add(departurePlaceLbl);
        add(destinationPlaceLbl);
        add(departureTimeLbl);
        add(arrivalTimeLbl);
        add(carrierLbl);
        add(deleteYesLink);
        add(deleteNoLink);
    }
}
