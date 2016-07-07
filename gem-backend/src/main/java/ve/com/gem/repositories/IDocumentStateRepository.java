package ve.com.gem.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.DocumentState;

@Repository
public interface IDocumentStateRepository extends PagingAndSortingRepository<DocumentState, Long> {

}
