package mk.mladen.avtobusi;

import org.apache.wicket.core.request.handler.BookmarkableListenerRequestHandler;
import org.apache.wicket.core.request.handler.ListenerRequestHandler;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.info.PageComponentInfo;
import org.apache.wicket.request.mapper.parameter.PageParametersEncoder;

public class NoVersionMapper extends MountedMapper {
	
	public NoVersionMapper(final Class<? extends IRequestablePage> pageClass) {
		this("/", pageClass);
	}

	public NoVersionMapper(String mountPath, final Class<? extends IRequestablePage> pageClass) {
		super(mountPath, pageClass, new PageParametersEncoder());
	}

	@Override
	protected void encodePageComponentInfo(Url url, PageComponentInfo info) {
		//Does nothing
	}
	
	@Override
	public Url mapHandler(IRequestHandler requestHandler) {
		if (requestHandler instanceof ListenerRequestHandler 
			|| requestHandler instanceof BookmarkableListenerRequestHandler) {
			return null;
		} else {
			return super.mapHandler(requestHandler);
		}
	}

}
