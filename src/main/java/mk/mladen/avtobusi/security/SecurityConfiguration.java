package mk.mladen.avtobusi.security;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	public SecurityConfiguration() {
		super(false);
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
			.passwordEncoder(encoder())
				.withUser("admin")				
					.password("$2a$10$eX6rvg03FdMRmzd1qP1s8egEuno.zKxzlYQZYuiBbwtzw2v4oM6h2")
					.roles("admin", Roles.ADMIN)
				.and()
					.withUser("vlado")				
					.password("$2a$10$CE./mOu5VgqQiUcTNzcv..h8hi6fb78g7VS/Un3lhMN6cDOMrA5ma")
					.roles("admin", Roles.ADMIN)
				.and()
					.withUser("alexandra")				
					.password("$2a$10$6M2DTd/0eWAws4/I1pElAOoNbS0MCAmJrE15Z/7ONVziPd24daXyu")
					.roles("admin", Roles.ADMIN)
				.and()
				.withUser("user")
					.password("$2a$10$MPiRyHyeunlJt9.E5HCI7eLm0kTDixTOpSIsmjcK1.pAZfFiqMyJK")
					.roles("user", Roles.USER)
				.and()
				.withUser("super")
					.password("$2a$10$ayi8KdqSXu/7mQ3.ffN8bODGTcETJwTXIhHUej5OhLkYmCXfIqWvi")
					.roles("super")
				.and()
				.withUser("superadmin")
					.password("$2a$10$4ww1EMkjwJf8yydtxUBYDOMbN6rvkN41RS9lwSxdWPFgueXyvrP3O")
					.roles("superadmin");
	}
	
	@Bean(name = "authenticationManger")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
	        .antMatchers("/")
	            .permitAll()
	        .antMatchers("/admin")
	            .authenticated().and()
	            .formLogin()
	                .loginPage("/login");
		
		http.logout().logoutSuccessUrl("/");
	}

}
