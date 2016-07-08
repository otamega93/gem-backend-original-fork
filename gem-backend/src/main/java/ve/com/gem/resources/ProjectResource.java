package ve.com.gem.resources;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import ve.com.gem.entities.Risk;

@Relation(collectionRelation = "projects")
public class ProjectResource extends ResourceSupport {
	
	private String name;
	
	private String description;
	
	private Timestamp estimatedStartDate;
	
	private Timestamp startDate;
	
	private Timestamp estimatedDateEnd;
	
	private Timestamp dateEnd;

	private Timestamp createdAt;
	
	private Timestamp updatedAt;
	
	private Timestamp deletedAt;

	private Boolean isActive;
	
	private DocumentStateResource documentState;
	
	private Risk risk;
	
	private Long ids;
	
	private List<TaskResource> task = new ArrayList<TaskResource>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getEstimatedStartDate() {
		return estimatedStartDate;
	}

	public void setEstimatedStartDate(Timestamp estimatedStartDate) {
		this.estimatedStartDate = estimatedStartDate;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEstimatedDateEnd() {
		return estimatedDateEnd;
	}

	public void setEstimatedDateEnd(Timestamp estimatedDateEnd) {
		this.estimatedDateEnd = estimatedDateEnd;
	}

	public Timestamp getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Timestamp dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public List<TaskResource> getTask() {
		return task;
	}

	public void setTask(List<TaskResource> task) {
		this.task = task;
	}

	public Long getIds() {
		return ids;
	}

	public void setIds(Long ids) {
		this.ids = ids;
	}

	public DocumentStateResource getDocumentState() {
		return documentState;
	}

	public void setDocumentState(DocumentStateResource documentState) {
		this.documentState = documentState;
	}

}