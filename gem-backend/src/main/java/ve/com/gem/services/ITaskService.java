package ve.com.gem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ve.com.gem.entities.Task;

public interface ITaskService {

	public Task save (Task task);
	
	public Page<Task> findAll (Pageable pageable);
	
	public Page<Task> findByNameLike(Pageable pageable, String name);
	
	public Task findById (Long id);
	
}
