package initializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.ActivityDao;
import fr.isika.cda.javaee.dao.CourseDAO;
import fr.isika.cda.javaee.dao.EquipmentDao;
import fr.isika.cda.javaee.dao.SpaceDao;
import fr.isika.cda.javaee.dao.accounts.AccountDao;

import fr.isika.cda.javaee.dao.gymspace.MembershipDao;
import fr.isika.cda.javaee.dao.platform.SubscriptionDao;

import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.accounts.Address;
import fr.isika.cda.javaee.entity.accounts.Contact;
import fr.isika.cda.javaee.entity.accounts.Goal;
import fr.isika.cda.javaee.entity.accounts.ProfessionalDetails;
import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.accounts.Role;
import fr.isika.cda.javaee.entity.accounts.TypeContact;
import fr.isika.cda.javaee.entity.gymspace.Membership;
import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.ActivityCategory;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.entity.gymspace.business.Equipment;
import fr.isika.cda.javaee.entity.platform.Subscription;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityViewModel;
import fr.isika.cda.javaee.presentation.viewmodel.CourseViewModel;
import fr.isika.cda.javaee.presentation.viewmodel.EquipmentViewModel;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;
import fr.isika.cda.javaee.utils.PasswordUtils;
import fr.isika.cda.javaee.viewModel.AccountViewModel;

@Singleton
@Startup
public class DataInitializer {

	@Inject
	private AccountDao accountDao;

	@Inject
	private SubscriptionDao subscriptionDao;

	@Inject
	private ActivityDao activityDao;

	@Inject
	private CourseDAO courseDao;

	@Inject
	private EquipmentDao equipmentDao;

	@Inject
	private SpaceDao spaceDao;

	@Inject
	MembershipDao membershipDao;

	@PostConstruct
	public void initialize() {
		createDefaultUser();
	}

	private void createDefaultUser() {
		// Check if the default user already exists

		AccountViewModel defaultUser = new AccountViewModel();
		defaultUser.setEmail("azerty");
		defaultUser.setPassword("azerty");
		defaultUser.setRole(Role.SUPER_ADMIN);
		accountDao.createAccount(defaultUser);
		AccountViewModel Adherent = new AccountViewModel();
		Adherent.setEmail("adherent");
		Adherent.setPassword("adherent");
		Adherent.setRole(Role.ADHERENT);
		Profile profileAdherent = new Profile();
		profileAdherent.setFirstName("Yoann");
		profileAdherent.setLastName("François");
		Address addressAdherent = new Address();
		addressAdherent.setCity("paris");
		addressAdherent.setStreetName("le lieu");
		Contact contact = new Contact();
		contact.setPhone("12212321");
		profileAdherent.setPictureUrl("bear_user_image.jpg");
		profileAdherent.setAddress(addressAdherent);
		profileAdherent.setContact(contact);
		Adherent.setProfile(profileAdherent);
		accountDao.createAccount(Adherent);
		AccountViewModel gestionnaire = new AccountViewModel();
		gestionnaire.setEmail("gestionnaire");
		gestionnaire.setPassword("gestionnaire");
		gestionnaire.setRole(Role.GESTIONNAIRE);

		Profile profileGestionnaire = new Profile();
		profileGestionnaire.setFirstName("Jean");
		profileGestionnaire.setLastName("Dupont");
		profileGestionnaire.setPictureUrl("bear_user_image.jpg");

		Address addressGestionnaire = new Address();
		addressGestionnaire.setStreetNumber("5");
		addressGestionnaire.setStreetName("Rue de la Paix");
		addressGestionnaire.setPostalCode("75001");
		addressGestionnaire.setCity("Paris");

		Contact contactGestionnaire = new Contact();
		contactGestionnaire.setPhone("0623456789");
		contactGestionnaire.setEmail("jean.dupont@gmail.com");

		profileGestionnaire.setAddress(addressGestionnaire);
		profileGestionnaire.setContact(contactGestionnaire);

		gestionnaire.setProfile(profileGestionnaire);

		accountDao.createAccount(gestionnaire);
		AccountViewModel coach = new AccountViewModel();
		coach.setEmail("coach");
		coach.setPassword("coach");
		coach.setRole(Role.COACH);

		Goal goal2 = new Goal();
		goal2.setGoalName("Perte de poids");
		goal2.setGoalDate(LocalDate.of(2024, Month.FEBRUARY, 28));

		coach.setGoal(goal2);

		Profile profileCoach = new Profile();
		profileCoach.setFirstName("Alice");
		profileCoach.setLastName("Smith");
		profileCoach.setPictureUrl("bear_user_image.jpg");

		Address addressCoach = new Address();
		addressCoach.setCity("Paris");
		addressCoach.setStreetName("rue de la Soif");
		addressCoach.setStreetNumber("2");
		addressCoach.setPostalCode("75005");

		Contact contactCoach = new Contact();
		contactCoach.setPhone("987654321");
		contactCoach.setEmail("alice.smith@example.com");

		profileCoach.setAddress(addressCoach);
		profileCoach.setContact(contactCoach);

		ProfessionalDetails coachDetails = new ProfessionalDetails();
		coachDetails.setCoachCertification("Certified Coach");
		coachDetails.setCoachForm("Advanced Coaching");
		coachDetails.setCoachCV("Coach Resume");

		profileCoach.setProfesionalDetails(coachDetails);

		coach.setProfile(profileCoach);

		accountDao.createAccount(coach);

		ActivityViewModel activity = new ActivityViewModel();
		activity.setName("judo");
		activity.setDescription("cours de judo avec les meilleurs coachs de la region");
		ActivityCategory activityCategory = ActivityCategory.CARDIO;
		activity.setActivityCategory(activityCategory);
//            activityDao.createActivity(activity, null);

		Course course = new Course();

		course.setStartDate(LocalDateTime.now());
		course.setEndDate(LocalDateTime.now());
		course.setNbPlaces(10);
		courseDao.saveCourse(course);

		EquipmentViewModel equipment = new EquipmentViewModel();
		equipment.setCondition("bonne");
		equipment.setQuantity(10);
		equipment.setEquipmentName("barre de traction");
		equipment.setDetails("barre de traction montée sur support métallique, poids max de 120kg");
//    		equipmentDao.createEquipment(equipment, null);

		// incomplet
		SpaceViewModel spaceViewModel = new SpaceViewModel();
		spaceViewModel.setFirstColor("845EC2");
		spaceViewModel.setSecondColor("00C9A7");
		spaceViewModel.setThirdColor("131B23");
		// Ici le chemin n'est autre que le nom du fichier car
		// le chamin est relatif en plus est géré à l'upload par JSF
		spaceViewModel.setGymLogoPath("goGym_logo.png");
		spaceViewModel.setGymBannerPath("banner_2.png");
		spaceViewModel.setMotto("Ensemble, en route vers le sport !");
		spaceViewModel.setSpaceName("Go Gym");
		spaceViewModel.setDescription(
				"Notre salle, idéalement située au coeur de Melun, vous permettra d'accéder à de nombreux cours pour tous les goûts et toutes les envies. Nos équipements dernier cri satisferont les sportifs les plus exigeants.");
		spaceViewModel.setFreeAccess(true);
		spaceViewModel.setLockerRoom(true);

		// AdminInfoGym
		spaceViewModel.setSiret("50073189800192");

		Address addressGym = new Address();
		addressGym.setStreetNumber("30");
		addressGym.setStreetName("rue Pierre Leroux");
		addressGym.setPostalCode("75007");
		addressGym.setCity("Paris");
		spaceViewModel.setLocation(addressGym);

		Contact contactGym = new Contact();
		contactGym.setPhone("01 41 50 06 53");
		contactGym.setEmail("contact@gogym.com");
		contactGym.setType(TypeContact.PRO);
		spaceViewModel.setContactInfo(contactGym);

		spaceDao.createSpace(spaceViewModel);

		Membership membership = new Membership();
		membership.setName("Abonnement Gold");
		membership.setPrice(99.99);
		membership.setNbrOfActivities(10);
		membership.setDescription("10 activités par jour, pour les plus grands sportfis");

		membershipDao.saveMembership(membership);

		Subscription subscription = new Subscription();
		subscription.setStartDate(LocalDateTime.now());
		subscription.setEndDate(LocalDateTime.now().plusMonths(6));
		subscription.setAutoRenewal(true);
		subscription.setDuration(6);
		subscriptionDao.saveSubscription(subscription);

	}
}