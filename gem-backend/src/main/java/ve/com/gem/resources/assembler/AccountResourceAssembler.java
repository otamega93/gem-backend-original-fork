package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.AccountController;
import ve.com.gem.entities.Account;
import ve.com.gem.resources.AccountResource;

@Component
public class AccountResourceAssembler extends ResourceAssemblerSupport<Account, AccountResource>{

	public AccountResourceAssembler () {
		super(AccountController.class, AccountResource.class);
	}

	@Override
	public AccountResource toResource(Account account) {
		AccountResource accountResource = new AccountResource();
		accountResource.setUsername(account.getUsername());
		accountResource.setPassword(account.getPassword());
		accountResource.setAuthorities(account.getAuthorities());
		accountResource.setEmail(account.getEmail());
		accountResource.setLastPasswordReset(account.getLastPasswordReset());
		accountResource.setIds(account.getId());
	    accountResource.add(linkTo(AccountController.class).slash("").slash(account.getId()).withSelfRel());
	    accountResource.add(linkTo(AccountController.class).slash("").slash(account.getId()).withRel("account"));
	    return accountResource;
	}
}
