//package mk.mladen.avtobusi;
//
//import org.apache.wicket.protocol.http.WebApplication;
//import org.apache.wicket.resource.loader.ClassStringResourceLoader;
//import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
//import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
//
//@ApplicationInitExtension
//public class AvtobusiConfig implements WicketApplicationInitConfiguration {
//	
//	@Override
//	public void init(WebApplication webApplication) {
//		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(webApplication.getServletContext());
//		webApplication.getComponentInstantiationListeners().add(new SpringComponentInjector(webApplication, ctx));
//		webApplication.getResourceSettings().getStringResourceLoaders().add(new ClassStringResourceLoader(AvtobusiConfig.class));
//	}
//	
//}