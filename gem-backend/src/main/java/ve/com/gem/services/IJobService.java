package ve.com.gem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ve.com.gem.entities.Job;

public interface IJobService {

	public Job save (Job job);
	
	public Page<Job> findAll(Pageable pageable);
	
	public Job findById (Long id);
	
}
