package mk.mladen.avtobusi;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mk.mladen.avtobusi.pages.SearchPage;

public class WicketApplication extends WebApplication {
	
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
	}

	@Override
	public RuntimeConfigurationType getConfigurationType() {
		//return RuntimeConfigurationType.DEPLOYMENT;
		return RuntimeConfigurationType.DEVELOPMENT;
	}
	
}

