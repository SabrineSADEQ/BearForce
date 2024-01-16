package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.schedule.ScheduleEntryMoveEvent;
import org.primefaces.event.schedule.ScheduleEntryResizeEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import fr.isika.cda.javaee.dao.ActivityDao;
import fr.isika.cda.javaee.dao.BookingDao;
import fr.isika.cda.javaee.dao.CourseDAO;
import fr.isika.cda.javaee.dao.accounts.AccountDao;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Booking;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.utils.SessionUtils;

@Named
@ViewScoped
public class PlanningCourseController implements Serializable {

	private static final long serialVersionUID = -160397842934902381L;

	/*
	 * Dependencies
	 */
	@Inject
	private CourseDAO courseDao;

	@Inject
	private ActivityDao activityDao;

	@Inject
	private BookingDao bookingDao;

	@Inject
	private AccountDao accountDao;

	//	@Inject
	//	private LoginController loginController;

	/*
	 * Schedule properties
	 */
	private ScheduleModel eventModel;
	private ScheduleEvent<?> event = new DefaultScheduleEvent<>();
	private String serverTimeZone = ZoneId.systemDefault().toString();
	private boolean slotEventOverlap = true;
	private boolean showWeekNumbers = false;
	private boolean showHeader = true;
	private boolean draggable = false;
	private boolean resizable = true;
	private boolean selectable = false;
	private boolean showWeekends = true;
	private boolean tooltip = true;
	private boolean allDaySlot = false;
	private boolean rtl = false;
	private double aspectRatio = Double.MIN_VALUE;
	private String leftHeaderTemplate = "précédent,suivant aujourd'hui";
	private String centerHeaderTemplate = "title";
	private String rightHeaderTemplate = "dayGridMonth,timeGridWeek,timeGridDay,listYear";
	private String nextDayThreshold = "09:00:00";
	private String weekNumberCalculation = "local";
	private String weekNumberCalculator = "date.getTime()";
	private String displayEventEnd;
	private String timeFormat;
	private String slotDuration = "00:30:00";
	private String slotLabelInterval;
	private String slotLabelFormat;
	private String scrollTime = "06:00:00";
	private String minTime = "04:00:00";
	private String maxTime = "20:00:00";
	private String locale = "fr";
	private String timeZone = "";
	private String clientTimeZone = "local";
	private String columnHeaderFormat = "";
	private String view = "timeGridWeek";
	private String height = "auto";
	private String backgroundColor = "blue";

	/*
	 * View props
	 */
	private Long selectedActivityId;
	private Long selectedCoachProfileId;
	private Long selectedCourseId;
	private Integer nbPlacesCount;
	private boolean reservedOrNot;


	@PostConstruct
	private void init() {
		refreshModel();
	}

	//******************************************************************************************
	private void refreshModel() {
		eventModel = new DefaultScheduleModel();
		loadAllCourses();
	}

	private void loadAllCourses() {	
		List<Course> courses = getAllCourses();
		for (Course c : courses) {
			transformCoursetoEvent(c);
		}
	}

	/**
	 * Get all the courses to come, of the current session space.
	 * 
	 * @return the courses list (:List<Course>)
	 */
	private List<Course> getAllCourses() {
		String role = getCurrentConnectedRole();
		if (role == "Coach") {
			return courseDao.getAllCoursesOfConnectedCoach();
		} else {
			return courseDao.getAllCoursesWithActivities();
		}
	}
	
	private DefaultScheduleEvent<?> transformCoursetoEvent(Course course) {
		
		DefaultScheduleEvent<?> event = DefaultScheduleEvent
				.builder()
				.id(String.valueOf(course.getId()))
				.startDate(course.getStartDate())
				.endDate(course.getEndDate())
				.draggable(false)
				.build();
		
		boolean booked = checkExistingBooking(event.getId());
		
		if (booked) {
			event.setTitle("RESERVE : " + course.getActivity().getName());
			event.setBackgroundColor("green");
		} else {
			event.setTitle(course.getActivity().getName());
			event.setBackgroundColor("orange");
		}
		
		// Dans tous les cas on renvoie l'event
		eventModel.addEvent(event);
		return event;
	}

	public void onEventSelect(SelectEvent<ScheduleEvent<Course>> selectEvent) {
		event = selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
		event = DefaultScheduleEvent.builder()
				.startDate(selectEvent.getObject())
				.endDate(selectEvent.getObject().plusHours(1))
				.build();
	}

	public void addEvent() {
		if (event.getId() == null) {
			createNewEvent();
		} else {
			updateEvent();
		}
		refreshModel();
		event = new DefaultScheduleEvent<>();
	}

	public void bookingEvent() {	
		boolean booked = checkExistingBooking(event.getId());
		if (!booked) {
			addBooking();
			refreshModel();
			event = new DefaultScheduleEvent<>();
		}
	}

	private Long getCurrentConnectedId() { 
		Account logged = SessionUtils.getAccount();
		return logged.getId();
	}

	private String getCurrentConnectedRole() { 
		Account logged = SessionUtils.getAccount();
		return logged.getRole().getLibelle();
	}

	private void addBooking() {	
		Booking bookingToCreate = new Booking();
		Course courseToBook = courseDao.getCourseByIdJoinActivity(Long.valueOf(event.getId()));
		Account bookingAccount = accountDao.getAccountById(getCurrentConnectedId());
		bookingToCreate.setAccount(bookingAccount);
		bookingToCreate.setCourse(courseToBook);		
		bookingDao.saveBooking(bookingToCreate);	
	}

	public boolean checkExistingBooking(String eventId) {
		//Long accountId = loginController.getLoggedAccount().getId();
		if(event.getId() != null) {
			Course courseToBook = courseDao.getCourseByIdJoinActivity(Long.valueOf(eventId));
			List<Booking> bookings = bookingDao.getBookingListByAccount();
			List<Long> coursesIdsList = new ArrayList<Long>();
			if (!bookings.isEmpty()) {
				for (Booking booking : bookings) {
					coursesIdsList.add(booking.getCourse().getId());
				}

				Long courseToBookId = courseToBook.getId();
				if(coursesIdsList.contains(courseToBookId)) {
					return true; 
				} else {
					return false;

				}
			}
		}
		return false;
	}
	
	private void createNewEvent() {	
		// add the event to the graphical model
		eventModel.addEvent(event);

		// save the course
		Course courseToCreate = new Course();

		Activity activity = activityDao.findActivityById(selectedActivityId);
		courseToCreate.setActivity(activity);

		Profile coach = activityDao.findTrainerById(selectedCoachProfileId);
		courseToCreate.setTrainer(coach);
		courseToCreate.setNbPlaces(nbPlacesCount);;
		courseToCreate.setStartDate(event.getStartDate());
		courseToCreate.setEndDate(event.getEndDate());

		courseDao.saveCourse(courseToCreate);
	}

	private void updateEvent() {
		Long courseId = Long.valueOf(event.getId());
		Course courseToUpdate = courseDao.getCourseByIdJoinActivity(courseId);

		// Si l'activité a changé 
		if(courseToUpdate.getActivity().getId() != selectedActivityId) {
			Activity activity = activityDao.findActivityById(selectedActivityId);
			courseToUpdate.setActivity(activity);
		}

		// Si le coach a changé 
		if(courseToUpdate.getTrainer().getId() != selectedCoachProfileId) {
			Profile coach = activityDao.findTrainerById(selectedCoachProfileId);
			courseToUpdate.setTrainer(coach);
		}

		// Dans tous les autres cas on modifie
		courseToUpdate.setStartDate(event.getStartDate());
		courseToUpdate.setEndDate(event.getEndDate());

		if(nbPlacesCount != null) {
			courseToUpdate.setNbPlaces(nbPlacesCount);
		}

		eventModel.updateEvent(event);

		courseDao.mergeCourse(courseToUpdate);

		refreshModel();
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved",
				"Delta:" + event.getDeltaAsDuration());
		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized",
				"Start-Delta:" + event.getDeltaStartAsDuration() + ", End-Delta: " + event.getDeltaEndAsDuration());
		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void resetEvent() {
		event = new DefaultScheduleEvent<>();
	}

	//******************************************************************************************
	public boolean isSlotEventOverlap() {
		return slotEventOverlap;
	}

	public void setSlotEventOverlap(boolean slotEventOverlap) {
		this.slotEventOverlap = slotEventOverlap;
	}

	public boolean isShowWeekNumbers() {
		return showWeekNumbers;
	}

	public void setShowWeekNumbers(boolean showWeekNumbers) {
		this.showWeekNumbers = showWeekNumbers;
	}

	public boolean isShowHeader() {
		return showHeader;
	}

	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
	}

	public boolean isDraggable() {
		return draggable;
	}

	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public boolean isShowWeekends() {
		return showWeekends;
	}

	public void setShowWeekends(boolean showWeekends) {
		this.showWeekends = showWeekends;
	}

	public boolean isTooltip() {
		return tooltip;
	}

	public void setTooltip(boolean tooltip) {
		this.tooltip = tooltip;
	}

	public boolean isAllDaySlot() {
		return allDaySlot;
	}

	public void setAllDaySlot(boolean allDaySlot) {
		this.allDaySlot = allDaySlot;
	}

	public boolean isRtl() {
		return rtl;
	}

	public void setRtl(boolean rtl) {
		this.rtl = rtl;
	}

	public double getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public String getLeftHeaderTemplate() {
		return leftHeaderTemplate;
	}

	public void setLeftHeaderTemplate(String leftHeaderTemplate) {
		this.leftHeaderTemplate = leftHeaderTemplate;
	}

	public String getCenterHeaderTemplate() {
		return centerHeaderTemplate;
	}

	public void setCenterHeaderTemplate(String centerHeaderTemplate) {
		this.centerHeaderTemplate = centerHeaderTemplate;
	}

	public String getRightHeaderTemplate() {
		return rightHeaderTemplate;
	}

	public void setRightHeaderTemplate(String rightHeaderTemplate) {
		this.rightHeaderTemplate = rightHeaderTemplate;
	}

	public String getNextDayThreshold() {
		return nextDayThreshold;
	}

	public void setNextDayThreshold(String nextDayThreshold) {
		this.nextDayThreshold = nextDayThreshold;
	}

	public String getWeekNumberCalculation() {
		return weekNumberCalculation;
	}

	public void setWeekNumberCalculation(String weekNumberCalculation) {
		this.weekNumberCalculation = weekNumberCalculation;
	}

	public String getWeekNumberCalculator() {
		return weekNumberCalculator;
	}

	public void setWeekNumberCalculator(String weekNumberCalculator) {
		this.weekNumberCalculator = weekNumberCalculator;
	}

	public String getDisplayEventEnd() {
		return displayEventEnd;
	}

	public void setDisplayEventEnd(String displayEventEnd) {
		this.displayEventEnd = displayEventEnd;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getSlotDuration() {
		return slotDuration;
	}

	public void setSlotDuration(String slotDuration) {
		this.slotDuration = slotDuration;
	}

	public String getSlotLabelInterval() {
		return slotLabelInterval;
	}

	public void setSlotLabelInterval(String slotLabelInterval) {
		this.slotLabelInterval = slotLabelInterval;
	}

	public String getSlotLabelFormat() {
		return slotLabelFormat;
	}

	public void setSlotLabelFormat(String slotLabelFormat) {
		this.slotLabelFormat = slotLabelFormat;
	}

	public String getScrollTime() {
		return scrollTime;
	}

	public void setScrollTime(String scrollTime) {
		this.scrollTime = scrollTime;
	}

	public String getMinTime() {
		return minTime;
	}

	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}

	public String getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getClientTimeZone() {
		return clientTimeZone;
	}

	public void setClientTimeZone(String clientTimeZone) {
		this.clientTimeZone = clientTimeZone;
	}

	public String getColumnHeaderFormat() {
		return columnHeaderFormat;
	}

	public void setColumnHeaderFormat(String columnHeaderFormat) {
		this.columnHeaderFormat = columnHeaderFormat;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public ScheduleEvent<?> getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent<?> event) {
		this.event = event;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public String getServerTimeZone() {
		return serverTimeZone;
	}

	public void setServerTimeZone(String serverTimeZone) {
		this.serverTimeZone = serverTimeZone;
	}

	public Long getSelectedActivityId() {
		return selectedActivityId;
	}
	public void setSelectedActivityId(Long selectedActivityId) {
		this.selectedActivityId = selectedActivityId;
	}
	public Long getSelectedCoachProfileId() {
		return selectedCoachProfileId;
	}
	public void setSelectedCoachProfileId(Long selectedCoachProfileId) {
		this.selectedCoachProfileId = selectedCoachProfileId;
	}
	public Integer getNbPlacesCount() {
		return nbPlacesCount;
	}
	public void setNbPlacesCount(Integer nbPlacesCount) {
		this.nbPlacesCount = nbPlacesCount;
	}

	public Long getSelectedCourseId() {
		return selectedCourseId;
	}

	public void setSelectedCourseId(Long selectedCourseId) {
		this.selectedCourseId = selectedCourseId;
	}

	public boolean isReservedOrNot() {
		return reservedOrNot;
	}

	public void setReservedOrNot(boolean reservedOrNot) {
		this.reservedOrNot = reservedOrNot;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}

