/**
 * 
 */
package ve.com.gem.entities;

import java.sql.Timestamp;
import javax.persistence.Entity;

/**
 * @author informatica
 *
 */
@Entity
public class Project extends Base {

	/**
	 * 
	 */
	public Project() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param description
	 * @param createdAt
	 * @param updatedAt
	 * @param deletedAt
	 * @param isActive
	 */
	public Project(String name, String description, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt,
			Boolean isActive) {
		super(name, description, createdAt, updatedAt, deletedAt, isActive);
		// TODO Auto-generated constructor stub
	}
	
}
