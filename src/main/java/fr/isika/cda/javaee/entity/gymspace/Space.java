package fr.isika.cda.javaee.entity.gymspace;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
/**
 * 
 * @author Bitjoka Vincent
 */
@Entity
public class Space {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private VisualIdentity visualIdentity;
	@OneToOne
	private IdGym idGym;

}
