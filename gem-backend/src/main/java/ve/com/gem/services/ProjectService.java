package ve.com.gem.services;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

import ve.com.gem.controllers.TaskController;
import ve.com.gem.entities.Project;
import ve.com.gem.entities.Task;
import ve.com.gem.repositories.IProjectRepository;
import ve.com.gem.resources.TaskResource;


@Transactional(readOnly = true)
@Service
public class ProjectService implements IProjectService {

	@Autowired
	private IProjectRepository projectRepository;
	
	@Autowired
	private ITaskService taskService;
	
	private List<Project> projects = new ArrayList<Project>();
	
	
	@Override
	public Page<Project> findAll(Pageable pageable) {
		projects = Lists.newArrayList(projectRepository.findAll(pageable));
		PageImpl<Project> projectPages= new PageImpl<Project>(projects, pageable, projectRepository.count());
		return projectPages;
	}
	
	@Override
	@JsonIgnoreProperties(ignoreUnknown = true)
	public List<TaskResource> findTaskFromProject(Long id) {
		
		Project project = projectRepository.findOne(id);
		List<Task> tasks = project.getTask();
		List<TaskResource> taskResourceList = new ArrayList<TaskResource>();
		
		for (Task task : tasks) {
			TaskResource taskResource = new TaskResource();
			taskResource.setName(task.getName());
			taskResource.setDescription(task.getDescription());
			taskResource.setCreatedAt(task.getCreatedAt());
			taskResource.setUpdatedAt(task.getUpdatedAt());
			taskResource.setDeletedAt(task.getDeletedAt());
			taskResource.setIsActive(task.getIsActive());
			taskResource.setRisk(task.getRisk());
			//taskResource.setDocumentState(task.getDocumentState());
			taskResource.setEstimatedStartDate(task.getEstimatedStartDate());
			taskResource.setStartDate(task.getStartDate());
			taskResource.setEstimatedDateEnd(task.getEstimatedDateEnd());
			taskResource.setDateEnd(task.getDateEnd());
			taskResource.setIds(task.getId());
			taskResource.setJob(taskService.findJobsFromTask(task.getId()));
			taskResource.add(linkTo(TaskController.class).slash("").slash(task.getId()).withSelfRel());
			taskResource.add(linkTo(TaskController.class).slash("").slash(task.getId()).withRel("task"));
			
			taskResourceList.add(taskResource);
			//taskResourceList.add(new TaskResourceProjectAssembler().toResource(task));	
		}
		
		return taskResourceList;
	}

	@Transactional(readOnly = false)
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
