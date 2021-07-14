package org.openclassrooms.tourguide.models.model.user;

import org.openclassrooms.tourguide.models.model.location.Attraction;
import org.openclassrooms.tourguide.models.model.location.VisitedLocation;

public class UserReward {

	private final VisitedLocation visitedLocation;
	private final Attraction attraction;
	private int rewardPoints;

	public UserReward(VisitedLocation visitedLocation, Attraction attraction, int rewardPoints) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
		this.rewardPoints = rewardPoints;
	}

	public UserReward(VisitedLocation visitedLocation, Attraction attraction) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
	}

	public VisitedLocation getVisitedLocation() {
		return visitedLocation;
	}

	public Attraction getAttraction() {
		return attraction;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(final int rewardPoints1) {
		rewardPoints = rewardPoints1;
	}
	
}
