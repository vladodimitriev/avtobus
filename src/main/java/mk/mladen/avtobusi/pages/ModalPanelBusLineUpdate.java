package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.beans.UpdateBean;
import mk.mladen.avtobusi.service.BusLineService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ModalPanelBusLineUpdate extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private BusLineService busLineService;

    public ModalPanelBusLineUpdate(String id, UpdateBean bean, ModalWindow window) {
        super(id);

        PropertyModel idModel = new PropertyModel(bean, "id");
        PropertyModel departureModel = new PropertyModel(bean, "departurePlace");
        PropertyModel arrivalModel = new PropertyModel(bean, "arrivalPlace");
        PropertyModel departureTimeModel = new PropertyModel(bean, "departureTime");
        PropertyModel arrivalTimeModel = new PropertyModel(bean, "arrivalTime");

        PropertyModel operationDaysModel = new PropertyModel(bean, "operationDays");
        PropertyModel operationMonthsModel = new PropertyModel(bean, "operationMonths");
        PropertyModel operationPeriodModel = new PropertyModel(bean, "operationPeriod");
        PropertyModel<String> commentModel = new PropertyModel<String>(bean, "comment");

        PropertyModel priceModel = new PropertyModel(bean, "price");
        PropertyModel hasPriceModel = new PropertyModel(bean, "hasPrice");
        PropertyModel lineNumberModel = new PropertyModel(bean, "lineNumber");
        PropertyModel carrierModel = new PropertyModel(bean, "carrier");

        TextField idTxt = new TextField("id", idModel);
        TextField departurePlaceTxt = new TextField("departurePlace", departureModel);
        departurePlaceTxt.setEnabled(false);
        TextField arrivalPlaceTxt = new TextField("arrivalPlace", arrivalModel);
        arrivalPlaceTxt.setEnabled(false);
        TextField departureTimeTxt = new TextField("departureTime", departureTimeModel);
        TextField arrivalTimeTxt = new TextField("arrivalTime", arrivalTimeModel);
        TextField operationDaysTxt = new TextField("operationDays", operationDaysModel);
        TextField operationMonthsTxt = new TextField("operationMonths", operationMonthsModel);
        TextField operationPeriodTxt = new TextField("operationPeriod", operationPeriodModel);
        TextArea<String> commentTxt = new TextArea<String>("comment", new PropertyModel<String>(bean, "comment"));
        TextField priceTxt = new TextField("price", priceModel);
        TextField hasPriceTxt = new TextField("hasPrice", hasPriceModel);
        TextField lineNumberTxt = new TextField("lineNumber", lineNumberModel);
        TextArea<String> carrierTxt = new TextArea<String>("carrier", carrierModel);
        //carrierTxt.set

        Form<Void> form = new Form<Void>("updateForm"){
            @Override
            protected void onSubmit() {
                busLineService.updateBusLine(bean);
            }
        };

        //form.add(idTxt);
        form.add(departurePlaceTxt);
        form.add(arrivalPlaceTxt);
        form.add(departureTimeTxt);
        form.add(arrivalTimeTxt);

        form.add(operationDaysTxt);
        form.add(operationMonthsTxt);
        form.add(operationPeriodTxt);
        form.add(commentTxt);

        form.add(priceTxt);
        //form.add(hasPriceTxt);
        //form.add(lineNumberTxt);
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
                //System.out.println("save btn");
                //busLineService.updateBusLine(bean);
                window.close(target);
            }
        };

        form.add(cancelLink);
        form.add(saveBtn);
        add(form);
    }

}
