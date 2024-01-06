package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.accounts.Address;
import fr.isika.cda.javaee.entity.accounts.Contact;
import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.gymspace.AdminInfoGym;
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
//			AdminInfoGym adminInfoGym = new AdminInfoGym();
//			Profile gymOwner = new Profile();
//			Address gymLocation = new Address();
//			Contact contactInfo = new Contact();
			
			spaceTextContent.setSpaceName(spaceViewModel.getSpaceName());
			spaceTextContent.setMotto(spaceViewModel.getMotto());
			spaceTextContent.setDescription(spaceViewModel.getDescription());
			
			gymCaracteristics.setLibreAccess(spaceViewModel.getFreeAccess());
			gymCaracteristics.setLockerRoom(spaceViewModel.getLockerRoom());
			
			
			visualIdentity.setFirstColor(spaceViewModel.getFirstColor());
			visualIdentity.setSecondColor(spaceViewModel.getSecondColor());
			visualIdentity.setThirdColor(spaceViewModel.getThirdColor());
			visualIdentity.setSpaceTextContent(spaceTextContent);
			visualIdentity.setGymLogoPath(spaceViewModel.getGymLogoPath());
			
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
	
	public List<Space> getAllSpaces() {
		// TODO :
		return null;
	}

}
