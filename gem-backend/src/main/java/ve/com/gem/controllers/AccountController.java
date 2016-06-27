package ve.com.gem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.Account;
import ve.com.gem.resources.AccountResource;
import ve.com.gem.services.IAccountService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

	    @Autowired
	    private IAccountService accountService;
	    
	    @RequestMapping(value = "/", method = RequestMethod.POST)
	    public ResponseEntity<AccountResource> saveAccount(@RequestBody Account account) {
	    	
	        if (null != account && accountService.findByUsername(account.getUsername()) == null) {
	    		accountService.save(account);
	    		AccountResource accountResource = new AccountResource();
				accountResource.setUsername(account.getUsername());
				accountResource.setPassword(account.getPassword());
				accountResource.setAuthorities(account.getAuthorities());
			    Link link = linkTo(AccountController.class).slash("/findById").slash(account.getId()).withSelfRel();
			    accountResource.add(link);
			      	
			    return new ResponseEntity<AccountResource>(accountResource, HttpStatus.CREATED);
	    	}
	    	
	    	else if (accountService.findByUsername(account.getUsername()) != null){	
	    		
	    		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    	}
	        
	    	else {
	    		return new ResponseEntity<AccountResource>(HttpStatus.BAD_REQUEST);
	    	}
	    }
	    
	    @RequestMapping(value = "/", method=RequestMethod.GET)
	    public ResponseEntity<List<AccountResource>> loadAll() {
			List<Account> accounts = accountService.findAll();
			List<AccountResource> accountListResource = new ArrayList<AccountResource>(accounts.size());
			for(Account account : accounts) {
				AccountResource accountResource = new AccountResource();
				accountResource.setUsername(account.getUsername());
				accountResource.setPassword(account.getPassword());
				accountResource.setAuthorities(account.getAuthorities());
			      	Link link = linkTo(AccountController.class).slash("/findById").slash(account.getId()).withSelfRel();
			      	accountResource.add(link);
			      	accountListResource.add(accountResource);
			    }
			
			return new ResponseEntity<List<AccountResource>>(accountListResource, HttpStatus.OK);

		}
	    
	    @RequestMapping(value = "/findByUsernameLike/{username}", method=RequestMethod.GET)
		public ResponseEntity<List<AccountResource>> findByUsernameLike(@PathVariable String username){
	    	
	    	if (null != username) {
	    		List<Account> accounts = accountService.findByUsernameLike(username);
	    		List<AccountResource> accountListResource = new ArrayList<AccountResource>(accounts.size());
				for(Account account : accounts) {
					AccountResource accountResource = new AccountResource();
					accountResource.setUsername(account.getUsername());
					accountResource.setPassword(account.getPassword());
					accountResource.setAuthorities(account.getAuthorities());
				    Link link = linkTo(AccountController.class).slash("/findById").slash(account.getId()).withSelfRel();
				   	accountResource.add(link);
				   	accountListResource.add(accountResource);
				}
				
	    		return new ResponseEntity<List<AccountResource>>(accountListResource, HttpStatus.OK);
	    	}
	    	
	    	else {
	    		
	    		return new ResponseEntity<List<AccountResource>>(HttpStatus.NOT_FOUND);
	    	}
		}
	    
	    @RequestMapping(value = "/findByUsername/{username}", method=RequestMethod.GET)
		public ResponseEntity<AccountResource> findByUsername(@PathVariable String username){
	    	
	    	if (null != username) {
	    		Account account = accountService.findByUsername(username);
	    			if (null != account) {
	    				AccountResource accountResource = new AccountResource();
	    				accountResource.setUsername(account.getUsername());
	    				accountResource.setPassword(account.getPassword());
	    				accountResource.setAuthorities(account.getAuthorities());
	    			      	Link link = linkTo(AccountController.class).slash("/findById").slash(account.getId()).withSelfRel();
	    			      	accountResource.add(link);
	    				return new ResponseEntity<AccountResource>(accountResource, HttpStatus.OK);
	    			}
	    			else {
	    				return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
	    			}
	    	}
	    	else {
	    		return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
	    	}
		}
	    
	    @RequestMapping(value = "/findById/{id}", method=RequestMethod.GET)
		public ResponseEntity<AccountResource> findById(@PathVariable Long id) {
	    	
	    	if (null != id) {
	    		Account account = accountService.findById(id);
	    		AccountResource accountResource = new AccountResource();
				accountResource.setUsername(account.getUsername());
				accountResource.setPassword(account.getPassword());
				accountResource.setAuthorities(account.getAuthorities());
			      	Link link = linkTo(AccountController.class).slash("/findById").slash(account.getId()).withSelfRel();
			      	accountResource.add(link);
			      	
				return new ResponseEntity<AccountResource>(accountResource, HttpStatus.OK);
	    	}
	    	
	    	else {
	    		return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
	    	}
		}
	    
	    @RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	    public ResponseEntity<AccountResource> updateAccount(@PathVariable Long id, @RequestBody Account account) {
	    	
	    	Account accountSearch = accountService.findById(id);
	    	
	    	if(null == accountSearch) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    	
	    	if (null != account) {
	    		accountService.save(account);
	    		AccountResource accountResource = new AccountResource();
				accountResource.setUsername(account.getUsername());
				accountResource.setPassword(account.getPassword());
				accountResource.setAuthorities(account.getAuthorities());
			      	Link link = linkTo(AccountController.class).slash("/findById").slash(account.getId()).withSelfRel();
			      	accountResource.add(link);
			      	
				return new ResponseEntity<AccountResource>(accountResource, HttpStatus.OK);
	    	}
	    	
	    	else {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    }
}
