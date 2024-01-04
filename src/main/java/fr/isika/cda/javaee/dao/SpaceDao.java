package fr.isika.cda.javaee.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.gymspace.GymCaracteristics;
import fr.isika.cda.javaee.entity.gymspace.IdGym;
import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.entity.gymspace.SpaceTextContent;
import fr.isika.cda.javaee.entity.gymspace.VisualIdentity;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;

@Stateless
public class SpaceDao {

	
	@PersistenceContext
	private EntityManager em;
	
	public Space createSpace(SpaceViewModel spaceViewModel) {
			
			SpaceTextContent spaceTextContent = new SpaceTextContent();
			VisualIdentity visualIdentity = new VisualIdentity();
			GymCaracteristics gymCaracteristics = new GymCaracteristics();
			Space space = new Space();
			IdGym gymIdentity = new IdGym();
			
			spaceTextContent.setSpaceName(spaceViewModel.getSpaceName());
			spaceTextContent.setMotto(spaceViewModel.getMotto());
			spaceTextContent.setDescription(spaceViewModel.getDescription());
			
			gymCaracteristics.setLibreAccess(spaceViewModel.getFreeAccess());
			gymCaracteristics.setLockerRoom(spaceViewModel.getLockerRoom());
			
			
			visualIdentity.setFirstColor(spaceViewModel.getFirstColor());
			//DEBUG//////
			System.out.println("First color : " + spaceViewModel.getFirstColor());
			visualIdentity.setSecondColor(spaceViewModel.getSecondColor());
			visualIdentity.setThirdColor(spaceViewModel.getThirdColor());
			visualIdentity.setSpaceTextContent(spaceTextContent);
			
			
			//TO DO : add photos to visualIdentity
			//visualIdentity.setGymLogo(spaceViewModel.getGymLogo());
			gymIdentity.setGymCaracteristics(gymCaracteristics);
			
			space.setIdGym(gymIdentity);
			
			space.setVisualIdentity(visualIdentity);

			em.persist(spaceTextContent);
			em.persist(gymCaracteristics);
			em.persist(visualIdentity);
			em.persist(gymIdentity);
			em.persist(space);
			em.flush();
			return space; 
		}

	public Space getSpaceById(Long id) {
		// TODO :
		return null;
	}

}
