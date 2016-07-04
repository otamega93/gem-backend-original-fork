package ve.com.gem.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Job;

@Repository
public interface IJobRepository extends PagingAndSortingRepository<Job, Long>{

}
