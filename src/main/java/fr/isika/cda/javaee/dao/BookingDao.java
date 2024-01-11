package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.DialogOwner;

import fr.isika.cda.javaee.account.controller.LoginController;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.business.Booking;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.presentation.viewmodel.BookingViewModel;
import fr.isika.cda.javaee.utils.SessionUtils;

@Stateless
public class BookingDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Booking createBooking (BookingViewModel bookingViewModel) {
		Booking bookingBean = new Booking();
		Account acnt = findAccountById(1);
		//Account acnt = findAccountById(bookingViewModel.getAccountId());
		bookingBean.setAccount(acnt);
		Course crs = findCourseById(bookingViewModel.getCourseId());
		bookingBean.setCourse(crs);
		entityManager.persist(bookingBean);
		entityManager.flush();
		return bookingBean;			
	}

	public void deleteBooking(Long bookingToDeleteId) {
		Booking bookingToDelete = entityManager
				.createQuery("SELECT b FROM Booking b WHERE b.id = :bookingIdParam", Booking.class)
				.setParameter("bookingIdParam", bookingToDeleteId)
				.getSingleResult();
		entityManager.remove(bookingToDelete);
	}

	public void saveBooking(Booking booking) {
		entityManager.persist(booking);
	}

	public Account findAccountById(long accountId) {
		return entityManager.createQuery("SELECT a FROM Account a WHERE a.id = :accountIdParam", Account.class)
				.setParameter("accountIdParam", accountId).getSingleResult();
	}
	
	public List<Course> getAllCoursesWithActivities() {
		return entityManager.createQuery("SELECT c FROM Course c LEFT JOIN FETCH c.activity", Course.class)
				.getResultList();
	}
	
	public List<Booking> getAllBookings() {
		LoginController controller = new LoginController();
		Account logged = controller.getLoggedAccount();
		Long loggedAccountGymId = logged.getGymId();
		return entityManager.createQuery("SELECT b FROM Booking b WHERE b.account.gymId = :gymIdParam", Booking.class)
				.setParameter("gymIdParam", loggedAccountGymId)
				.getResultList();
	}
	
	public List<Booking> getAllBookingsForAdherent() {
		LoginController controller = new LoginController();
		Account logged = controller.getLoggedAccount();
		Long loggedAccountId = logged.getId();
		return entityManager.createQuery("SELECT b FROM Booking b WHERE b.account.id = :accountIdParam", Booking.class)
				.setParameter("accountIdParam", loggedAccountId)
				.getResultList();
	}


//	public List <Booking> getAccountBookingsList(Account accountToCheck){
//		Long accountToCheckId = accountToCheck.getId();
//		return entityManager.createQuery("SELECT a FROM Account a LEFT JOINT FETCH a.bookingList WHERE a.id = :accountIdParam", Account.class)
//				.setParameter("accountIdParam", accountToCheckId)
//				.getResultList();
//	}

	public Course findCourseById(long courseId) {
		return entityManager.createQuery("SELECT c FROM Course c WHERE c.id = :courseIdParam", Course.class)
				.setParameter("courseIdParam", courseId).getSingleResult();
	}

}
