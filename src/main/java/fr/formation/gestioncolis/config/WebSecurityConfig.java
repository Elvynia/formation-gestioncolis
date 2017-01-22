package fr.formation.gestioncolis.config;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@ComponentScans({ @ComponentScan("fr.formation.gestioncolis.service") })
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication().rolePrefix("ROLE_")
				.dataSource(this.dataSource())
				.passwordEncoder(this.passwordEncoder())
				.usersByUsernameQuery(this.getUsers())
				.authoritiesByUsernameQuery(this.getAuthoritiesQuery());
	}

	@Bean
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		final DataSource dataSource = dsLookup
				.getDataSource("jdbc/gestioncolis");
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(this.dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(
				HibernatePersistenceProvider.class);
		entityManagerFactoryBean
				.setPackagesToScan("fr.formation.gestioncolis.entity");
		return entityManagerFactoryBean;
	}

	private String getAuthoritiesQuery() {
		return "SELECT username, upper(role.name) as authority"
				+ " FROM user, role WHERE user.username = ? AND user.roleId = role.id";
	}

	private String getUsers() {
		return "SELECT USERNAME as username, PASSWORD as password,"
				+ " true FROM user WHERE username = ?";
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(
				this.entityManagerFactory().getObject());
		return transactionManager;
	}

}
