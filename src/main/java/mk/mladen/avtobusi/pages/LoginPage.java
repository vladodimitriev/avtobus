package mk.mladen.avtobusi.pages;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class LoginPage extends BasePage {

    private static final long serialVersionUID = 1L;

    public LoginPage(PageParameters parameters) {
        super(parameters);
        Model<String> model1 = new Model<String>();
        TextField<String> tf1 = new TextField<String>("username", model1);

        Model<String> model2 = new Model<String>();
        PasswordTextField ptf = new PasswordTextField("password", model2);
        Form form = new Form("loginForm") {
            @Override
            protected void onSubmit() {
                String username = model1.getObject();
                String password = model2.getObject();
                if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                    if(username.equals(password)) {
                        //setResponsePage(AdminPage.class, getParams("EN"));
                        setResponsePage(AdminHomePage.class, getParams("EN"));
                    }
                }
            }
        };
        form.add(tf1);
        form.add(ptf);

        add(form);
    }

    private PageParameters getParams(String language) {
        PageParameters params = new PageParameters();
        if(language != null) {
            params.add("lang", language);
        }

        return params;
    }

    @Override
    protected void setResponse(PageParameters params) {
        setResponsePage(LoginPage.class, params);
    }
}
