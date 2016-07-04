package ve.com.gem.resources;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ve.com.gem.entities.DocumentState;
import ve.com.gem.entities.Job;
import ve.com.gem.entities.Risk;


@Relation(collectionRelation = "tasks")
public class TaskResource extends ResourceSupport {

	private String name;
	
	private String description;
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;
	
	private Timestamp deletedAt;
	
	private Boolean isActive;
	
	private DocumentState documentState;
	
	private Timestamp estimatedStartDate;
	
	private Timestamp startDate;
	
	private Timestamp estimatedDateEnd;
	
	private Timestamp dateEnd;
	
	private Long ids;
	
	private Risk risk;
	
	private List<Job> job = new ArrayList<Job>();

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

	@JsonIgnore
	public String getDocumentState() {
		return documentState.toString();
	}

	public void setDocumentState(DocumentState documentState) {
		this.documentState = documentState;
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

	public Long getIds() {
		return ids;
	}

	public void setIds(Long ids) {
		this.ids = ids;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}
	
}
