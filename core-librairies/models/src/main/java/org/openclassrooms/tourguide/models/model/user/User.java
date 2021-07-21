package org.openclassrooms.tourguide.models.model.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import org.openclassrooms.tourguide.models.model.trip.Provider;

public class User {
	private UUID userId;

	private String username;

	private String phoneNumber;

	private String emailAddress;

	@JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
	@JsonSerialize(using = DateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date latestLocationTimestamp;

	private List<VisitedLocation> visitedLocations = new ArrayList<>();

	private List<UserReward> userRewards = new ArrayList<>();

	private UserPreferences userPreferences = new UserPreferences();

	private List<Provider> tripDeals = new ArrayList<>();


	public User(final UUID userId1,
				final String username1,
				final String phoneNumber1,
				final String emailAddress1,
				final Date latestLocationTimestamp1,
				final List<VisitedLocation> visitedLocations1,
				final List<UserReward> userRewards1,
				final UserPreferences userPreferences1,
				final List<Provider> tripDeals1) {
		userId = userId1;
		username = username1;
		phoneNumber = phoneNumber1;
		emailAddress = emailAddress1;
		latestLocationTimestamp = latestLocationTimestamp1;
		visitedLocations = visitedLocations1;
		userRewards = userRewards1;
		userPreferences = userPreferences1;
		tripDeals =tripDeals1;
	}

	public User() { }

	public UUID getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
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
