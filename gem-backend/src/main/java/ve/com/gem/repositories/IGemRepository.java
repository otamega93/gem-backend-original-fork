package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Gem;

@Repository
public interface IGemRepository extends PagingAndSortingRepository <Gem, Long> {
	
	public List<Gem> findById(Long id);
	public List<Gem> findByNameLike(String name);

}
