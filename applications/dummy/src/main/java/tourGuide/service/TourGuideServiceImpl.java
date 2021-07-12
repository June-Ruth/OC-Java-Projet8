package tourGuide.service;

import org.springframework.stereotype.Service;

@Service
public class TourGuideServiceImpl /*implements TourGuideService */{
	/*

	private Tracker tracker;

	@Override
	public ExecutorService getExecutor() {
		return executor;
	}


	public TourGuideServiceImpl() {
		/*
			tracker = new Tracker(this);
			tracker.startTracking();
			addShutDownHook();
		*/
/*	}

	private void addShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(tracker::stopTracking));
	}

	@Override
	// Utilisé par les tests
	public void stopTracker() {
		tracker.stopTracking();
	}

	@Override
	public List<Attraction> getNearByAttractions(VisitedLocation visitedLocation) {
		List<Attraction> nearbyAttractions = new ArrayList<>();
		for(Attraction attraction : gpsUtil.getAttractions()) {
			if(rewardsService.isWithinAttractionProximity(attraction, visitedLocation.location)) {
				nearbyAttractions.add(attraction);
			}
		}
		
		return nearbyAttractions;
	}


}
