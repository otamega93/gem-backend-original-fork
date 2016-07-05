package ve.com.gem.securities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import ve.com.gem.entities.Account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by informatica on 01/01/16.
 */
public class AccountUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6809181220864736243L;

	private Account account;
	
	private Date lastPasswordReset;
	private Long id;
	public AccountUserDetails() {
		super();
	}
	
	public AccountUserDetails(User user){
		user.getUsername();
		user.getPassword();
	}

	public AccountUserDetails(Account account, Date lastPasswordReset, Long id, String username, String password,
			String email, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired,
			Boolean enabled) {
		super();
		this.account = account;
		this.lastPasswordReset = lastPasswordReset;
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority authority = new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return account.getAuthorities();
			}
		};

		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
	}

	public void setPassword(String password) {
	}

}