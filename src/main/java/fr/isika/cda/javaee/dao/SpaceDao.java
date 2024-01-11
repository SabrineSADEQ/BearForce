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
import fr.isika.cda.javaee.entity.gymspace.business.Course;
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
		AdminInfoGym adminInfoGym = new AdminInfoGym();
		Address gymLocation = new Address();
		Contact contactInfo = new Contact();
		
		gymIdentity.setGymName(spaceViewModel.getSpaceName());

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
		visualIdentity.setGymBannerPath(spaceViewModel.getGymBannerPath());

		gymLocation = spaceViewModel.getLocation();
		adminInfoGym.setLocation(gymLocation);
		adminInfoGym.setContactInfo(spaceViewModel.getContactInfo());
		adminInfoGym.setSiret(spaceViewModel.getSiret());

		gymIdentity.setGymCaracteristics(gymCaracteristics);
		gymIdentity.setAdminInfoGym(adminInfoGym);
		
		space.setVisualIdentity(visualIdentity);
		
		space.setIdGym(gymIdentity);
		
		em.persist(spaceTextContent);
		em.persist(gymCaracteristics);
		em.persist(visualIdentity);
		em.persist(gymLocation);
		em.persist(contactInfo);
		em.persist(adminInfoGym);
		em.persist(gymIdentity);
		em.persist(space);
		em.flush();
		return space;
	}

	public Space getSpaceById(Long spaceId) {
		return em.createQuery("SELECT space FROM Space space WHERE space.id = :spaceIdParam", Space.class)
				.setParameter("spaceIdParam", spaceId).getSingleResult();
	}

	public List<Space> getAllSpaces() {

		return em.createQuery("SELECT s FROM Space s", Space.class).getResultList();
	}

	public void update(Space updatedSpace) {
		//Find the given space from the database
		Space oldSpace = em.find(Space.class, updatedSpace.getId());
		
		if (oldSpace != null) {
			//Update the space with the new data
			
			//Contact
			oldSpace.getIdGym().getAdminInfoGym().getContactInfo().setEmail(updatedSpace.getIdGym().getAdminInfoGym().getContactInfo().getEmail());
			oldSpace.getIdGym().getAdminInfoGym().getContactInfo().setPhone(updatedSpace.getIdGym().getAdminInfoGym().getContactInfo().getPhone());
			
			//SpaceTextContent
			oldSpace.getVisualIdentity().getSpaceTextContent().setSpaceName(updatedSpace.getVisualIdentity().getSpaceTextContent().getSpaceName());
			oldSpace.getVisualIdentity().getSpaceTextContent().setMotto(updatedSpace.getVisualIdentity().getSpaceTextContent().getMotto());
			oldSpace.getVisualIdentity().getSpaceTextContent().setDescription(updatedSpace.getVisualIdentity().getSpaceTextContent().getDescription());
			
			//VisualIdentity
			oldSpace.getVisualIdentity().setFirstColor(updatedSpace.getVisualIdentity().getFirstColor());
			oldSpace.getVisualIdentity().setSecondColor(updatedSpace.getVisualIdentity().getSecondColor());
			oldSpace.getVisualIdentity().setThirdColor(updatedSpace.getVisualIdentity().getThirdColor());
			oldSpace.getVisualIdentity().setGymLogoPath(updatedSpace.getVisualIdentity().getGymLogoPath());
			oldSpace.getVisualIdentity().setGymBannerPath(updatedSpace.getVisualIdentity().getGymBannerPath());
			
			//Save modifications
			em.merge(oldSpace);
			
		}
		else {
			System.out.println("Space not found for ID: "+ updatedSpace.getId());
		}
		
		
		
	}

}
