package mk.mladen.avtobusi;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mk.mladen.avtobusi.pages.SearchPage;

//@SpringBootApplication
public class WicketApplication extends WebApplication {
	
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(WicketApplication.class);
	
	/*
	public static void main(String[] args) {
        SpringApplication.run(WicketApplication.class, args);
    }
    */
	
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
	
}

