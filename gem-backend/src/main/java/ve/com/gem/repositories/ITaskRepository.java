package ve.com.gem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Task;

@Repository
public interface ITaskRepository extends PagingAndSortingRepository<Task, Long> {

	public Page<Task> findByNameLike (Pageable pagable,String name);
}
