package ve.com.gem.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ve.com.gem.entities.Account;
import ve.com.gem.repositories.IAccountRepository;
import ve.com.gem.services.IAccountService;

@Transactional(readOnly = true)
@Service
public class AccountService implements IAccountService {
	
	List<Account> accounts = new ArrayList<Account>(); 

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAccountRepository accountRepository;

    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @Transactional(readOnly = false)
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
	public Page<Account> findAll(Pageable pageable) {
		accounts = Lists.newArrayList(accountRepository.findAll(pageable));
		PageImpl<Account> accountPages= new PageImpl<>(accounts, pageable, accountRepository.count());
		return accountPages;
	}

	@Override
	public Page<Account> findByUsernameLike(String key, Pageable pageable) {
		accountRepository.findByUsernameLike("%"+key+"%", pageable);
		PageImpl<Account> accountPages= new PageImpl<>(accounts, pageable, accountRepository.count());
		return accountPages;
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
