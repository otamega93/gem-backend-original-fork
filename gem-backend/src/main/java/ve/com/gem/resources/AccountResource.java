package ve.com.gem.resources;

import java.sql.Date;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Relation(collectionRelation = "accounts")
public class AccountResource extends ResourceSupport {
	
    private String username;

    private String password;

    private String authorities;
    
    private String email;
    
    private Date lastPasswordReset;
    
    private Long ids;


	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
    
    public Long getIds() {
		return ids;
	}

	public void setIds(Long ids) {
		this.ids = ids;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}
	
}