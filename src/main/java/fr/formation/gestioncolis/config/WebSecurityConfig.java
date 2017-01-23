package fr.formation.gestioncolis.config;

<<<<<<< HEAD
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth)throws Exception {
		auth.jdbcAuthentication().rolePrefix("ROLE_")
		.dataSource(this.dataSource())
		.usersByUsernameQuery(this.usersByUsername())
		.authoritiesByUsernameQuery(this.authoritiesByUsername());
	}
	
	private String authoritiesByUsername() {
		return "SELECT username, upper(role.name) as authority FROM user, role WHERE user.username = ? AND user.roleId = role.id";
	}

	private String usersByUsername() {	
		return "SELECT USERNAME as username, PASSWORD as password, true FROM user WHERE username";
	}

	@Bean
	public DataSource dataSource() throws DataSourceLookupFailureException {
		
		JndiDataSourceLookup nslookup = new JndiDataSourceLookup();
		nslookup.setResourceRef(true);
		return nslookup.getDataSource("jdbc/gestioncolis");
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entiryManagerFactory() {
		final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(this.dataSource());
		factory.setPersistenceProvider(new HibernatePersistenceProvider());
		factory.setPackagesToScan(("fr.formation.gestioncolis.entity"));
		
		return factory;
		
	}
	
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transaction = new JpaTransactionManager();
		transaction.setEntityManagerFactory(this.entiryManagerFactory().getObject());
		
		return transaction;
		
	}
=======
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		final InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("user").roles("USER").build());
		return manager;
	}

>>>>>>> 399f582830eef3fcb45ef246facb0e83140f2453
}
