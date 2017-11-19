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

        PropertyModel<String> departureModel = new PropertyModel<String>(bean, "departurePlace");
        PropertyModel<String> arrivalModel = new PropertyModel<String>(bean, "arrivalPlace");
        PropertyModel<String> departureTimeModel = new PropertyModel<String>(bean, "departureTime");
        PropertyModel<String> arrivalTimeModel = new PropertyModel<String>(bean, "arrivalTime");

        PropertyModel<String> operationDaysModel = new PropertyModel<String>(bean, "operationDays");
        PropertyModel<String> operationMonthsModel = new PropertyModel<String>(bean, "operationMonths");
        PropertyModel<String> operationPeriodModel = new PropertyModel<String>(bean, "operationPeriod");
        PropertyModel<String> commentModel = new PropertyModel<String>(bean, "comment");
        PropertyModel<Integer> redenBrojModel = new PropertyModel<Integer>(bean, "redenBroj");

        PropertyModel<String> priceModel = new PropertyModel<String>(bean, "price");
        PropertyModel<String> priceReturnModel = new PropertyModel<String>(bean, "priceReturn");
        PropertyModel<Integer> lineNumberModel = new PropertyModel<Integer>(bean, "lineNumber");
        PropertyModel<String> lineNameModel = new PropertyModel<String>(bean, "lineName");
        PropertyModel<String> carrierModel = new PropertyModel<String>(bean, "carrier");
        
        PropertyModel<String> smallPlacesModel = new PropertyModel<String>(bean, "smallPlaces");
        PropertyModel<String> smallPlacesLatinModel = new PropertyModel<String>(bean, "smallPlacesLatin");

        TextField<String> departurePlaceTxt = new TextField<String>("departurePlace", departureModel);
        departurePlaceTxt.setEnabled(false);
        TextField<String> arrivalPlaceTxt = new TextField<String>("arrivalPlace", arrivalModel);
        arrivalPlaceTxt.setEnabled(false);
        TextField<String> departureTimeTxt = new TextField<String>("departureTime", departureTimeModel);
        TextField<String> arrivalTimeTxt = new TextField<String>("arrivalTime", arrivalTimeModel);
        TextField<String> operationDaysTxt = new TextField<String>("operationDays", operationDaysModel);
        TextField<String> operationMonthsTxt = new TextField<String>("operationMonths", operationMonthsModel);
        TextField<String> operationPeriodTxt = new TextField<String>("operationPeriod", operationPeriodModel);
        TextArea<String> commentTxt = new TextArea<String>("comment", commentModel);
        TextField<String> priceTxt = new TextField<String>("price", priceModel);
        TextField<Integer> redenBrojTxt = new TextField<Integer>("redenBroj", redenBrojModel);
        TextField<Integer> lineNumberTxt = new TextField<Integer>("lineNumber", lineNumberModel);
        TextField<String> priceReturnTxt = new TextField<String>("priceReturn", priceReturnModel);
        TextField<String> lineNameTxt = new TextField<String>("lineName", lineNameModel);
        TextArea<String> carrierTxt = new TextArea<String>("carrier", carrierModel);
        TextArea<String> smallPlacesTxt = new TextArea<String>("smallPlaces", smallPlacesModel);
        TextArea<String> smallPlacesLatinTxt = new TextArea<String>("smallPlacesLatin", smallPlacesLatinModel);
        //carrierTxt.set

        Form<Void> form = new Form<Void>("updateForm"){
            private static final long serialVersionUID = 1L;

			@Override
            protected void onSubmit() {
            	busLineService.updateBusLine(bean);
            }
        };

        form.add(departurePlaceTxt);
        form.add(arrivalPlaceTxt);
        form.add(departureTimeTxt);
        form.add(arrivalTimeTxt);
        form.add(redenBrojTxt);	
        form.add(operationDaysTxt);
        form.add(operationMonthsTxt);
        form.add(operationPeriodTxt);
        form.add(commentTxt);
        form.add(priceTxt);
        form.add(priceReturnTxt);
        form.add(lineNumberTxt);
        form.add(lineNameTxt);
        form.add(carrierTxt);
        form.add(smallPlacesTxt);
        form.add(smallPlacesLatinTxt);

        AjaxLink<String> cancelLink = new AjaxLink<String>("cancelLink") {
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
