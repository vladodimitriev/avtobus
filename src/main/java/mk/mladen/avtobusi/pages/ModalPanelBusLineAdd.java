package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.beans.AddBean;
import mk.mladen.avtobusi.service.BusLineService;
import mk.mladen.avtobusi.service.CarrierService;
import mk.mladen.avtobusi.service.PlaceService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class ModalPanelBusLineAdd extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private BusLineService busLineService;

    @SpringBean
    private PlaceService placeService;

    @SpringBean
    private CarrierService carrierService;

    public ModalPanelBusLineAdd(String id, ModalWindow window) {
        super(id);

        List<String> carriers = carrierService.findAllCarrierNames();
        List<String> places = placeService.findAllPlacesNames();

        AddBean bean = new AddBean();

        PropertyModel<String> departureTimeModel = new PropertyModel<String>(bean, "departureTime");
        PropertyModel<String> arrivalTimeModel = new PropertyModel<String>(bean, "arrivalTime");
        PropertyModel<String> operationDaysModel = new PropertyModel<String>(bean, "operationDays");
        PropertyModel<String> operationMonthsModel = new PropertyModel<String>(bean, "operationMonths");
        PropertyModel<String> operationPeriodModel = new PropertyModel<String>(bean, "operationPeriod");
        PropertyModel<String> commentModel = new PropertyModel<String>(bean, "comment");
        PropertyModel<String> priceModel = new PropertyModel<String>(bean, "price");

        TextField<String> departureTimeTxt = new TextField<String>("departureTime", departureTimeModel);
        TextField<String> arrivalTimeTxt = new TextField<String>("arrivalTime", arrivalTimeModel);
        TextField<String> operationDaysTxt = new TextField<String>("operationDays", operationDaysModel);
        TextField<String> operationMonthsTxt = new TextField<String>("operationMonths", operationMonthsModel);
        TextField<String> operationPeriodTxt = new TextField<String>("operationPeriod", operationPeriodModel);
        TextField<String> priceTxt = new TextField<String>("price", priceModel);
        TextArea<String> commentTxt = new TextArea<String>("comment", commentModel);

        DropDownChoice<String> departureList = new DropDownChoice<String>("departures", new PropertyModel<String>(bean, "departurePlace"), places);
        DropDownChoice<String> destinationList = new DropDownChoice<String>("destinations", new PropertyModel<String>(bean, "arrivalPlace"), places);
        DropDownChoice<String> carrierList = new DropDownChoice<String>("carriers", new PropertyModel<String>(bean, "carrier"), carriers);

        Form<String> form = new Form<String>("addForm") {
            private static final long serialVersionUID = 1L;
			@Override
            protected void onSubmit() {
                busLineService.addNewBusLine(bean);
            }
        };

        form.add(departureList);
        form.add(destinationList);
        form.add(departureTimeTxt);
        form.add(arrivalTimeTxt);

        form.add(operationDaysTxt);
        form.add(operationMonthsTxt);
        form.add(operationPeriodTxt);
        form.add(commentTxt);

        form.add(priceTxt);
        form.add(carrierList);

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
