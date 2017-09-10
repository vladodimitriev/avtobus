package mk.mladen.avtobusi.pages;

import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;

public class ModalAddPage extends WebPage {

    public ModalAddPage(final PageReference modalWindowPage, final ModalWindow window) {

        add(new AjaxLink<Void>("closeOK")
        {
            @Override
            public void onClick(AjaxRequestTarget target)
            {
                if (modalWindowPage != null) {
                    //((AdminPage) modalWindowPage.getPage()).setResult("Modal window 1 - close link OK");
                }
                window.close(target);
            }
        });

        add(new AjaxLink<Void>("closeCancel")
        {
            @Override
            public void onClick(AjaxRequestTarget target)
            {
                if (modalWindowPage != null) {
                    //((AdminPage) modalWindowPage.getPage()).setResult("Modal window 1 - close link Cancel");
                }
                window.close(target);
            }
        });

    }
}
