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

import com.google.common.collect.Lists;

import ve.com.gem.controllers.DocumentStateController;
import ve.com.gem.entities.DocumentState;
import ve.com.gem.entities.Job;
import ve.com.gem.entities.Project;
import ve.com.gem.entities.Task;
import ve.com.gem.repositories.IDocumentStateRepository;
import ve.com.gem.repositories.IProjectRepository;
import ve.com.gem.repositories.ITaskRepository;
import ve.com.gem.resources.DocumentStateResource;
import ve.com.gem.resources.JobResource;
import ve.com.gem.resources.assembler.DocumentStateResourceAssembler;
import ve.com.gem.resources.assembler.JobResourceAssembler;
import ve.com.gem.services.ITaskService;

@Transactional(readOnly = true)
@Service
public class TaskService implements ITaskService {

	@Autowired
	private IDocumentStateRepository documentStateRepository;
	
	@Autowired
	private ITaskRepository taskRepository;
	
	@Autowired
	private IProjectRepository projectRepository;
	
	private List<Task> tasks = new ArrayList<Task>();
	
	@Transactional(readOnly = false)
	@Override
	public Task save(Task task) {
		if (null != task) {
			task.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			Project project = projectRepository.findOne(task.getProject().getId());
			task.setProject(project);
			project.getTask().add(task);
			projectRepository.save(project);

			//TEST
			DocumentState documentState = documentStateRepository.findOne(1L);
			task.setDocumentState(documentState);
			
			return taskRepository.save(task);
		}
		
		else {
			return null;
		}
	}

	@Override
	public Page<Task> findAll(Pageable pageable) {
		
		tasks = Lists.newArrayList(taskRepository.findAll(pageable));
		PageImpl<Task> taskPages = new PageImpl<Task>(tasks, pageable, taskRepository.count());
		return taskPages;
	}
	
	@Override
	public List<JobResource> findJobsFromTask(Long id) {
		
		Task task = taskRepository.findOne(id);
		List<Job> jobs = task.getJob(); 
		List<JobResource> jobResourceList = new ArrayList<JobResource>();
		
		for (Job job : jobs) {
			jobResourceList.add(new JobResourceAssembler().toResource(job));
		}
		return jobResourceList;
	}


	@Override
	public Page<Task> findByNameLike(Pageable pageable, String name) {
		
		tasks = Lists.newArrayList(taskRepository.findByNameLike(pageable, "%" + name + "%"));
		PageImpl<Task> taskPages = new PageImpl<>(tasks, pageable, taskRepository.count());
		return taskPages;
	}
	

	@Override
	public DocumentStateResource findDocumentStateFromTaskId(Long id) {
		
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
	public Task findById(Long id) {
		
		if (null != id) {
			return taskRepository.findOne(id);			 
		}
		
		return null;
	}

}
