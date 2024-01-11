package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.account.controller.LoginController;
import fr.isika.cda.javaee.dao.BookingDao;
import fr.isika.cda.javaee.dao.CourseDAO;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.business.Booking;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.presentation.viewmodel.BookingViewModel;

@Named
@ViewScoped
public class BookingController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BookingViewModel bookingViewModel = new BookingViewModel();
	private Booking selectedBooking;
		
	@Inject
	private BookingDao bookingDao;
	
	@Inject
	private CourseDAO courseDao;
	
	@PostConstruct
	public void init() {
		System.out.println("BookingController bean initialized!");
	}
	
	//CREATE NEW BOOKING IN DATABASE
	public void addBooking() {
		bookingDao.createBooking(bookingViewModel);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Une nouvelle réservation à été enregistrée"));
		bookingViewModel =  new BookingViewModel();
	}
	
	//DELETE SELECTED COURSE IN DATABASE
	public void deleteSelectedBooking() {
		bookingDao.deleteBooking(selectedBooking.getId());	
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Réservation annulée"));
	}
	
	public List<Booking> displayBookingList(){
		return bookingDao.getAllBookings();
	}
	public List<Booking> displayBookingListForAdherent(){
		return bookingDao.getAllBookingsForAdherent();
	}
	
	public List<Course> displayCoursesList() {
		return courseDao.getAllCoursesWithActivities();
	}
	
	//***************GETTERS & SETTERS***************
	public BookingViewModel getBookingViewModel() {
		return bookingViewModel;
	}

	public void setBookingViewModel(BookingViewModel bookingViewModel) {
		this.bookingViewModel = bookingViewModel;
	}

	public Booking getSelectedBooking() {
		return selectedBooking;
	}

	public void setSelectedBooking(Booking selectedBooking) {
		this.selectedBooking = selectedBooking;
	}
	

}
