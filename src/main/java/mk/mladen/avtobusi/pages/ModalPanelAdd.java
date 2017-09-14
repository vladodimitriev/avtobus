package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.beans.AddBean;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class ModalPanelAdd extends Panel {

    private AddBean bean;

    public ModalPanelAdd(String id) {
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



        add(form);
    }
}
