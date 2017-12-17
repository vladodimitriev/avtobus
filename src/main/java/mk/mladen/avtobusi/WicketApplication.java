package mk.mladen.avtobusi;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.apache.wicket.markup.html.pages.InternalErrorPage;
import org.apache.wicket.markup.html.pages.PageExpiredErrorPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mk.mladen.avtobusi.pages.AdminHomePage;
import mk.mladen.avtobusi.pages.LoginPage;
import mk.mladen.avtobusi.pages.SearchPage;
import mk.mladen.avtobusi.security.AuthenticatedSession;

public class WicketApplication extends AuthenticatedWebApplication {
	
	@Override
    public Class<? extends WebPage> getHomePage() {
        return SearchPage.class;
    }
	
	@Override
	protected void init() {
		super.init();
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
		ctx.register(AppConfiguration.class);
		//mountPage("login", LoginPage.class);
		mountPage("admin", AdminHomePage.class);
		
		getApplicationSettings().setAccessDeniedPage(AccessDeniedPage.class);
        getApplicationSettings().setInternalErrorPage(InternalErrorPage.class);
        getApplicationSettings().setPageExpiredErrorPage(PageExpiredErrorPage.class);
	}

	@Override
	public RuntimeConfigurationType getConfigurationType() {
		//return RuntimeConfigurationType.DEPLOYMENT;
		return RuntimeConfigurationType.DEVELOPMENT;
	}
	
	@Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return AuthenticatedSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }
}

