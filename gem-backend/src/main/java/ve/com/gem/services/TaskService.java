package ve.com.gem.services;

import java.sql.Timestamp;
import java.time.LocalDate;
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

import ve.com.gem.entities.Job;
import ve.com.gem.entities.Task;
import ve.com.gem.repositories.ITaskRepository;
import ve.com.gem.resources.JobResource;
import ve.com.gem.resources.assembler.JobResourceAssembler;

@Transactional(readOnly = true)
@Service
public class TaskService implements ITaskService {

	@Autowired
	private ITaskRepository taskRepository;
	
	private List<Task> tasks = new ArrayList<Task>();
	
	@Transactional(readOnly = false)
	@Override
	public Task save(Task task) {
		System.out.println(LocalDateTime.now());
		if (null != task) {
			task.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			return taskRepository.save(task);
		}
		
		else {
			return null;
		}
	}

	@Override
	public Page<Task> findAll(Pageable pageable) {
		
		tasks = Lists.newArrayList(taskRepository.findAll(pageable));
		PageImpl<Task> taskPages = new PageImpl<>(tasks, pageable, taskRepository.count());
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
	public Task findById(Long id) {
		
		if (null != id) {
			return taskRepository.findOne(id);			 
		}
		
		return null;
	}

}
