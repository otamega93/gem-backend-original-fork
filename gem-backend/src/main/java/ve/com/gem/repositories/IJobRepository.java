package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Job;

@Repository
public interface IJobRepository extends PagingAndSortingRepository<Job, Long>{

	public List<Job> findJobByTaskId(Long id);
}
