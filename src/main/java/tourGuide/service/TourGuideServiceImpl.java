package tourGuide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

	private final Tracker tracker;
	Executor executor = Executors.newFixedThreadPool(2000);

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
		tracker.startTracking();
		addShutDownHook();
	}


	private void addShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				tracker.stopTracking();
			}
		});
	}

	@Override
	// Utilisé par les tests
	public void stopTracker() {
		tracker.stopTracking();
	}

	@Override
	public List<UserReward> getUserRewards(User user) {
		return user.getUserRewards();
	}

	@Override
	public VisitedLocation getUserLocation(User user) throws ExecutionException, InterruptedException {
		if(user.getVisitedLocations().isEmpty()) {
			trackUserLocation(user);
		}

		VisitedLocation visitedLocation = getLastVisitedLocation(user);
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
		/*VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
		addToVisitedLocationsOfUser(visitedLocation, user);
		rewardsService.calculateRewards(user);
		return visitedLocation;*/

		/*CompletableFuture<?> completableFuture = CompletableFuture.supplyAsync(() -> gpsUtil.getUserLocation(user.getUserId()), executor)
				.thenAccept(visitedLocation -> addToVisitedLocationsOfUser(visitedLocation, user))
				.thenRunAsync(() -> rewardsService.calculateRewards(user));*/

		CompletableFuture<VisitedLocation> completableFuture = CompletableFuture.supplyAsync(() -> gpsUtil.getUserLocation(user.getUserId()), executor)
				.thenCompose(visitedLocation -> CompletableFuture.supplyAsync(() -> addToVisitedLocationsOfUser(visitedLocation, user)));

		rewardsService.calculateRewards(user);

		VisitedLocation visitedLocation = null;
		try {
			visitedLocation = completableFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return visitedLocation;

		//return completableFuture;
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
	public VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user) {
		List<VisitedLocation> visitedLocations = user.getVisitedLocations();
		visitedLocations.add(visitedLocation);
		return visitedLocation;
	}

	private VisitedLocation getLastVisitedLocation(User user) {
		List<VisitedLocation> visitedLocations = user.getVisitedLocations();
		return visitedLocations.get(visitedLocations.size() - 1);
	}

}
