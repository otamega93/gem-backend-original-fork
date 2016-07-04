package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ve.com.gem.entities.Project;

public interface IProjectService {

	public List<Project> findAll(Pageable pageable);
	
	public Project save(Project project);
}
