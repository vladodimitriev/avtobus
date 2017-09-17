package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.beans.AddBean;
import mk.mladen.avtobusi.service.BusLineService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ModalPanelAdd extends Panel {

    @SpringBean
    private BusLineService busLineService;

    private AddBean bean;

    public ModalPanelAdd(String id, ModalWindow window) {
        super(id);
        AddBean bean = new AddBean();

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
        TextField departurePlaceTxt = new TextField("departurePlace", departureModel);
        TextField arrivalPlaceTxt = new TextField("arrivalPlace", arrivalModel);
        TextField departureTimeTxt = new TextField("departureTime", departureTimeModel);
        TextField arrivalTimeTxt = new TextField("arrivalTime", arrivalTimeModel);
        TextField operationDaysTxt = new TextField("operationDays", operationDaysModel);
        TextField operationMonthsTxt = new TextField("operationMonths", operationMonthsModel);
        TextField operationPeriodTxt = new TextField("operationPeriod", operationPeriodModel);
        TextField commentTxt = new TextField("comment", commentModel);
        TextField priceTxt = new TextField("price", priceModel);
        TextField hasPriceTxt = new TextField("hasPrice", hasPriceModel);
        TextField lineNumberTxt = new TextField("lineNumber", lineNumberModel);
        TextField carrierTxt = new TextField("carrier", carrierModel);

        Form form = new Form("addForm") {
            @Override
            protected void onSubmit() {
                busLineService.addNewBusLine(bean);
            }
        };

        form.add(idTxt);
        form.add(departurePlaceTxt);
        form.add(arrivalPlaceTxt);
        form.add(departureTimeTxt);
        form.add(arrivalTimeTxt);

        form.add(operationDaysTxt);
        form.add(operationMonthsTxt);
        form.add(operationPeriodTxt);
        form.add(commentTxt);

        form.add(priceTxt);
        form.add(hasPriceTxt);
        form.add(lineNumberTxt);
        form.add(carrierTxt);

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
