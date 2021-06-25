package tourGuide.service;

import org.springframework.stereotype.Service;

@Service
public class TourGuideServiceImpl /*implements TourGuideService */{
	/*private static final Logger LOGGER = LoggerFactory.getLogger(TourGuideServiceImpl.class);
	private final GpsUtil gpsUtil;
	private final RewardsService rewardsService;
	private final TripPricer tripPricer = new TripPricer();

	private Tracker tracker;
	ExecutorService executor = Executors.newFixedThreadPool(800);

	@Override
	public ExecutorService getExecutor() {
		return executor;
	}

	private static final String tripPricerApiKey = "test-server-api-key";
	private Map<String, User> internalUserMap;

	public TourGuideServiceImpl(GpsUtil gpsUtil, RewardsService rewardsService) {
		this.gpsUtil = gpsUtil;
		this.rewardsService = rewardsService;


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
	public List<Provider> getTripDeals(User user) {
		int cumulatativeRewardPoints = user.getUserRewards().stream().mapToInt(UserReward::getRewardPoints).sum();
		List<Provider> providers = tripPricer.getPrice(tripPricerApiKey, user.getUserId(), user.getUserPreferences().getNumberOfAdults(), 
				user.getUserPreferences().getNumberOfChildren(), user.getUserPreferences().getTripDuration(), cumulatativeRewardPoints);
		user.setTripDeals(providers);
		return providers;
	}

	@Override
	public CompletableFuture<?> trackUserLocation(User user) {
		CompletableFuture<?> completableFuture = CompletableFuture.supplyAsync(() -> gpsUtil.getUserLocation(user.getUserId()), executor)
				.thenAccept(visitedLocation -> addToVisitedLocationsOfUser(visitedLocation, user))
				.thenRunAsync(() -> rewardsService.calculateRewards(user));

		return completableFuture;
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
	public VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user) {
		List<VisitedLocation> visitedLocations = user.getVisitedLocations();
		visitedLocations.add(visitedLocation);
		return visitedLocation;
	}*/
}
