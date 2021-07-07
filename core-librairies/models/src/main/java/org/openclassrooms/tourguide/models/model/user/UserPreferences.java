package org.openclassrooms.tourguide.models.model.user;

public class UserPreferences {

	private int attractionProximity = Integer.MAX_VALUE;

	private final String currency = "USD";

	private long lowerPricePoint = 0;

	private long highPricePoint = Integer.MAX_VALUE;

	private int tripDuration = 1;

	private int ticketQuantity = 1;

	private int numberOfAdults = 1;

	private int numberOfChildren = 0;

	//TODO : pourquoi vide ?
	public UserPreferences() {
	}

	public int getAttractionProximity() {
		return attractionProximity;
	}

	public void setAttractionProximity(final int attractionProximity1) {
		attractionProximity = attractionProximity1;
	}

	public long getLowerPricePoint() {
		return lowerPricePoint;
	}

	public void setLowerPricePoint(final long lowerPricePoint1) {
		lowerPricePoint = lowerPricePoint1;
	}

	public long getHighPricePoint() {
		return highPricePoint;
	}

	public void setHighPricePoint(final long highPricePoint1) {
		highPricePoint = highPricePoint1;
	}

	public int getTripDuration() {
		return tripDuration;
	}

	public void setTripDuration(final int tripDuration1) {
		tripDuration = tripDuration1;
	}

	public int getTicketQuantity() {
		return ticketQuantity;
	}

	public void setTicketQuantity(final int ticketQuantity1) {
		ticketQuantity = ticketQuantity1;
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(final int numberOfAdults1) {
		numberOfAdults = numberOfAdults1;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(final int numberOfChildren1) {
		numberOfChildren = numberOfChildren1;
	}
}
