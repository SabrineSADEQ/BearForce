package initializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.ActivityDao;
import fr.isika.cda.javaee.dao.CourseDAO;
import fr.isika.cda.javaee.dao.EquipmentDao;
import fr.isika.cda.javaee.dao.PackDao;
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
import fr.isika.cda.javaee.entity.gymspace.IdGym;
import fr.isika.cda.javaee.entity.gymspace.Membership;
import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.ActivityCategory;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.entity.gymspace.business.Equipment;
import fr.isika.cda.javaee.entity.platform.Pack;
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

	@Inject
	PackDao packDao;

	@PostConstruct
	public void initialize() {
		createDefaultUser();
	}

	private void createDefaultUser() {
		// Check if the default user already exists

		AccountViewModel defaultUser = new AccountViewModel();
		defaultUser.setEmail("superAdmin@gmail.com");
		defaultUser.setPassword("superAdmin");
		defaultUser.setRole(Role.SUPER_ADMIN);
		accountDao.createAccount(defaultUser);
		AccountViewModel Adherent = new AccountViewModel();
		Adherent.setEmail("adherent@gmail.com");
		Adherent.setPassword("adherent");
		Adherent.setGymId((long) 46);
		Adherent.setRole(Role.ADHERENT);
		Adherent.setGymId((long) 46);
		Profile profileAdherent = new Profile();
		profileAdherent.setFirstName("Yoann");
		profileAdherent.setLastName("François");
		Address addressAdherent = new Address();
		addressAdherent.setCity("Paris");
		addressAdherent.setStreetNumber("1");
		addressAdherent.setStreetName("avenue des Champs-Elysées");
		addressAdherent.setPostalCode("75008");
		Contact contact = new Contact();
		contact.setPhone("01 22 12 32 11");
		profileAdherent.setPictureUrl("bear_user_image.jpg");
		profileAdherent.setAddress(addressAdherent);
		profileAdherent.setContact(contact);
		Adherent.setProfile(profileAdherent);
		accountDao.createAccount(Adherent);
		AccountViewModel gestionnaire = new AccountViewModel();
		gestionnaire.setEmail("gestionnaire@gmail.com");
		gestionnaire.setPassword("gestionnaire");
		gestionnaire.setRole(Role.GESTIONNAIRE);

		gestionnaire.setGymId((long) 46);

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
		contactGestionnaire.setPhone("06 23 45 67 89");
		contactGestionnaire.setEmail("jean.dupont@gmail.com");

		profileGestionnaire.setAddress(addressGestionnaire);
		profileGestionnaire.setContact(contactGestionnaire);

		gestionnaire.setProfile(profileGestionnaire);

		accountDao.createAccount(gestionnaire);
		AccountViewModel coach = new AccountViewModel();
		coach.setEmail("coach@gmail.com");
		coach.setPassword("coach");
		coach.setRole(Role.COACH);
		coach.setGymId((long) 46);
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
		contactCoach.setPhone("08 76 54 32 19");
		contactCoach.setEmail("alice.smith@example.com");

		profileCoach.setAddress(addressCoach);
		profileCoach.setContact(contactCoach);

		ProfessionalDetails coachDetails = new ProfessionalDetails();
		coachDetails.setCoachCertification("Certifiée en Zumba, 4ème niveau et en Yoga");
		coachDetails.setCoachForm("Advanced Coaching");
		coachDetails.setCoachCV(
				"Coach sportive passionnée et polyvalente, spécialisée dans l'enseignement du yoga et de la zumba, avec une expertise éprouvée dans la création de programmes holistiques favorisant le bien-être physique et mental.");

		profileCoach.setProfesionalDetails(coachDetails);

		coach.setProfile(profileCoach);

		Account savedAccount = accountDao.createAccount(coach);

		/*
		 * Prépa données cours par défaut + activité + coach
		 */
		Activity activity = new Activity();
		activity.setName("Cardio vélo");
		activity.setDescription("cours de judo avec les meilleurs coachs de la region");
		activity.setActivityCategory(ActivityCategory.CARDIO);
		activityDao.persist(activity);
		
		Course course = new Course();
		course.setActivity(activity);
		course.setTrainer(savedAccount.getProfile());
		course.setStartDate(LocalDateTime.now());
		course.setEndDate(LocalDateTime.now().plusHours(2));
		course.setNbPlaces(10);
		courseDao.saveCourse(course);
		
		/*
		 * Fin données cours
		 */

		EquipmentViewModel equipment = new EquipmentViewModel();
		equipment.setCondition("bonne");
		equipment.setQuantity(10);
		equipment.setEquipmentName("barre de traction");
		equipment.setDetails("barre de traction montée sur support métallique, poids max de 120kg");
    		//equipmentDao.createEquipment(equipment, null);

		// Packs for the BearForce platform
		Pack basicPack = new Pack();
		basicPack.setName("Ourson");
		basicPack.setPrice(99);
		packDao.createPack(basicPack);

		Pack premiumPack = new Pack();
		premiumPack.setName("Grizzly");
		premiumPack.setPrice(199);
		packDao.createPack(premiumPack);

		// Memberships for the example spaces
		Membership membership = new Membership();
		membership.setName("Gold");
		membership.setPrice(99);
		membership.setNbrOfActivities(20);
		membership.setDescription(
				"Jusqu'à 20 cours par semaine pour les plus grands sportifs, encadrement personnalisé par le coach de votre choix et accès illimité à l'espace détente");
		membershipDao.saveMembership(membership);

		Membership membership2 = new Membership();
		membership2.setName("Bronze");
		membership2.setPrice(20);
		membership2.setNbrOfActivities(2);
		membership2.setDescription(
				"2 cours par semaine, pour une remise en forme en douceur, un accès à l'espace détente par semaine");
		membershipDao.saveMembership(membership2);

		Membership membership3 = new Membership();
		membership3.setName("Silver");
		membership3.setPrice(45);
		membership3.setNbrOfActivities(7);
		membership3.setDescription("7 cours par semaine, accès illimité aux équipements en accès libre");
		membershipDao.saveMembership(membership3);

		List<Membership> membershipList = new ArrayList<Membership>();
		membershipList.add(membership);
		membershipList.add(membership2);
		membershipList.add(membership3);
		
		IdGym idGym = new IdGym();
		idGym.setMemberships(membershipList);
		
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
				"Notre salle, idéalement située au coeur de Paris, vous permettra d'accéder à de nombreux cours pour tous les goûts et toutes les envies. Nos équipements dernier cri satisferont les sportifs les plus exigeants.");
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
		
		spaceViewModel.setIdGym(idGym);

		spaceDao.createSpace(spaceViewModel);

		Subscription subscription = new Subscription();
		subscription.setStartDate(LocalDateTime.now());
		subscription.setEndDate(LocalDateTime.now().plusMonths(6));
		subscription.setAutoRenewal(true);
		subscription.setDuration(6);
		subscription.setMembership(membership);
		subscriptionDao.saveSubscription(subscription);


		AccountViewModel Adherent2 = new AccountViewModel();
		Adherent2.setEmail("leroymerlin@gmail.com");
		Adherent2.setPassword("adherent2");
		Adherent2.setGymId((long) 95);
		Adherent2.setRole(Role.ADHERENT);
		Profile profileAdherent2 = new Profile();
		profileAdherent2.setFirstName("Quentin");
		profileAdherent2.setLastName("Garcia");
		Contact contact2 = new Contact();
		contact2.setPhone("06 99 85 12 35");
		profileAdherent2.setContact(contact2);
		Adherent2.setProfile(profileAdherent2);
		accountDao.createAccount(Adherent2);

		AccountViewModel Adherent3 = new AccountViewModel();
		Adherent3.setEmail("adherent3@gmail.com");
		Adherent3.setPassword("adherent3");

		Adherent3.setGymId((long) 95);

		Adherent3.setRole(Role.ADHERENT);
		Profile profileAdherent3 = new Profile();
		profileAdherent3.setFirstName("Theo");
		profileAdherent3.setLastName("Hamad");
		Contact contact3 = new Contact();
		contact3.setPhone("06 55 48 79 45");
		profileAdherent3.setContact(contact3);
		Adherent3.setProfile(profileAdherent3);
		accountDao.createAccount(Adherent3);
		
		AccountViewModel anotherAdherent = new AccountViewModel();
		anotherAdherent.setEmail("alice@example.com");
		anotherAdherent.setPassword("securePassword123");
		anotherAdherent.setGymId((long) 46);
		anotherAdherent.setRole(Role.ADHERENT);
		anotherAdherent.setGymId((long) 46);

		Profile anotherProfileAdherent = new Profile();
		anotherProfileAdherent.setFirstName("Alice");
		anotherProfileAdherent.setLastName("Smith");

		Address anotherAddressAdherent = new Address();
		anotherAddressAdherent.setCity("macon");
		anotherAddressAdherent.setStreetNumber("123");
		anotherAddressAdherent.setStreetName("jeanne d'arc");
		anotherAddressAdherent.setPostalCode("10001");

		Contact anotherContact = new Contact();
		anotherContact.setPhone("06 59 63 54 87");
		anotherProfileAdherent.setPictureUrl("personne.png");
		anotherProfileAdherent.setAddress(anotherAddressAdherent);
		anotherProfileAdherent.setContact(anotherContact);

		anotherAdherent.setProfile(anotherProfileAdherent);	
		accountDao.createAccount(anotherAdherent);
		
		AccountViewModel newMember = new AccountViewModel();
		newMember.setEmail("emma@example.com");
		newMember.setPassword("strongPassword456");
		newMember.setGymId((long) 46);
		newMember.setRole(Role.ADHERENT);

		Profile newMemberProfile = new Profile();
		newMemberProfile.setFirstName("Emma");
		newMemberProfile.setLastName("Johnson");

		Address newMemberAddress = new Address();
		newMemberAddress.setCity("daube");
		newMemberAddress.setStreetNumber("789");
		newMemberAddress.setStreetName("republique");
		newMemberAddress.setPostalCode("90001");

		Contact newMemberContact = new Contact();
		newMemberContact.setPhone("01 54 87 95 31");
		newMemberProfile.setPictureUrl("personne.png");
		newMemberProfile.setAddress(newMemberAddress);
		newMemberProfile.setContact(newMemberContact);

		newMember.setProfile(newMemberProfile);
		accountDao.createAccount(newMember);
		
		AccountViewModel guestUser = new AccountViewModel();
		guestUser.setEmail("alex@example.com");
		guestUser.setPassword("temporaryPass789");
		guestUser.setGymId((long) 46);
		guestUser.setRole(Role.ADHERENT);

		Profile guestProfile = new Profile();
		guestProfile.setFirstName("Alex");
		guestProfile.setLastName("Miller");

		Address guestAddress = new Address();
		guestAddress.setCity("fosses");
		guestAddress.setStreetNumber("456");
		guestAddress.setStreetName("leonard de vinci");
		guestAddress.setPostalCode("94105");

		Contact guestContact = new Contact();
		guestContact.setPhone("04 59 78 45 11");
		guestProfile.setPictureUrl("personne.png");
		guestProfile.setAddress(guestAddress);
		guestProfile.setContact(guestContact);

		guestUser.setProfile(guestProfile);
		accountDao.createAccount(guestUser);
		
		AccountViewModel premiumMember = new AccountViewModel();
		premiumMember.setEmail("sophia@example.com");
		premiumMember.setPassword("strongPass789");
		premiumMember.setGymId((long) 46);
		premiumMember.setRole(Role.COACH);

		Profile premiumMemberProfile = new Profile();
		premiumMemberProfile.setFirstName("Sophia");
		premiumMemberProfile.setLastName("Williams");

		Address premiumMemberAddress = new Address();
		premiumMemberAddress.setCity("versaille");
		premiumMemberAddress.setStreetNumber("789");
		premiumMemberAddress.setStreetName("rue du roi");
		premiumMemberAddress.setPostalCode("33139");

		Contact premiumMemberContact = new Contact();
		premiumMemberContact.setPhone("02 23 54 87 45");
		premiumMemberProfile.setPictureUrl("personne.png");
		premiumMemberProfile.setAddress(premiumMemberAddress);
		premiumMemberProfile.setContact(premiumMemberContact);

		premiumMember.setProfile(premiumMemberProfile);
		accountDao.createAccount(premiumMember);

		// Create an Activity
		Activity newActivity = new Activity();
		newActivity.setName("Yoga");
		newActivity.setDescription("Venez vous relaxer avec nous");
		newActivity.setActivityCategory(ActivityCategory.FITNESS);
		activityDao.persist(newActivity);

		// Create a Course for the Activity
		Course yogaCourse = new Course();
		yogaCourse.setActivity(newActivity);
		yogaCourse.setTrainer(savedAccount.getProfile()); // Assuming savedAccount is the instructor's account
		yogaCourse.setStartDate(LocalDateTime.now().plusDays(1)); // Start the course tomorrow
		yogaCourse.setEndDate(LocalDateTime.now().plusDays(1).plusHours(1)); // Duration: 1 hour
		yogaCourse.setNbPlaces(15); // Maximum number of participants
		courseDao.saveCourse(yogaCourse);
		
		// Create an Activity
		Activity newActivity2 = new Activity();
		newActivity2.setName("HIIT Training");
		newActivity2.setDescription("Du cardio venu des states");
		newActivity2.setActivityCategory(ActivityCategory.CARDIO);
		activityDao.persist(newActivity2);

		// Create a Course for the Activity
		Course hiitCourse = new Course();
		hiitCourse.setActivity(newActivity2);
		hiitCourse.setTrainer(savedAccount.getProfile()); // Assuming savedAccount is the instructor's account
		hiitCourse.setStartDate(LocalDateTime.now().plusDays(2)); // Start the course in two days
		hiitCourse.setEndDate(LocalDateTime.now().plusDays(2).plusHours(1)); // Duration: 1 hour
		hiitCourse.setNbPlaces(20); // Maximum number of participants
		courseDao.saveCourse(hiitCourse);
		
		
		SpaceViewModel newSpace = new SpaceViewModel();
		newSpace.setFirstColor("FF5733");
		newSpace.setSecondColor("33FF57");
		newSpace.setThirdColor("334BFF");
		newSpace.setGymLogoPath("logo2gym.png");
		newSpace.setGymBannerPath("banner_3.png");
		newSpace.setMotto("No pain, no gain");
		newSpace.setSpaceName("Forklift");
		newSpace.setDescription(
		        "Plus qu'une salle de sport, une salle ou les corps s'activent");
		newSpace.setFreeAccess(false); // No free access
		newSpace.setLockerRoom(true);

		// AdminInfoGym
		newSpace.setSiret("12345678901234");

		Address addressGym2 = new Address();
		addressGym2.setStreetNumber("45");
		addressGym2.setStreetName("Avenue des Sports");
		addressGym2.setPostalCode("75001");
		addressGym2.setCity("Aix-en-provence");
		newSpace.setLocation(addressGym2);

		Contact contactGym2 = new Contact();
		contactGym2.setPhone("01 23 45 67 89");
		contactGym2.setEmail("info@fithub.com");
		contactGym2.setType(TypeContact.PERSO);
		newSpace.setContactInfo(contactGym2);

		

		spaceDao.createSpace(newSpace);
		
		SpaceViewModel fitnessCenter = new SpaceViewModel();
		fitnessCenter.setFirstColor("4285F4");
		fitnessCenter.setSecondColor("DB4437");
		fitnessCenter.setThirdColor("F4B400");
		fitnessCenter.setGymLogoPath("fitness_logo.png");
		fitnessCenter.setGymBannerPath("banner_fitness.jpg");
		fitnessCenter.setMotto("Toujours plus forts");
		fitnessCenter.setSpaceName("FitnessBonhomme");
		fitnessCenter.setDescription(
		        "Ici, c'est jusqu'à plus envie");
		fitnessCenter.setFreeAccess(false);
		fitnessCenter.setLockerRoom(true);

		// AdminInfoGym
		fitnessCenter.setSiret("98765432101234");

		Address gymAddress = new Address();
		gymAddress.setStreetNumber("123");
		gymAddress.setStreetName("Main Street");
		gymAddress.setPostalCode("90210");
		gymAddress.setCity("Potier");
		fitnessCenter.setLocation(gymAddress);

		Contact gymContact = new Contact();
		gymContact.setPhone("555-7890");
		gymContact.setEmail("info@epicfitness.com");
		gymContact.setType(TypeContact.PERSO);
		fitnessCenter.setContactInfo(gymContact);

		spaceDao.createSpace(fitnessCenter);



	}


}