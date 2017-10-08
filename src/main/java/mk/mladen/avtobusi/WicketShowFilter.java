package mk.mladen.avtobusi;

import org.apache.wicket.protocol.http.WicketFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(value = "/*",
initParams = {
        @WebInitParam(name = "applicationClassName", value = "mk.mladen.avtobusi.WicketApplication"),
        @WebInitParam(name = "filterMappingUrlPattern", value = "/*")
})
public class WicketShowFilter extends WicketFilter {
}
