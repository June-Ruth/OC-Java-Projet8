package org.openclassrooms.tourguide.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import gpsUtil.location.VisitedLocation;
import tripPricer.Provider;

public class User {
	private final UUID userId;

	private final String userName;

	private String phoneNumber;

	private String emailAddress;

	//TODO : voir si sert  à quelque chose mais à priori non
	private Date latestLocationTimestamp;

	private List<VisitedLocation> visitedLocations = new ArrayList<>();

	private List<UserReward> userRewards = new ArrayList<>();

	private UserPreferences userPreferences = new UserPreferences();

	private List<Provider> tripDeals = new ArrayList<>();

	//TODO : voir pourquoi pas un constructeur avec tous les params ?
	public User(UUID userId, String userName, String phoneNumber, String emailAddress) {
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	public UUID getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber1) {
		phoneNumber = phoneNumber1;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(final String emailAddress1) {
		emailAddress = emailAddress1;
	}

	public Date getLatestLocationTimestamp() {
		return latestLocationTimestamp;
	}

	public void setLatestLocationTimestamp(final Date latestLocationTimestamp1) {
		latestLocationTimestamp = latestLocationTimestamp1;
	}

	public List<VisitedLocation> getVisitedLocations() {
		return visitedLocations;
	}

	public void setVisitedLocations(final List<VisitedLocation> visitedLocations1) {
		visitedLocations = visitedLocations1;
	}

	public List<UserReward> getUserRewards() {
		return userRewards;
	}

	public void setUserRewards(final List<UserReward> userRewards1) {
		userRewards = userRewards1;
	}

	public UserPreferences getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(final UserPreferences userPreferences1) {
		userPreferences = userPreferences1;
	}

	public List<Provider> getTripDeals() {
		return tripDeals;
	}

	public void setTripDeals(final List<Provider> tripDeals1) {
		tripDeals = tripDeals1;
	}
}
