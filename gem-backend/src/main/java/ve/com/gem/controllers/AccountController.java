package ve.com.gem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.Account;
import ve.com.gem.services.IAccountService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

	    @Autowired
	    private IAccountService accountService;
	    

	    @RequestMapping(value = "/", method = RequestMethod.POST)
	    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
	    	if (null != account) {
	    		accountService.save(account);
		        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
	    	}
	    	
	    	else {	
	    		return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
	    	}
	    }
	    
	    @RequestMapping(value = "/", method=RequestMethod.GET)
		public ResponseEntity<List<Account>> loadAll(){
			List<Account> account = accountService.findAll();
			
			return new ResponseEntity<List<Account>>(account, HttpStatus.OK);
		}
	    
	    @RequestMapping(value = "/findByUsernameLike/{username}", method=RequestMethod.GET)
		public ResponseEntity<List<Account>> findByUsernameLike(@PathVariable String username){
	    	
	    	if (null != username) {
	    		List<Account> account = accountService.findByUsernameLike(username);
	    		return new ResponseEntity<List<Account>>(account, HttpStatus.OK);
	    	}
	    	else {
	    		return new ResponseEntity<List<Account>>(HttpStatus.NOT_FOUND);
	    	}
		}
	    
	    @RequestMapping(value = "/findByUsername/{username}", method=RequestMethod.GET)
		public ResponseEntity<Account> findByUsername(@PathVariable String username){
	    	
	    	if (null != username) {
	    		Account account = accountService.findByUsername(username);
	    			if (null != account) {
	    				return new ResponseEntity<Account>(account, HttpStatus.OK);
	    			}
	    			else {
	    				return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
	    			}
	    	}
	    	else {
	    		return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
	    	}
		}
	    
	    @RequestMapping(value = "/findById/{id}", method=RequestMethod.GET)
		public ResponseEntity<Account> findById(@PathVariable Long id) {
	    	
	    	if (null != id) {
	    		Account account = accountService.findById(id);
	    		return new ResponseEntity<Account>(account, HttpStatus.OK);
	    	}
	    	else {
	    		return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
	    	}
		}
	    
	    @RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
	    	
	    	Account accountSearch = accountService.findById(id);
	    	
	    	if(null == accountSearch) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    	
	    	if (null != account) {
	    		accountService.save(account);
	    		return new ResponseEntity<>(account, HttpStatus.OK);
	    	}
	    	
	    	else {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    }
}
