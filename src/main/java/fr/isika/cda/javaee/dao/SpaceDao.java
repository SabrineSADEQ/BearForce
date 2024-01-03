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
	
	public Space createSpaceTextContent(SpaceViewModel spaceViewModel) {
			
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
			
			//TO DO : add colors and photos to visualIdentity
			visualIdentity.setSpaceTextContent(spaceTextContent);
			
			gymIdentity.setGymCaracteristics(gymCaracteristics);
			
			space.setIdGym(gymIdentity);
			
			space.setVisualIdentity(visualIdentity);

			em.persist(spaceTextContent);
			System.out.println("spacetextcontent persisté");
			em.persist(gymCaracteristics);
			em.persist(visualIdentity);
			em.persist(gymIdentity);
			em.persist(space);
			em.flush();
			return space; 
		}

	public void createSpaceVisualIdentity(SpaceViewModel spaceViewModel) {
		
	}
}
