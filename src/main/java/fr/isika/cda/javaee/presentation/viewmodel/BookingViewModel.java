package fr.isika.cda.javaee.presentation.viewmodel;

public class BookingViewModel {
	
	private Long id;
	
	
	private Long accountId;
	private Long courseId;

	//***************GETTERS & SETTERS***************
	public Long getId() {
		return id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
}
