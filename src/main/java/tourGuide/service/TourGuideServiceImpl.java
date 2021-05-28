package tourGuide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import tourGuide.helper.InternalTestInitializer;
import tourGuide.tracker.Tracker;
import tourGuide.model.User;
import tourGuide.model.UserReward;
import tripPricer.Provider;
import tripPricer.TripPricer;

@Service
public class TourGuideServiceImpl implements TourGuideService {
	private Logger logger = LoggerFactory.getLogger(TourGuideServiceImpl.class);
	private final GpsUtil gpsUtil;
	private final RewardsService rewardsService;
	private final TripPricer tripPricer = new TripPricer();

	private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	private final Tracker tracker;


	boolean testMode = true;
	private static final String tripPricerApiKey = "test-server-api-key";
	private Map<String, User> internalUserMap;
	private final InternalTestInitializer internalTestInitializer = new InternalTestInitializer();

	public TourGuideServiceImpl(GpsUtil gpsUtil, RewardsService rewardsService) {
		this.gpsUtil = gpsUtil;
		this.rewardsService = rewardsService;
		
		if(testMode) {
			logger.info("TestMode enabled");
			logger.debug("Initializing users");
			internalUserMap = internalTestInitializer.initializeInternalUsers();
			logger.debug("Finished initializing users");
		}

		tracker = new Tracker(this);
		executorService.scheduleAtFixedRate(tracker, 0, 5, TimeUnit.MINUTES);
		addShutDownHook();
	}


	private void addShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				tracker.stopTracking();
				executorService.shutdownNow();
			}
		});
	}

	@Override
	public void stopTracker() {
		tracker.stopTracking();
		executorService.shutdownNow();
	}



	@Override
	public List<UserReward> getUserRewards(User user) {
		return user.getUserRewards();
	}

	@Override
	public VisitedLocation getUserLocation(User user) {
		VisitedLocation visitedLocation = (user.getVisitedLocations().size() > 0) ?
			getLastVisitedLocation(user) :
			trackUserLocation(user);
		return visitedLocation;
	}

	@Override
	public User getUser(String userName) {
		return internalUserMap.get(userName);
	}

	@Override
	public List<User> getAllUsers() {
		return new ArrayList<>(internalUserMap.values());
	}

	@Override
	public void addUser(User user) {
		if(!internalUserMap.containsKey(user.getUserName())) {
			internalUserMap.put(user.getUserName(), user);
		}
	}

	@Override
	public List<Provider> getTripDeals(User user) {
		int cumulatativeRewardPoints = user.getUserRewards().stream().mapToInt(UserReward::getRewardPoints).sum();
		List<Provider> providers = tripPricer.getPrice(tripPricerApiKey, user.getUserId(), user.getUserPreferences().getNumberOfAdults(), 
				user.getUserPreferences().getNumberOfChildren(), user.getUserPreferences().getTripDuration(), cumulatativeRewardPoints);
		user.setTripDeals(providers);
		return providers;
	}

	@Override
	public VisitedLocation trackUserLocation(User user) {
		VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
		addToVisitedLocationsOfUser(visitedLocation, user);
		rewardsService.calculateRewards(user);
		return visitedLocation;
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

	@Override
	public void clearVisitedLocationsOfUser(User user) {
		user.getVisitedLocations().clear();
	}

	@Override
	public void addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user) {
		List<VisitedLocation> visitedLocations = user.getVisitedLocations();
		visitedLocations.add(visitedLocation);
	}

	private VisitedLocation getLastVisitedLocation(User user) {
		List<VisitedLocation> visitedLocations = user.getVisitedLocations();
		return visitedLocations.get(visitedLocations.size() - 1);
	}

}
