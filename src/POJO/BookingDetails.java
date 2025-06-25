package POJO;

import java.util.List;

public class BookingDetails {
	
	private List<BookingId> bookingId;
	private String firstname;
	private String lastname;
	private String totalprice;
	private String depositpaid;
	private BookingDates bookingdates;
	private String additionalneeds;
	
	public List<BookingId> getBookingId() {
		return bookingId;
	}
	public void setBookingId(List<BookingId> bookingId) {
		this.bookingId = bookingId;
	}
	
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}
	public String getLastName() {
		return lastname;
	}
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}
	public String getTotalPrice() {
		return totalprice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalprice = totalPrice;
	}
	public String getDepositPaid() {
		return depositpaid;
	}
	public void setDepositPaid(String depositPaid) {
		this.depositpaid = depositPaid;
	}
	public BookingDates getBookingDates() {
		return bookingdates;
	}
	public void setBookingDates(BookingDates bookingDates) {
		this.bookingdates = bookingDates;
	}
	public String getAdditionalNeeds() {
		return additionalneeds;
	}
	public void setAdditionalNeeds(String additionalNeeds) {
		this.additionalneeds = additionalNeeds;
	}
	

}
