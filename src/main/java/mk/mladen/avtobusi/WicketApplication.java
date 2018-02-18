package mk.mladen.avtobusi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.apache.wicket.markup.html.pages.InternalErrorPage;
import org.apache.wicket.markup.html.pages.PageExpiredErrorPage;
import org.apache.wicket.session.ISessionStore;
import org.apache.wicket.settings.ExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mk.mladen.avtobusi.pages.AboutPage;
import mk.mladen.avtobusi.pages.AdminHomePage;
import mk.mladen.avtobusi.pages.ContactPage;
import mk.mladen.avtobusi.pages.LoginPage;
import mk.mladen.avtobusi.pages.ResultPage;
import mk.mladen.avtobusi.pages.SearchPage;
import mk.mladen.avtobusi.security.AuthenticatedSession;

public class WicketApplication extends AuthenticatedWebApplication {
	
	private static final Logger logger = LogManager.getLogger(WicketApplication.class);
	
	@Override
    public Class<SearchPage> getHomePage() {
        return SearchPage.class;
    }
	
	@Override
	protected void init() {
		super.init();
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
		getSessionListeners().add(new MySessionListener());
		//getExceptionSettings().setAjaxErrorHandlingStrategy(ExceptionSettings.AjaxErrorStrategy.INVOKE_FAILURE_HANDLER);
		getExceptionSettings().setUnexpectedExceptionDisplay(ExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
		
		mountPage("/#{lang}/#{departure}/#{destination}/#{date}", SearchPage.class);
		mountPage("/search/#{lang}/#{departure}/#{destination}/#{date}", ResultPage.class);
		mountPage("/login/#{lang}", LoginPage.class);
		mountPage("/admin/#{lang}", AdminHomePage.class);
		mountPage("/about/#{lang}", AboutPage.class);
		mountPage("/contact/#{lang}", ContactPage.class);
		
		getApplicationSettings().setAccessDeniedPage(AccessDeniedPage.class);
        getApplicationSettings().setInternalErrorPage(InternalErrorPage.class);
        getApplicationSettings().setPageExpiredErrorPage(PageExpiredErrorPage.class);
	}

	@Override
	public <T extends Page> MountedMapper mountPage(String path, Class<T> pageClass) {
		NoVersionMapper mapper = new NoVersionMapper(path, pageClass);
		mount(mapper);
		return mapper;
	}
	
	@Override
	public RuntimeConfigurationType getConfigurationType() {
		//return RuntimeConfigurationType.DEPLOYMENT;
		return RuntimeConfigurationType.DEVELOPMENT;
	}
	
	@Override
    protected Class<AuthenticatedSession> getWebSessionClass() {
        return AuthenticatedSession.class;
    }

    @Override
    public Class<LoginPage> getSignInPageClass() {
        return LoginPage.class;
    }
    
}

