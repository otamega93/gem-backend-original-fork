package ve.com.gem.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by informatica on 26/02/16.
 */

@ComponentScan
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private EntryPointUnauthorizedHandler authenticationEntryPoint;
    @Autowired
    private AuthFailure authenticationFailureHandler;
    @Autowired
    private AuthSuccess authenticationSuccessHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

    @Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
//    public class WebConfig extends WebMvcConfigurerAdapter {
//        @Override
//        public void configureContentNegotiation(ContentNegotiationConfigurer c) {
//            c.defaultContentType(MediaTypes.HAL_JSON);
//        }
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	/*
    	 * Production ready configuration. Uncomment when in production:
    	 */
    	
//    	http    
//            .addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class)
//            .authorizeRequests()
//            .antMatchers("/login").permitAll().anyRequest().authenticated()
//            .antMatchers("/logout").permitAll().anyRequest().authenticated();
//
//        http
//            .formLogin().loginPage("/login").permitAll().usernameParameter("username").passwordParameter("password")
//            .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
//            .and()
//            .rememberMe().userDetailsService(userDetailService).rememberMeParameter("remember-me").tokenValiditySeconds(20000)
//            .and()
//            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//            .and()
//            .sessionManagement().maximumSessions(3);
//        
//        http
//        	.logout().logoutSuccessHandler(logoutSuccessHandler).logoutUrl("/logout");
        
        //*/
        
        // In Development:
        
        http
        	.csrf().disable()
        	.authorizeRequests().anyRequest().permitAll()
        	.and()
        	.formLogin().loginPage("/login").permitAll().successHandler(authenticationSuccessHandler)
        	.failureHandler(authenticationFailureHandler)
        	.and()
        	.rememberMe().rememberMeParameter("remember-me").tokenValiditySeconds(2000)
        	.and()
        	.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
        	.and()
            .sessionManagement().maximumSessions(1);
    }
    
}