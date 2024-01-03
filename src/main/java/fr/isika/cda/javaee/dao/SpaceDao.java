package fr.isika.cda.javaee.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.entity.gymspace.SpaceTextContent;
import fr.isika.cda.javaee.entity.gymspace.VisualIdentity;

@Stateless
public class SpaceDao {

	
	@PersistenceContext
	private EntityManager em;
	
	public Long createSpaceVisualIdentity(VisualIdentity visualIdentityToPersist) {
			
			// 1 - On va créer les entités vides
			SpaceTextContent spaceTextContent = new SpaceTextContent();
			VisualIdentity visualIdentity = new VisualIdentity();
			
			
			
			// 2 - On va copier les données provenant du formulaire vers les entités vides
			//spaceTextContent.setSpaceName(space.getVisualIdentity().getSpaceTextContent());

			
			// 3 - On va persister les entités créées
				// a - on ne doit pas oublier la relation one to one !!!
//				user.setUserDetailsEntity(userDetails);
//				
//				// b - on persiste les deux entités
//				em.persist(userDetails);
//				em.persist(user);
//				
//			// 4 - On renvoie l'id de l'entité parente "userEntity"
			return visualIdentityToPersist.getId(); //user.getId();
		}
}
