package fr.formation.gestioncolis.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
		super(WebSecurityConfig.class);
	}
}
