package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.TaskController;
import ve.com.gem.entities.Task;
import ve.com.gem.resources.TaskResource;
import ve.com.gem.services.IDocumentStateService;
import ve.com.gem.services.ITaskService;

@Component
public class TaskResourceAssembler extends ResourceAssemblerSupport<Task, TaskResource>{

	@Autowired
	private ITaskService taskService;

	public TaskResourceAssembler () {
		super(TaskController.class, TaskResource.class);
	}
	
	@Override
	public TaskResource toResource(Task task) {
		TaskResource taskResource = new TaskResource();
		taskResource.setName(task.getName());
		taskResource.setDescription(task.getDescription());
		taskResource.setCreatedAt(task.getCreatedAt());
		taskResource.setUpdatedAt(task.getUpdatedAt());
		taskResource.setDeletedAt(task.getDeletedAt());
		taskResource.setIsActive(task.getIsActive());
		taskResource.setRisk(task.getRisk());
		//taskResource.setDocumentState(taskService.findDocumentStateFromTaskId(task.getDocumentState().getId()));
		taskResource.setEstimatedStartDate(task.getEstimatedStartDate());
		taskResource.setStartDate(task.getStartDate());
		taskResource.setEstimatedDateEnd(task.getEstimatedDateEnd());
		taskResource.setDateEnd(task.getDateEnd());
		taskResource.setIds(task.getId());
		taskResource.setJob(taskService.findJobsFromTask(task.getId()));
		taskResource.add(linkTo(TaskController.class).slash("").slash(task.getId()).withSelfRel());
		taskResource.add(linkTo(TaskController.class).slash("").slash(task.getId()).withRel("task"));
		return taskResource;
	}

}
