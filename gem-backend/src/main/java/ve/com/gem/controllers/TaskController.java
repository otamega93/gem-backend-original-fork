package ve.com.gem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.Task;
import ve.com.gem.resources.TaskResource;
import ve.com.gem.resources.assembler.TaskResourceAssembler;
import ve.com.gem.services.ITaskService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/v1/tasks")
public class TaskController {

	@Autowired
	private ITaskService taskService;

	@Autowired
	private TaskResourceAssembler taskResourceAssembler;

	@Autowired
	private PagedResourcesAssembler<Task> pageAssembler;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public PagedResources<TaskResource> findAll(Pageable pageable) {
		
		Page<Task> tasks = taskService.findAll(pageable);
		return pageAssembler.toResource(tasks, taskResourceAssembler);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<TaskResource> save (@RequestBody Task task) {
		
		if (null != task) {
			taskService.save(task);
			return new ResponseEntity<TaskResource>(taskResourceAssembler.toResource(task), HttpStatus.OK);
		}

		else {
			return new ResponseEntity<TaskResource>(taskResourceAssembler.toResource(task), HttpStatus.BAD_REQUEST);
		}
	}
	
}
