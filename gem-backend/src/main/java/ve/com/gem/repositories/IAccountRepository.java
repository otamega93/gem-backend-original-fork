package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ve.com.gem.entities.Account;

@Repository
public interface IAccountRepository extends PagingAndSortingRepository<Account, Long> {

    Account findByUsername(String username);
    
    Account findOne(Long id);
    
    List<Account> findByUsernameLike(String username, Pageable pageable);

    Account save (Account account);
}
