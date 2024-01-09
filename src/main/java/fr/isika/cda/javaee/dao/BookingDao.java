package fr.isika.cda.javaee.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.business.Booking;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.presentation.viewmodel.BookingViewModel;

@Stateless
public class BookingDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Booking createBooking (BookingViewModel bookingViewModel) {
		Booking bookingBean = new Booking();
		Account acnt = findAccountById(bookingViewModel.getAccountId());
		bookingBean.setAccount(acnt);
		Course crs = findCourseById(bookingViewModel.getCourseId());
		bookingBean.setCourse(crs);
		entityManager.persist(bookingBean);
		entityManager.flush();
		return bookingBean;			
	}
	
	public void deleteBooking(Long bookingToDeleteId) {
		Booking bookingToDelete = entityManager
				.createQuery("SELECT b FROM Booking WHERE b.id = :bookingIdParam", Booking.class)
				.setParameter("bookingIdParam", bookingToDeleteId)
				.getSingleResult();
		entityManager.remove(bookingToDelete);
	}
	
	public Account findAccountById(long accountId) {
		return entityManager.createQuery("SELECT a FROM Account a WHERE a.id = :accountIdParam", Account.class)
				.setParameter("accountIdParam", accountId).getSingleResult();
	}
	
	public Course findCourseById(long courseId) {
		return entityManager.createQuery("SELECT c FROM Course c WHERE c.id = :courseIdParam", Course.class)
				.setParameter("courseIdParam", courseId).getSingleResult();
	}

}
