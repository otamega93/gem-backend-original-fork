package ve.com.gem.services;

import java.util.List;

import ve.com.gem.entities.Account;

public interface IAccountService {

	Account save(Account account);
	Account findById(Long id);
	Account findByUsername(String key);
	List<Account> findAll();
	List<Account> findByUsernameLike(String key);

}
