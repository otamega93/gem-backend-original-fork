package ve.com.gem.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import ve.com.gem.entities.Account;
import ve.com.gem.repositories.IAccountRepository;

@Transactional
@Service
public class AccountService implements IAccountService {
	
	List<Account> accounts = new ArrayList<Account>(); 

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAccountRepository accountRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Account save(Account account) {
    	if (account.getPassword() != null) {
    		account.setPassword(passwordEncoder.encode(account.getPassword()));
    	}
    	
    	if (account.getId() != null) {
    		account.setId(account.getId());
    	}
    	
        accountRepository.save(account);
        return account;
        
    }

	@Override
	public List<Account> findAll() {
		return accounts = Lists.newArrayList(accountRepository.findAll());
	}

	@Override
	public List<Account> findByUsernameLike(String key) {
		return accountRepository.findByUsernameLike("%"+key+"%");
	}

	@Override
	public Account findById(Long id) {
		return accountRepository.findOne(id);
	}

	@Override
	public Account findByUsername(String key) {
		return accountRepository.findByUsername(key);
	}    
    
}
