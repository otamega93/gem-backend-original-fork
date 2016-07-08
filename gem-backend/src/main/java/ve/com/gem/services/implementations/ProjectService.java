package ve.com.gem.services.implementations;

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

import ve.com.gem.controllers.DocumentStateController;
import ve.com.gem.controllers.TaskController;
import ve.com.gem.entities.DocumentState;
import ve.com.gem.entities.Project;
import ve.com.gem.entities.Task;
import ve.com.gem.repositories.IDocumentStateRepository;
import ve.com.gem.repositories.IProjectRepository;
import ve.com.gem.resources.DocumentStateResource;
import ve.com.gem.resources.TaskResource;
import ve.com.gem.resources.assembler.TaskResourceAssembler;
import ve.com.gem.services.IProjectService;
import ve.com.gem.services.ITaskService;


@Transactional(readOnly = true)
@Service
public class ProjectService implements IProjectService {

	@Autowired
	private IProjectRepository projectRepository;
	
	@Autowired
	private ITaskService taskService;
	
	@Autowired
	private IDocumentStateRepository documentStateRepository;
	
	private List<Project> projects = new ArrayList<Project>();
	
	
	@Override
	public Page<Project> findAll(Pageable pageable) {
		projects = Lists.newArrayList(projectRepository.findAll(pageable));
		PageImpl<Project> projectPages= new PageImpl<Project>(projects, pageable, projectRepository.count());
		return projectPages;
	}
	
	public DocumentStateResource findDocumentStateFromProjectId(Long id) {
		
		DocumentState documentState = documentStateRepository.findOne(id);
		DocumentStateResource documentStateResource = new DocumentStateResource();
		documentStateResource.setName(documentState.getName());
		documentStateResource.setDescription(documentState.getDescription());
		documentStateResource.setCreatedAt(documentState.getCreatedAt());
		documentStateResource.setUpdatedAt(documentState.getUpdatedAt());
		documentStateResource.setDeletedAt(documentState.getDeletedAt());
		documentStateResource.setIds(documentState.getId());
		documentStateResource.add(linkTo(DocumentStateController.class).slash("").slash(documentState.getId()).withSelfRel());
	    documentStateResource.add(linkTo(DocumentStateController.class).slash("").slash(documentState.getId()).withRel("documentState"));
		return documentStateResource;
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
			taskResource.setDocumentState(taskService.findDocumentStateFromTaskId(task.getDocumentState().getId()));
			taskResource.setEstimatedStartDate(task.getEstimatedStartDate());
			taskResource.setStartDate(task.getStartDate());
			taskResource.setEstimatedDateEnd(task.getEstimatedDateEnd());
			taskResource.setDateEnd(task.getDateEnd());
			taskResource.setIds(task.getId());
			taskResource.setJob(taskService.findJobsFromTask(task.getId()));
			taskResource.add(linkTo(TaskController.class).slash("").slash(task.getId()).withSelfRel());
			taskResource.add(linkTo(TaskController.class).slash("").slash(task.getId()).withRel("task"));
			
			taskResourceList.add(taskResource);
		}
		
		return taskResourceList;
	}

	@Transactional(readOnly = false)
	@Override
	public Project save(Project project) {
		if (null != project) {
			project.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			
			//TEST
			DocumentState documentState = documentStateRepository.findOne(1L);
			project.setDocumentState(documentState);
			
			projectRepository.save(project);
			return project;
		}
		
		return null;
	}

}
