package mk.mladen.avtobusi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import mk.mladen.avtobusi.security.AuthenticatedSession;

public class LoginPage extends BasePage {

    private static final long serialVersionUID = 1L;
    
    private static Logger logger = LogManager.getLogger(LoginPage.class);
    
    @SpringBean
    private PasswordEncoder passwordEncoder; 
    
    
    public LoginPage(PageParameters parameters) {
    	super(parameters);
        add(new LoginForm("loginForm", parameters));
    }

    private class LoginForm extends Form<Void> {
        private static final long serialVersionUID = 1L;

        private Model<String> modelUsername;
        private Model<String> modelPassword;
        private PageParameters pageParameters;

        public LoginForm(String id, PageParameters parameters) {
            super(id);
            
            pageParameters = parameters;
            modelUsername = new Model<String>();
            modelPassword = new Model<String>();
            
            TextField<String> tfUsername = new TextField<String>("username", modelUsername);
            PasswordTextField tfPassword = new PasswordTextField("password", modelPassword);
            add(tfUsername);
            add(tfPassword);
        }

        @Override
        public void onSubmit() {
            AuthenticatedSession session = (AuthenticatedSession) AuthenticatedSession.get();
            try {
            	String username = modelUsername.getObject();
            	String password = modelPassword.getObject();
            	String hashedPassword = passwordEncoder.encode(password);
            	boolean matches = passwordEncoder.matches(password, hashedPassword);
            	if(!matches) {
            		logger.debug("hashed and plain passwords do not matches");
            		return;
            	}
            	
                boolean signin = session.signIn(username, password);
                if(!signin) {
                	logger.debug("bad credentials");
                	return;
                }
                
                boolean isAdmin = session.getRoles().hasRole(Roles.ADMIN);
				if (!isAdmin) {
					logger.debug("user does not have admin role");
					return;
                } 
				
				logger.info("user [{}], tries to log into the application", username);
				setResponsePage(AdminHomePage.class, pageParameters);
            } catch (AuthenticationException e) {
                logger.error(e.getMessage());
            }
        }
    }
    
    @Override
    protected void setResponse(PageParameters params) {
        setResponsePage(LoginPage.class, params);
    }

}
