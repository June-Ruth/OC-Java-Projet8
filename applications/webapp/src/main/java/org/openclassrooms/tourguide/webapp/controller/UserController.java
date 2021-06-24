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
        //TODO ; tests & services

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

}
