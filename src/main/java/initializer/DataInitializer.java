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
import fr.isika.cda.javaee.dao.accounts.ProfilesManagementDao;
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
		Adherent.setGymId((long) 60);
		Adherent.setRole(Role.ADHERENT);
		Adherent.setGymId((long) 60);
		Profile profileAdherent = new Profile();
		profileAdherent.setFirstName("Yann");
		profileAdherent.setLastName("Foisant");
		Address addressAdherent = new Address();
		addressAdherent.setCity("Paris");
		addressAdherent.setStreetNumber("1");
		addressAdherent.setStreetName("avenue des Champs-Elysées");
		addressAdherent.setPostalCode("75008");
		Contact contact = new Contact();
		contact.setPhone("01 22 12 32 11");
		profileAdherent.setPictureUrl("profile_pic5.png");
		profileAdherent.setAddress(addressAdherent);
		profileAdherent.setContact(contact);
		Adherent.setProfile(profileAdherent);
		accountDao.createAccount(Adherent);
		AccountViewModel gestionnaire = new AccountViewModel();
		gestionnaire.setEmail("gestionnaire@gmail.com");
		gestionnaire.setPassword("gestionnaire");
		gestionnaire.setRole(Role.GESTIONNAIRE);

		gestionnaire.setGymId((long) 60);

		Profile profileGestionnaire = new Profile();
		profileGestionnaire.setFirstName("Jean");
		profileGestionnaire.setLastName("Dupont");
		profileGestionnaire.setPictureUrl("profile_pic4.jpg");

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
		coach.setGymId((long) 60);
		Goal goal2 = new Goal();
		goal2.setGoalName("Perte de poids");
		goal2.setGoalDate(LocalDate.of(2024, Month.FEBRUARY, 28));

		coach.setGoal(goal2);

		Profile profileCoach = new Profile();
		profileCoach.setFirstName("Alice");
		profileCoach.setLastName("Smith");
		profileCoach.setPictureUrl("profile_pic1.jpg");

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
		coachDetails.setCoachCertification("Certificat d'Instructrice de Natation, membre des Dauphins de Toulouse OEC arrivé en première position au classement de la saison 2023-2024.");
		coachDetails.setCoachForm("Licence de Coaching avancé");
		coachDetails.setCoachCV("Découvrez le plaisir de nager avec mes cours personnalisés ! De l'apprentissage des bases à la maîtrise des techniques avancées, plongez dans une aventure aquatique. Nageons vers le succès ensemble !");
		profileCoach.setProfesionalDetails(coachDetails);

		coach.setProfile(profileCoach);

		Account savedAccount = accountDao.createAccount(coach);

		/*
		 * Do not delete this
		 */
		createFakeProfiles();

		/*
		 * Prépa données cours par défaut + activité + coach
		 */
		Activity activity = new Activity();
		activity.setName("Cardio vélo");
		activity.setDescription("Pédalez au rythme de musiques entraînantes, brûlez des calories, renforcez vos jambes et améliorez votre condition physique. Rejoignez-nous pour une séance cardio pleine d'énergie et de résultats !");
		activity.setAttachedGymId(Long.valueOf(60));
		activity.setActivityCategory(ActivityCategory.CARDIO);
		activity.setActivityPicturePath("Cardio_velo.png");
		activityDao.persist(activity);
		
//		Course course = new Course();
//		course.setActivity(activity);
//		course.setTrainer(savedAccount.getProfile());
//		course.setStartDate(LocalDateTime.now());
//		course.setEndDate(LocalDateTime.now().plusHours(2));
//		course.setNbPlaces(10);
//		courseDao.saveCourse(course);

		/*
		 * Fin données cours
		 */

		EquipmentViewModel equipment = new EquipmentViewModel();
		equipment.setEquipmentPicturePath(null);
		equipment.setQuantity(10);
		equipment.setEquipmentName("barre de traction");
		equipment.setDetails("Barre de traction montée sur support métallique, poids max de 120kg");
		// equipmentDao.createEquipment(equipment, null);

		// Packs for the BearForce platform
		Pack basicPack = new Pack();
		basicPack.setName("Ourson");
		basicPack.setPrice(99);
		packDao.deletePack(basicPack);

		Pack premiumPack = new Pack();
		premiumPack.setName("Grizzly");
		premiumPack.setPrice(199);
		packDao.deletePack(premiumPack);

		Pack basicPack1 = new Pack();
		basicPack1.setName("Basic");
		basicPack1.setPrice(200);
		basicPack1.setDescription(
				"Offre une solution économique, idéale pour les petites salles de sport , en facilitant la gestion des membres, le suivi des paiements, et en fournissant des rapports de base pour une gestion transparente.");
		packDao.createPack(basicPack1);

		Pack avancePack = new Pack();
		avancePack.setName("Avancé");
		avancePack.setPrice(250);
		avancePack.setDescription(
				"Le package Avancé élève votre salle de sport en offrant, au-delà des fonctionnalités essentielles, la planification de cours, la gestion des réservations, et une analyse approfondie des performances.");
		packDao.createPack(avancePack);

		Pack premiumPack1 = new Pack();
		premiumPack1.setName("Premium");
		premiumPack1.setPrice(400);
		premiumPack1.setDescription(
				"Le package Premium offre une expérience exceptionnelle avec des fonctionnalités avancées, un support prioritaire, idéal pour les salles de sport visant l'excellence et la satisfaction maximale des membres");
		packDao.createPack(premiumPack1);

		// Memberships for the example spaces

		Membership membership2 = new Membership();
		membership2.setName("Bronze");
		membership2.setPrice(19.99);
		membership2.setNbrOfActivities(2);
		membership2.setDescription("2 cours par semaine, pour une remise en forme en douceur, "
				+ "un accès à l'espace détente par semaine");
		membershipDao.saveMembership(membership2);

		Membership membership3 = new Membership();
		membership3.setName("Silver");
		membership3.setPrice(45.99);
		membership3.setNbrOfActivities(7);
		membership3.setDescription("7 cours par semaine dont un cours particulier, accès illimité aux équipements en accès libre");

		membershipDao.saveMembership(membership3);
		Membership membership = new Membership();
		membership.setName("Gold");
		membership.setPrice(99.99);
		membership.setNbrOfActivities(20);
		membership.setDescription("Jusqu'à 20 cours par semaine,"
				+ " encadrement personnalisé par un coach et accès illimité à l'espace détente");
		membershipDao.saveMembership(membership);

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
		spaceViewModel.setGymBannerPath("banner_2_3.png");
		spaceViewModel.setMotto("Ensemble, en route vers le sport !");
		spaceViewModel.setSpaceName("Go Gym");
		spaceViewModel.setDescription("Notre salle, idéalement située au coeur de Paris,"
				+ " vous permettra d'accéder à de nombreux cours pour tous les goûts et toutes les envies."
				+ " Nos équipements dernier cri satisferont les sportifs les plus exigeants. Nos coachs de haut niveau vous aideront à atteindre vos objectifs.");
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
		subscriptionDao.CreateSubscription(subscription);

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
		anotherAdherent.setGymId((long) 60);
		anotherAdherent.setRole(Role.ADHERENT);
		anotherAdherent.setGymId((long) 60);

		Profile anotherProfileAdherent = new Profile();
		anotherProfileAdherent.setFirstName("Alice");
		anotherProfileAdherent.setLastName("Smith");

		Address anotherAddressAdherent = new Address();
		anotherAddressAdherent.setCity("Macon");
		anotherAddressAdherent.setStreetNumber("123");
		anotherAddressAdherent.setStreetName("Jeanne d'arc");
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
		newMember.setGymId((long) 60);
		newMember.setRole(Role.ADHERENT);

		Profile newMemberProfile = new Profile();
		newMemberProfile.setFirstName("Emma");
		newMemberProfile.setLastName("Johnson");

		Address newMemberAddress = new Address();
		newMemberAddress.setCity("Daube");
		newMemberAddress.setStreetNumber("789");
		newMemberAddress.setStreetName("Republique");
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
		guestUser.setGymId((long) 60);
		guestUser.setRole(Role.ADHERENT);

		Profile guestProfile = new Profile();
		guestProfile.setFirstName("Alex");
		guestProfile.setLastName("Miller");

		Address guestAddress = new Address();
		guestAddress.setCity("Fosses");
		guestAddress.setStreetNumber("456");
		guestAddress.setStreetName("Leonard de vinci");
		guestAddress.setPostalCode("94105");

		Contact guestContact = new Contact();
		guestContact.setPhone("04 59 78 45 11");
		guestProfile.setPictureUrl("profile_pic3.jpg");
		guestProfile.setAddress(guestAddress);
		guestProfile.setContact(guestContact);

		guestUser.setProfile(guestProfile);
		accountDao.createAccount(guestUser);

		AccountViewModel premiumMember = new AccountViewModel();
		premiumMember.setEmail("sophia@example.com");
		premiumMember.setPassword("strongPass789");
		premiumMember.setGymId((long) 60);
		premiumMember.setRole(Role.ADHERENT);

		Profile premiumMemberProfile = new Profile();
		premiumMemberProfile.setFirstName("Sophia");
		premiumMemberProfile.setLastName("Williams");

		Address premiumMemberAddress = new Address();
		premiumMemberAddress.setCity("Versailles");
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
//		Course yogaCourse = new Course();
//		yogaCourse.setActivity(newActivity);
//		yogaCourse.setTrainer(savedAccount.getProfile()); // Assuming savedAccount is the instructor's account
//		yogaCourse.setStartDate(LocalDateTime.now().plusDays(1)); // Start the course tomorrow
//		yogaCourse.setEndDate(LocalDateTime.now().plusDays(1).plusHours(1)); // Duration: 1 hour
//		yogaCourse.setNbPlaces(15); // Maximum number of participants
//		courseDao.saveCourse(yogaCourse);

		// Create an Activity
		Activity newActivity2 = new Activity();
		newActivity2.setName("HIIT Training");
		newActivity2.setDescription("Du cardio venu des states");
		newActivity2.setActivityCategory(ActivityCategory.CARDIO);
		activityDao.persist(newActivity2);

		// Create a Course for the Activity
		Course hiitCourse = new Course();
//		hiitCourse.setActivity(newActivity2);
//		hiitCourse.setTrainer(savedAccount.getProfile()); // Assuming savedAccount is the instructor's account
//		hiitCourse.setStartDate(LocalDateTime.now().plusDays(2)); // Start the course in two days
//		hiitCourse.setEndDate(LocalDateTime.now().plusDays(2).plusHours(1)); // Duration: 1 hour
//		hiitCourse.setNbPlaces(20); // Maximum number of participants
//		courseDao.saveCourse(hiitCourse);

		SpaceViewModel newSpace = new SpaceViewModel();
		newSpace.setFirstColor("FF5733");
		newSpace.setSecondColor("33FF57");
		newSpace.setThirdColor("334BFF");
		newSpace.setGymLogoPath("logo2gym.png");
		newSpace.setGymBannerPath("banner_4.png");
		newSpace.setMotto("No pain, no gain");
		newSpace.setSpaceName("Forklift");
		newSpace.setDescription(
				"Entrez dans un monde de bien-être dans notre salle de sport moderne, où chaque séance d'entraînement devient une aventure."
						+ " Avec une variété de cours adaptés à tous les niveaux et des équipements de pointe, vous atteindrez vos objectifs "
						+ "tout en repoussant vos limites.");
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
		fitnessCenter.setFirstColor("F4B400");
		fitnessCenter.setSecondColor("DB4437");
		fitnessCenter.setThirdColor("191A2D");
		fitnessCenter.setGymLogoPath("fitFusion.png");
		fitnessCenter.setGymBannerPath("banner1.jpg");
		fitnessCenter.setMotto("Toujours plus forts");
		fitnessCenter.setSpaceName("FitFusion");
		fitnessCenter.setDescription(
				"Rejoignez notre communauté de passionnés de fitness dans notre salle de sport. "
						+ "Des cours captivants, des équipements de pointe et une atmosphère motivante font de notre espace "
						+ "le choix parfait pour quiconque cherche à élever son niveau de forme physique en suivant des cours variés.");
		fitnessCenter.setFreeAccess(false);
		fitnessCenter.setLockerRoom(true);

		// AdminInfoGym
		fitnessCenter.setSiret("98765432101234");

		Address gymAddress = new Address();
		gymAddress.setStreetNumber("123");
		gymAddress.setStreetName("Main Street");
		gymAddress.setPostalCode("90210");
		gymAddress.setCity("Mulhouse");
		fitnessCenter.setLocation(gymAddress);

		Contact gymContact = new Contact();
		gymContact.setPhone("555-7890");
		gymContact.setEmail("info@epicfitness.com");
		gymContact.setType(TypeContact.PERSO);
		fitnessCenter.setContactInfo(gymContact);

		spaceDao.createSpace(fitnessCenter);
		
		//REMPLISSAGE BDD POUR DEMO YOANN
		
		//ACTIVITES
		Activity activityTwo = new Activity();
		activityTwo.setName("Yoga apaisant");
		activityTwo.setDescription("Détendez-vous et équilibrez votre esprit avec une séance de yoga apaisante. Idéal pour libérer le stress et améliorer la flexibilité.");
		activityTwo.setAttachedGymId(Long.valueOf(60));
		activityTwo.setActivityCategory(ActivityCategory.RELAXATION);
		activityTwo.setActivityPicturePath("Yoga_apaisant.png");
		activityDao.persist(activityTwo);
		
		Activity activityThree = new Activity();
		activityThree.setName("Crossfit");
		activityThree.setDescription("Explorez le CrossFit, le secret d'une forme physique ultime. Des séances dynamiques, des résultats rapides. Joignez-vous à une communauté passionnée, brisez vos limites, devenez votre meilleure version. La révolution fitness commence ici.");
		activityThree.setAttachedGymId(Long.valueOf(60));
		activityThree.setActivityCategory(ActivityCategory.FORCE);
		activityThree.setActivityPicturePath("Crossfit.png");
		activityDao.persist(activityThree);
		
		Activity activityFour = new Activity();
		activityFour.setName("Aquagym dynamique");
		activityFour.setDescription("Plongez dans une séance d'aquagym énergisante. L'eau ajoute une résistance naturelle pour tonifier vos muscles sans impact sur les articulations.");
		activityFour.setAttachedGymId(Long.valueOf(60));
		activityFour.setActivityCategory(ActivityCategory.NATATION);
		activityFour.setActivityPicturePath("Aquagym_dynamique.png");
		activityDao.persist(activityFour);
		
		Activity activityFive = new Activity();
		activityFive.setName("Pilates pour le renforcement du noyau");
		activityFive.setDescription("Renforcez votre noyau avec des exercices de Pilates ciblés. Améliorez la posture, la stabilité et la force musculaire.");
		activityFive.setAttachedGymId(Long.valueOf(60));
		activityFive.setActivityCategory(ActivityCategory.FORCE);
		activityFive.setActivityPicturePath("Pilates.png");
		activityDao.persist(activityFive);
		
		Activity activitySix = new Activity();
		activitySix.setName("Zumba Party");
		activitySix.setDescription("Dansez au rythme de musiques entraînantes dans une ambiance de fête. Brûlez des calories tout en vous amusant avec des mouvements de danse latine.");
		activitySix.setAttachedGymId(Long.valueOf(60));
		activitySix.setActivityCategory(ActivityCategory.DANCE);
		activitySix.setActivityPicturePath("Zumba.png");
		activityDao.persist(activitySix);
		
		Activity activitySeven = new Activity();
		activitySeven.setName("Entraînement de boxe");
		activitySeven.setDescription("Libérez votre énergie avec un entraînement de boxe dynamique. Améliorez la coordination, la force et la résistance.");
		activitySeven.setAttachedGymId(Long.valueOf(60));
		activitySeven.setActivityCategory(ActivityCategory.COMBAT);
		activitySeven.setActivityPicturePath("Box.png");
		activityDao.persist(activitySeven);
		
		Activity activityEight = new Activity();
		activityEight.setName("Entraînement de musculation avancé");
		activityEight.setDescription("Développez votre force et votre masse musculaire avec un programme avancé d'entraînement en musculation. Encadrement professionnel inclus.");
		activityEight.setAttachedGymId(Long.valueOf(60));
		activityEight.setActivityCategory(ActivityCategory.FORCE);
		activityEight.setActivityPicturePath("Musculation.png");
		activityDao.persist(activityEight);
		
		Activity activityNine = new Activity();
		activityNine.setName("Danse contemporaine créative");
		activityNine.setDescription("Exprimez-vous à travers la danse contemporaine. Libérez votre créativité et améliorez votre agilité.");
		activityNine.setAttachedGymId(Long.valueOf(60));
		activityNine.setActivityCategory(ActivityCategory.DANCE);
		activityNine.setActivityPicturePath("Danse_contemporaine.png");
		activityDao.persist(activityNine);
		
		Activity activityTen = new Activity();
		activityTen.setName("Entraînement TRX");
		activityTen.setDescription("Explorez de nouvelles dimensions de fitness avec le TRX Pro Suspension Trainer. Pour des séances d'entraînement dynamiques qui dépassent toutes vos attentes.");
		activityTen.setAttachedGymId(Long.valueOf(60));
		activityTen.setActivityCategory(ActivityCategory.FITNESS);
		activityTen.setActivityPicturePath("TRX.png");
		activityDao.persist(activityTen);
		
		//EQUIPMENTS
		Equipment equipmentOne = new Equipment();
		equipmentOne.setEquipmentName("Vélo de cardio");
		equipmentOne.setEquipmentPicturePath("Velo_d_interieur.png");
		equipmentOne.setAttachedGymId(Long.valueOf(60));
		equipmentOne.setQuantity(12);
		equipmentOne.setDetails("Offrez vous une expérience de cyclisme premium pour des résultats rapides. Cardio élégant, musculation efficace. La nouvelle norme du fitness.");
		equipmentOne.setActivity(activity);
		equipmentDao.persist(equipmentOne);
		
		Equipment equipmentTwo = new Equipment();
		equipmentTwo.setEquipmentName("Tapis de yoga");
		equipmentTwo.setEquipmentPicturePath("Tapis_yoga.png");
		equipmentTwo.setAttachedGymId(Long.valueOf(60));
		equipmentTwo.setQuantity(10);
		equipmentTwo.setDetails("Offrez-vous l'expérience ultime du yoga avec notre tapis premium. Transformez chaque séance en un voyage de bien-être et d'équilibre.");
		equipmentTwo.setActivity(activityTwo);
		equipmentDao.persist(equipmentTwo);
		
		Equipment equipmentThreeA = new Equipment();
		equipmentThreeA.setEquipmentName("Poids");
		equipmentThreeA.setEquipmentPicturePath("Poids.png");
		equipmentThreeA.setAttachedGymId(Long.valueOf(60));
		equipmentThreeA.setQuantity(30);
		equipmentThreeA.setDetails("Découvrez l'outil essentiel pour propulser vos performances au CrossFit ! Notre poids de 5 kg, parfaitement conçu, est bien plus qu'un simple accessoire d'entraînement.");
		equipmentThreeA.setActivity(activityThree);
		equipmentDao.persist(equipmentThreeA);
		
		Equipment equipmentThreeB = new Equipment();
		equipmentThreeB.setEquipmentName("Box jump");
		equipmentThreeB.setEquipmentPicturePath("Box_jump.png");
		equipmentThreeB.setAttachedGymId(Long.valueOf(60));
		equipmentThreeB.setQuantity(15);
		equipmentThreeB.setDetails("Une boîte robuste pour sauter, renforçant les jambes et améliorant l'explosivité.");
		equipmentThreeB.setActivity(activityThree);
		equipmentDao.persist(equipmentThreeB);
		
		Equipment equipmentFour = new Equipment();
		equipmentFour.setEquipmentName("Poids d'aquagym");
		equipmentFour.setEquipmentPicturePath("Poids_aquagym.png");
		equipmentFour.setAttachedGymId(Long.valueOf(60));
		equipmentFour.setQuantity(40);
		equipmentFour.setDetails("Transformez votre entraînement aquatique avec style et efficacité. Nos poids Aquafit sont votre partenaire idéal pour sculpter et renforcer votre corps dans l'eau.");
		equipmentFour.setActivity(activityFour);
		equipmentDao.persist(equipmentFour);
		
		Equipment equipmentFive = new Equipment();
		equipmentFive.setEquipmentName("Swiss ball");
		equipmentFive.setEquipmentPicturePath("Ballon_pilates.png");
		equipmentFive.setAttachedGymId(Long.valueOf(60));
		equipmentFive.setQuantity(10);
		equipmentFive.setDetails("Transformez vos séances d'entraînement avec notre Swiss Ball Premium. Une fusion parfaite de forme, fonction et style pour des résultats exceptionnels.");
		equipmentFive.setActivity(activityFive);
		equipmentDao.persist(equipmentFive);
		
		Equipment equipmentSeven = new Equipment();
		equipmentSeven.setEquipmentName("Gants de box");
		equipmentSeven.setEquipmentPicturePath("Gants_box.png");
		equipmentSeven.setAttachedGymId(Long.valueOf(60));
		equipmentSeven.setQuantity(6);
		equipmentSeven.setDetails("Dominez chaque round avec assurance et style. Optez pour les gants de boxe ProFit pour une expérience de boxe incomparable dans notre salle de sport de renom.");
		equipmentSeven.setActivity(activitySeven);
		equipmentDao.persist(equipmentSeven);
		
		Equipment equipmentEightA = new Equipment();
		equipmentEightA.setEquipmentName("Banc de développé couché");
		equipmentEightA.setEquipmentPicturePath("Banc_developpe_couche.png");
		equipmentEightA.setAttachedGymId(Long.valueOf(60));
		equipmentEightA.setQuantity(3);
		equipmentEightA.setDetails("Transformez votre routine de musculation avec le Banc de Développé Couché UltimaForce. La fusion parfaite de confort, de robustesse et de performance pour des résultats qui se démarquent.");
		equipmentEightA.setActivity(activityEight);
		equipmentDao.persist(equipmentEightA);
		
		Equipment equipmentEightB = new Equipment();
		equipmentEightB.setEquipmentName("Barre à traction");
		equipmentEightB.setEquipmentPicturePath("Barre_traction.png");
		equipmentEightB.setAttachedGymId(Long.valueOf(60));
		equipmentEightB.setQuantity(2);
		equipmentEightB.setDetails("Révélez votre puissance avec la Barre de Traction ProFit-Xtreme. Pour des tractions sans compromis et des résultats qui repoussent les limites.");
		equipmentEightB.setActivity(activityEight);
		equipmentDao.persist(equipmentEightB);
		
		Equipment equipmentEightC = new Equipment();
		equipmentEightC.setEquipmentName("Set d'altères");
		equipmentEightC.setEquipmentPicturePath("Serie_poids.png");
		equipmentEightC.setAttachedGymId(Long.valueOf(60));
		equipmentEightC.setQuantity(2);
		equipmentEightC.setDetails("Redéfinissez vos limites avec les Altères PowerFlex Pro. Pour des muscles sculptés et une performance qui repousse constamment les frontières.");
		equipmentEightC.setActivity(activityEight);
		equipmentDao.persist(equipmentEightC);
		
		Equipment equipmentNine = new Equipment();
		equipmentNine.setEquipmentName("Barres de danse");
		equipmentNine.setEquipmentPicturePath("Barre_danse.png");
		equipmentNine.setAttachedGymId(Long.valueOf(60));
		equipmentNine.setQuantity(2);
		equipmentNine.setDetails("Révélez votre puissance avec la Barre de Traction ProFit-Xtreme. Pour des tractions sans compromis et des résultats qui repoussent les limites.");
		equipmentNine.setActivity(activityNine);
		equipmentDao.persist(equipmentNine);
		
		Equipment equipmentTen = new Equipment();
		equipmentTen.setEquipmentName("Set de sangles TRX");
		equipmentTen.setEquipmentPicturePath("Sangles_TRX.jpg");
		equipmentTen.setAttachedGymId(Long.valueOf(60));
		equipmentTen.setQuantity(13);
		equipmentTen.setDetails("xplorez de nouvelles dimensions de force, d'agilité et de performance avec le Set de Sangles de Suspension TRX Ultimate. Pour des résultats qui surpassent vos attentes.");
		equipmentTen.setActivity(activityTen);
		equipmentDao.persist(equipmentTen);
		
		//CREATION D'UN COACH
		AccountViewModel coachBeta = new AccountViewModel();
		coachBeta.setEmail("e.durand@gmail.com");
		coachBeta.setPassword("coach");
		coachBeta.setRole(Role.COACH);
		coachBeta.setGymId((long) 60);
		Goal goalBeta = new Goal();
		goalBeta.setGoalName("Perte de poids");
		goalBeta.setGoalDate(LocalDate.of(2024, Month.FEBRUARY, 28));

		coachBeta.setGoal(goalBeta);

		Profile profileCoachBeta = new Profile();
		profileCoachBeta.setFirstName("Eustache");
		profileCoachBeta.setLastName("Durand");
		profileCoachBeta.setPictureUrl("Eustache_Durand_box.jpg");

		Address addressCoachBeta = new Address();
		addressCoachBeta.setCity("Paris");
		addressCoachBeta.setStreetName("avenue du Général Leclerc");
		addressCoachBeta.setStreetNumber("34");
		addressCoachBeta.setPostalCode("75016");

		Contact contactCoachBeta = new Contact();
		contactCoachBeta.setPhone("06 32 98 91 44");
		contactCoachBeta.setEmail("e.durand@gmail.com");

		profileCoachBeta.setAddress(addressCoachBeta);
		profileCoachBeta.setContact(contactCoachBeta);

		ProfessionalDetails coachDetailsBeta = new ProfessionalDetails();
		coachDetailsBeta.setCoachCertification("Compétiteur professionnel de box, 1ère place au championnat de France elites 1 de janvier 2024, acrédité coach professionel par la FFB.");
		coachDetailsBeta.setCoachForm("Licence de Coaching avancé");
		coachDetailsBeta.setCoachCV("Révélez votre force intérieure avec mes cours de boxe sur mesure ! De la technique de base à la stratégie avancée. Prêt à devenir le champion de votre propre histoire ? Entraînons-nous ensemble vers la victoire !");
		profileCoachBeta.setProfesionalDetails(coachDetailsBeta);

		coachBeta.setProfile(profileCoachBeta);

		Account savedAccountBeta = accountDao.createAccount(coachBeta);
		
	}

	private void createFakeProfiles() {
		/*
		 * 1er profile fake
		 */
		Profile profileCoach2 = new Profile();
		profileCoach2.setFirstName("Rayan");
		profileCoach2.setLastName("Simon");
		profileCoach2.setPictureUrl("profil2.jpg");

		Address addressCoach2 = new Address();
		addressCoach2.setCity("Paris");
		addressCoach2.setStreetName("rue de Générale Leclerc");
		addressCoach2.setStreetNumber("4");
		addressCoach2.setPostalCode("75018");

		Contact contactCoach2 = new Contact();
		contactCoach2.setPhone("08 76 54 32 19");
		contactCoach2.setEmail("rayan1simon@example.com");

		profileCoach2.setAddress(addressCoach2);
		profileCoach2.setContact(contactCoach2);

		ProfessionalDetails coachDetails1 = new ProfessionalDetails();
		coachDetails1.setCoachCertification("Niveau avancé en Crossfit, première place au Lacanau Contest Winter Edition de 2023.");
		coachDetails1.setCoachForm("Advanced Coaching Intermediate Level");
		coachDetails1.setCoachCV(
				"Ensemble, nous repousserons vos limites, écrirons une nouvelle histoire de force et de persévérance. Prêt à embrasser le changement et à devenir la meilleure version de vous-même ? Let's CrossFit together !");

		profileCoach2.setProfesionalDetails(coachDetails1);

		AccountViewModel coach = new AccountViewModel();
		coach.setEmail("coach.lambert@gmail.com");
		coach.setPassword("coach");
		coach.setRole(Role.COACH);
		coach.setGymId((long) 60);

		Goal goal2 = new Goal();
		goal2.setGoalName("Perte de poids");
		goal2.setGoalDate(LocalDate.of(2024, Month.FEBRUARY, 28));

		coach.setGoal(goal2);
		coach.setProfile(profileCoach2);

		Account savedAccount = accountDao.createAccount(coach);

		// ***************************************************************************************************************

		/*
		 * 2er profile fake
		 */
		Profile profileCoach3 = new Profile();
		profileCoach3.setFirstName("Emma");
		profileCoach3.setLastName("Cai");
		profileCoach3.setPictureUrl("profil3.png");

		Address addressCoach3 = new Address();
		addressCoach3.setCity("Créteil");
		addressCoach3.setStreetName("rue Charles de Gaulle");
		addressCoach3.setStreetNumber("14");
		addressCoach3.setPostalCode("94000");

		Contact contactCoach3 = new Contact();
		contactCoach3.setPhone("08 76 54 15 47");
		contactCoach3.setEmail("emma26@gmail.com");

		profileCoach3.setAddress(addressCoach3);
		profileCoach3.setContact(contactCoach3);

		ProfessionalDetails coachDetails3 = new ProfessionalDetails();
		coachDetails3.setCoachCertification("Certification enseigante Zumba universidade de São Paulo et niveau expert (maître) en yoga.");
		coachDetails3.setCoachForm("Advanced Yoga");
		coachDetails3.setCoachCV("Coach sportive passionnée et polyvalente, spécialisée dans l'enseignement du yoga et de la zumba, avec une expertise éprouvée dans la création de programmes holistiques favorisant le bien-être physique et mental.");
		profileCoach3.setProfesionalDetails(coachDetails3);

		AccountViewModel coach3 = new AccountViewModel();
		coach3.setEmail("emma26@gmail.com");
		coach3.setPassword("coach124");
		coach3.setRole(Role.COACH);
		coach3.setGymId((long) 60);

		Goal goal3 = new Goal();
		goal3.setGoalName(" Yoga");
		goal3.setGoalDate(LocalDate.of(2023, Month.FEBRUARY, 28));

		coach3.setGoal(goal3);
		coach3.setProfile(profileCoach3);

		Account savedAccount3 = accountDao.createAccount(coach3);
			
	}
	
}