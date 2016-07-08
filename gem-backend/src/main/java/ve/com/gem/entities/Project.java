package ve.com.gem.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String description;
	
	private Timestamp estimatedStartDate;
	
	private Timestamp startDate;
	
	private Timestamp estimatedDateEnd;
	
	private Timestamp dateEnd;
	
	@NotNull
	private Timestamp createdAt;
	
	private Timestamp updatedAt;
	
	private Timestamp deletedAt;

	@NotNull
	private Boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "risk_id", nullable=true, insertable=true, updatable=true)
	private Risk risk;
	
	@OneToMany(mappedBy = "project", orphanRemoval = true)
	@JsonManagedReference(value = "project-task")
	private List<Task> task = new ArrayList<Task>();
	
	@ManyToOne
	@JoinColumn(name = "document_state_id")
	private DocumentState documentState;

	public Project(Long id) {
		this.id = id;
	}
	
	public Project(Long id, String name, String description, Timestamp estimatedStartDate, Timestamp startDate,
			Timestamp estimatedDateEnd, Timestamp dateEnd, Timestamp createdAt, Timestamp updatedAt,
			Timestamp deletedAt, Boolean isActive, Risk risk, List<Task> task, DocumentState documentState) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.estimatedStartDate = estimatedStartDate;
		this.startDate = startDate;
		this.estimatedDateEnd = estimatedDateEnd;
		this.dateEnd = dateEnd;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.isActive = isActive;
		this.risk = risk;
		this.task = task;
		this.documentState = documentState;
	}

	public Project() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}

	public DocumentState getDocumentState() {
		return documentState;
	}

	public void setDocumentState(DocumentState documentState) {
		this.documentState = documentState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", estimatedStartDate="
				+ estimatedStartDate + ", startDate=" + startDate + ", estimatedDateEnd=" + estimatedDateEnd
				+ ", dateEnd=" + dateEnd + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt="
				+ deletedAt + ", isActive=" + isActive + ", risk=" + risk + ", task=" + task + ", documentState="
				+ documentState + "]";
	}
	
}
