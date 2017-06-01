//package mk.mladen.avtobusi;
//
//import javax.servlet.annotation.WebListener;
//
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//
//@WebListener
//public class SpringContextLoaderListener extends ContextLoaderListener {
//
//    public SpringContextLoaderListener() {
//        super(getWebApplicationContext());
//    }
//
//    private static WebApplicationContext getWebApplicationContext() {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(AppConfiguration.class);
//        ctx.refresh();
//        return ctx;
//    }
//
//}
