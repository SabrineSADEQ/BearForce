package fr.isika.cda.javaee.entity.gymspace;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Space {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private VisualIdentity visualIdentity;
	
	@OneToOne
	private IdGym idGym;
	
	public VisualIdentity getVisualIdentity() {
		return visualIdentity;
	}
	public void setVisualIdentity(VisualIdentity visualIdentity) {
		this.visualIdentity = visualIdentity;
	}
	public IdGym getIdGym() {
		return idGym;
	}
	public void setIdGym(IdGym idGym) {
		this.idGym = idGym;
	}
	public Long getId() {
		return id;
	}
	
	

}
