/**
 * 
 */
package ve.com.gem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author informatica
 *
 */
@Entity
public class Expert {

	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Basic constructor.
	 */
	public Expert() {
		// TODO Auto-generated constructor stub
	}

}
