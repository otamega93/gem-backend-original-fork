package ve.com.gem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.Account;
import ve.com.gem.resources.AccountResource;
import ve.com.gem.resources.assembler.AccountResourceAssembler;
import ve.com.gem.services.IAccountService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private AccountResourceAssembler accountResourceAssembler;

	@Autowired
	private PagedResourcesAssembler<Account> pageAssembler;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Account> saveAccount(@RequestBody Account account) {

		if (null != account && accountService.findByUsername(account.getUsername()) == null) {

			accountService.save(account);
			return new ResponseEntity<Account>(account, HttpStatus.CREATED);
		}

		else if (accountService.findByUsername(account.getUsername()) != null) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		else {
			return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public PagedResources<AccountResource> loadAll(Pageable pageable) {

		Page<Account> accounts = accountService.findAll(pageable);
		return pageAssembler.toResource(accounts, accountResourceAssembler);
	}

	@RequestMapping(value = "search/findByUsernameLike/{username}", method = RequestMethod.GET)
	public PagedResources<AccountResource> loadAll(@PathVariable String username, Pageable pageable) {

		if (null != username) {
			Page<Account> accounts = accountService.findByUsernameLike(username, pageable);
			return pageAssembler.toResource(accounts, accountResourceAssembler);
		} else {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return null;
		}
	}

	@RequestMapping(value = "search/findByUsername/{username}", method = RequestMethod.GET)
	public ResponseEntity<Account> findByUsername(@PathVariable String username) {

		if (null != username) {
			Account account = accountService.findByUsername(username);
			if (null != account) {

				return new ResponseEntity<Account>(account, HttpStatus.OK);
				
			} else {
				return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
			}
			
		} else {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> findById(@PathVariable Long id) {

		if (null != id) {

			Account account = accountService.findById(id);
			if (null != account) {

				return new ResponseEntity<AccountResource>(accountResourceAssembler.toResource(account), HttpStatus.OK);
			}
		}

		else {

			return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<AccountResource>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<AccountResource> updateAccount(@PathVariable Long id, @RequestBody Account account) {

		Account accountSearch = accountService.findById(id);

		if (null == accountSearch) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (null != account) {
			
			accountService.save(account);
			return new ResponseEntity<AccountResource>(accountResourceAssembler.toResource(account), HttpStatus.OK);
		}

		else {
			
			return new ResponseEntity<AccountResource>(accountResourceAssembler.toResource(account),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
