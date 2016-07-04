package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.JobController;
import ve.com.gem.entities.Job;
import ve.com.gem.resources.JobResource;

@Component
public class JobResourceAssembler extends ResourceAssemblerSupport<Job, JobResource> {

	public JobResourceAssembler() {
		super(JobController.class, JobResource.class);
	}
	
	@Override
	public JobResource toResource(Job job) {
		JobResource jobResource = new JobResource();
		jobResource.setName(job.getName());
		jobResource.setDescription(job.getDescription());
		jobResource.setCreatedAt(job.getCreatedAt());
		jobResource.setUpdatedAt(job.getUpdatedAt());
		jobResource.setDeletedAt(job.getDeletedAt());
		jobResource.setIsActive(job.getIsActive());
		//jobResource.setTask(job.getTask());
		jobResource.setIds(job.getId());
		jobResource.add(linkTo(JobController.class).slash("").slash(job.getId()).withSelfRel());
		jobResource.add(linkTo(JobController.class).slash("").slash(job.getId()).withRel("job"));
		return jobResource;
	}

}
