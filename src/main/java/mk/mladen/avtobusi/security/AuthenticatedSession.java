package mk.mladen.avtobusi.security;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticatedSession extends AuthenticatedWebSession {

    private static final long serialVersionUID = 1L;
    
	@SpringBean
    private AuthenticationManager authenticationManager;
	
	private HttpSession httpSession;
	
	private String username;

    public AuthenticatedSession(Request request) {
        super(request);
        this.httpSession = ((HttpServletRequest) request.getContainerRequest()).getSession();
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

}
