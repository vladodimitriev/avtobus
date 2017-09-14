package mk.mladen.avtobusi.pages;

import mk.mladen.avtobusi.beans.AddBean;
import mk.mladen.avtobusi.beans.DeleteBean;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class ModalPanelDelete extends Panel {

    private DeleteBean bean;

    public ModalPanelDelete(String id, DeleteBean dbean) {
        super(id);

        if(dbean == null) {
            bean = new DeleteBean();
        } else {
            bean = dbean;
        }

        PropertyModel idModel = new PropertyModel(bean, "id");
        TextField idTxt = new TextField("id", idModel);

        Form form = new Form("deleteForm") {
            @Override
            protected void onSubmit() {
            }
        };

        form.add(idTxt);
        add(form);
    }
}
