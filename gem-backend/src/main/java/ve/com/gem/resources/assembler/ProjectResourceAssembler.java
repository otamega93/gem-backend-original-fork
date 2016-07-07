package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.ProjectController;
import ve.com.gem.entities.Project;
import ve.com.gem.resources.ProjectResource;
import ve.com.gem.services.IProjectService;

@Component
public class ProjectResourceAssembler extends ResourceAssemblerSupport<Project, ProjectResource> {

	@Autowired
	private IProjectService projectService;
	
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
		projectResource.setIsActive(project.getIsActive());
		projectResource.setRisk(project.getRisk());
		projectResource.setIds(project.getId());
		projectResource.setTask(projectService.findTaskFromProject(project.getId()));
		projectResource.add(linkTo(ProjectController.class).slash("").slash(project.getId()).withSelfRel());
		projectResource.add(linkTo(ProjectController.class).slash("").slash(project.getId()).withRel("project"));
		return projectResource;
	}
	
	
}
