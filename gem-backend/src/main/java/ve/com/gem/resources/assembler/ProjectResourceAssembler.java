package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.ProjectController;
import ve.com.gem.entities.Project;
import ve.com.gem.resources.ProjectResource;

@Component
public class ProjectResourceAssembler extends ResourceAssemblerSupport<Project, ProjectResource> {

	public ProjectResourceAssembler() {
		super(ProjectController.class, ProjectResource.class);
	}

	@Override
	public ProjectResource toResource(Project project) {
		ProjectResource projectResource = new ProjectResource();
		projectResource.setName(project.getName());
		projectResource.setDescription(project.getDescription());
		projectResource.setCreatedAt(project.getCreatedAt());
		projectResource.setUpdatedAt(project.getUpdatedAt());
		projectResource.setDeletedAt(project.getDeletedAt());
		projectResource.setEstimatedStartDate(project.getEstimatedStartDate());
		projectResource.setStartDate(project.getStartDate());
		projectResource.setEstimatedDateEnd(project.getEstimatedDateEnd());
		projectResource.setEstimatedDateEnd(project.getEstimatedDateEnd());
		projectResource.setIsActive(project.getIsActive());
		projectResource.setRisk(project.getRisk());
		projectResource.setTask(project.getTask());
		projectResource.add(linkTo(ProjectController.class).slash("").slash(project.getId()).withSelfRel());
		projectResource.add(linkTo(ProjectController.class).slash("").slash(project.getId()).withRel("project"));
		return projectResource;
	}
	
	
}
