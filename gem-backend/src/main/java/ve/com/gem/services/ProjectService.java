package ve.com.gem.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ve.com.gem.entities.Project;
import ve.com.gem.repositories.IProjectRepository;

@Transactional(readOnly = true)
@Service
public class ProjectService implements IProjectService {

	@Autowired
	private IProjectRepository projectRepository;
	
	private List<Project> projects = new ArrayList<Project>();
	
	
	@Override
	public List<Project> findAll(Pageable pageable) {
		projects = Lists.newArrayList(projectRepository.findAll(pageable));
		return projects;
	}

	@Override
	public Project save(Project project) {
		if (null != project) {
			project.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			projectRepository.save(project);
			return project;
		}
		
		return null;
	}

}
