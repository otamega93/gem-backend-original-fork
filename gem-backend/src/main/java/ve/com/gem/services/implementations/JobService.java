package ve.com.gem.services.implementations;

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

import ve.com.gem.entities.Job;
import ve.com.gem.entities.Task;
import ve.com.gem.repositories.IJobRepository;
import ve.com.gem.repositories.ITaskRepository;
import ve.com.gem.services.IJobService;

@Transactional(readOnly = true)
@Service
public class JobService implements IJobService {
	
	
	@Autowired
	private IJobRepository jobRepository;
	
	@Autowired
	private ITaskRepository taskRepository;
	
	private List<Job> jobs = new ArrayList<Job>();

	
	@Transactional(readOnly = false)
	@Override
	public Job save(Job job) {
		
		if (null != job) {
			job.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			Task task = taskRepository.findOne(job.getTask().getId());
			job.setTask(task);
			task.getJob().add(job);
			taskRepository.save(task);
			return jobRepository.save(job);
		}
		
		else
			return null;
	}

	@Override
	public Page<Job> findAll(Pageable pageable) {
		
		jobs = Lists.newArrayList(jobRepository.findAll(pageable));
		PageImpl<Job> jobPages = new PageImpl<>(jobs, pageable, jobRepository.count());
		return jobPages;
	}

	@Override
	public Job findById(Long id) {
		
		if (null != id)
			return jobRepository.findOne(id);
		
		else
			return null;
	}

}
