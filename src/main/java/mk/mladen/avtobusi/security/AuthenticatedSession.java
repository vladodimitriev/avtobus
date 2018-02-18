package mk.mladen.avtobusi.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import mk.mladen.avtobusi.pages.BasePage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticatedSession extends AuthenticatedWebSession {
	
	private static Logger logger = LogManager.getLogger(AuthenticatedSession.class);

    private static final long serialVersionUID = 1L;
    
	@SpringBean
    private AuthenticationManager authenticationManager;
	
	private HttpSession httpSession;
	
	private String username;

    public AuthenticatedSession(Request request) {
        super(request);
        this.httpSession = ((HttpServletRequest) request.getContainerRequest()).getSession();
        this.httpSession.setMaxInactiveInterval(20);
        Injector.get().inject(this);
    }

    @Override
    protected boolean authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        
        this.username = username;
        if (authentication.isAuthenticated()) {
        	SecurityContextHolder.getContext().setAuthentication(authentication);
            httpSession.setAttribute(
            		HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void onInvalidate() {
    	logger.info("on invalidate session " + httpSession.getId());
    	logger.info("on invalidate session username = " + username);
    }

    @Override
    public Roles getRoles() {
        Roles resultRoles = new Roles();
        if(username != null && 
        		(username.equals("admin")
    				|| username.equals("vlado")
    				|| username.equals("alexandra")
    				|| username.equals("ivan"))){
            resultRoles.add(Roles.ADMIN);
            resultRoles.add("admin");
        } else if(username != null && username.equals("superadmin")){
            resultRoles.add("superadmin");
            resultRoles.add("super");
        } else if(username != null && username.equals("super")){
            resultRoles.add("superadmin");
            resultRoles.add("super");
        }
        return resultRoles;
    }
    
    @Override
    public void signOut() {
        super.signOut();
        username = null;
    }

	public String getUsername() {
		return username;
	}

	@Override
	public void invalidateNow() {
		super.invalidateNow();
		logger.debug("invalidateNow()");
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		logger.debug("invalidate()");
	}
	
	@Override
	public void detach() {
		super.detach();
		//logger.debug("detach()");
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		//logger.debug("finalize()");
	}
	
	@Override
	public void internalDetach() {
		super.internalDetach();
		//logger.debug("internalDetach()");
	}
}
