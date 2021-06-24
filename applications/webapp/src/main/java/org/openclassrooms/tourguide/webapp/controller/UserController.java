package org.openclassrooms.tourguide.webapp.controller;

public class UserController {

    /**
     * Get the closest five attractions to the user, no matter how far away they are.
     * @param username of user - may throw 404 exception if user doesn't exist.
     * @return nearAttractionDto
     * @see NearAttractionDto
     */
   /* @GetMapping("/users/{username}/closest-attractions")
    public List<NearAttractionDto> getUserFiveClosestAttractions(@PathVariable String username) {

        // NB manque ces deux étapes dans le process
        // Return The distance in miles between the user's location and each of the attractions.
        // Note: Attraction reward points can be gathered from RewardsCentral

        List<Attraction> fiveNearestAttractions = userService.getUserFiveClosestAttractions(username);
        List<NearAttractionDto> fiveNearestAttractionsDto = new ArrayList<>();

        for(Attraction attraction : fiveNearestAttractions) {
            int rewardPoints = userService.getAttractionRewardPoints(attraction);
            NearAttractionDto nearAttractionDTO = DtoConverter.convertAttractionToNearAttractionDto(attraction, userService.getUserCurrentLocation(username).location, rewardPoints);
            fiveNearestAttractionsDto.add(nearAttractionDTO);
        }
        return fiveNearestAttractionsDto;
    }*/

    /* TODO : Feature List - As a user, I want ... :
        - HomePage
        - To see my contact information
            - UserMS : GET : /users/{username} => READY
        - To modify my contact information
            - UserMS : PUT : /users/{username} => READY
        - To see my preferences to know how trip deals are provided
            - UserMS : GET : /users/{username}/preferences => READY
        - To modify my preferences to have appropriate trip deals
            - UserMS : PUT : /users/{username}/preferences => READY
        - To see my rewards to anticipate trip deals
            - UserMS : GET : /users/{username}/rewards => READY
        - To get attraction information
            - LocalizationMS : /attractions/{attractionName} => READY
        - To get attraction proposals near my location
            - UserMS : GET : /users/{username}/current-location => READY
            - LocalizationMS : GET : /attractions/closest-five
            - Get rewards for each attractions

        - To get trip deals

     TODO : Feature List - As an admin, I want ... :
      - HomePage
      - To see all users current locations to analyze it
        - UserMS : GET : /users/all-current-locations => READY
      - To see each user current location
        - UserMS : GET : /users/{username}/current-location => READY

      */

}
