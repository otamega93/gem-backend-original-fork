package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import ve.com.gem.entities.Account;
import ve.com.gem.entities.Gem;

@Repository
public interface IAccountRepository extends PagingAndSortingRepository<Account, Long> {

    Account findByUsername(String username);
    
    Account findOne(Long id);
    
    List<Account> findByUsernameLike(String username);

    Account save(Account account);
}
