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

import java.util.Arrays;
import java.util.List;

public class ModalPanelBusLineAdd extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private BusLineService busLineService;

    @SpringBean
    private PlaceService placeService;

    @SpringBean
    private CarrierService carrierService;

    private static final List<String> CARRIERS = Arrays.asList(new String[] {"Rule Turs", "Sam-Vel", "Galeb" });

    public ModalPanelBusLineAdd(String id, ModalWindow window) {
        super(id);

        List<String> carriers = carrierService.findAllCarrierNames();
        List<String> places = placeService.findAllPlacesNames();

        AddBean bean = new AddBean();
//        bean.setDeparturePlace(searchBean.getDeparturePlace());
//        bean.setArrivalPlace(searchBean.getDestinationPlace());

        PropertyModel idModel = new PropertyModel(bean, "id");
        PropertyModel departureModel = new PropertyModel(bean, "departurePlace");
        PropertyModel arrivalModel = new PropertyModel(bean, "arrivalPlace");
        PropertyModel departureTimeModel = new PropertyModel(bean, "departureTime");
        PropertyModel arrivalTimeModel = new PropertyModel(bean, "arrivalTime");

        PropertyModel operationDaysModel = new PropertyModel(bean, "operationDays");
        PropertyModel operationMonthsModel = new PropertyModel(bean, "operationMonths");
        PropertyModel operationPeriodModel = new PropertyModel(bean, "operationPeriod");
        PropertyModel commentModel = new PropertyModel(bean, "comment");

        PropertyModel priceModel = new PropertyModel(bean, "price");
        PropertyModel hasPriceModel = new PropertyModel(bean, "hasPrice");
        PropertyModel lineNumberModel = new PropertyModel(bean, "lineNumber");
        PropertyModel carrierModel = new PropertyModel(bean, "carrier");

        TextField idTxt = new TextField("id", idModel);
        //TextField departurePlaceTxt = new TextField("departurePlace", departureModel);
        //departurePlaceTxt.setEnabled(false);
        //TextField arrivalPlaceTxt = new TextField("arrivalPlace", arrivalModel);
        //arrivalPlaceTxt.setEnabled(false);
        TextField departureTimeTxt = new TextField("departureTime", departureTimeModel);
        TextField arrivalTimeTxt = new TextField("arrivalTime", arrivalTimeModel);
        TextField operationDaysTxt = new TextField("operationDays", operationDaysModel);
        TextField operationMonthsTxt = new TextField("operationMonths", operationMonthsModel);
        TextField operationPeriodTxt = new TextField("operationPeriod", operationPeriodModel);
        TextArea<String> commentTxt = new TextArea<String>("comment", commentModel);
        TextField priceTxt = new TextField("price", priceModel);
        TextField hasPriceTxt = new TextField("hasPrice", hasPriceModel);
        TextField lineNumberTxt = new TextField("lineNumber", lineNumberModel);
        TextArea<String> carrierTxt = new TextArea<String>("carrier", carrierModel);

        DropDownChoice<String> departureList = new DropDownChoice<String>("departures", new PropertyModel<String>(bean, "departurePlace"), places);
        DropDownChoice<String> destinationList = new DropDownChoice<String>("destinations", new PropertyModel<String>(bean, "arrivalPlace"), places);
        DropDownChoice<String> carrierList = new DropDownChoice<String>("carriers", new PropertyModel<String>(bean, "carrier"), carriers);

        Form form = new Form("addForm") {
            @Override
            protected void onSubmit() {
                System.out.println("Carrier: " + bean.getCarrier());
                System.out.println("Departure: " + bean.getDeparturePlace());
                System.out.println("Destination: " + bean.getArrivalPlace());
                busLineService.addNewBusLine(bean);
            }
        };

        //form.add(idTxt);
        form.add(departureList);
        form.add(destinationList);
        form.add(departureTimeTxt);
        form.add(arrivalTimeTxt);

        form.add(operationDaysTxt);
        form.add(operationMonthsTxt);
        form.add(operationPeriodTxt);
        form.add(commentTxt);

        form.add(priceTxt);
        //form.add(hasPriceTxt);
        //form.add(lineNumberTxt);
        form.add(carrierList);

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
