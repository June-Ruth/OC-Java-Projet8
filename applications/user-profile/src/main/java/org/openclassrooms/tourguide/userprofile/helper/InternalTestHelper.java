package org.openclassrooms.tourguide.userprofile.helper;

import org.springframework.context.annotation.Profile;

@Profile("test")
public class InternalTestHelper {

	// Set this default up to 100,000 for testing
	private static int internalUserNumber = 27;
	
	public static void setInternalUserNumber(int internalUserNumber) {
		InternalTestHelper.internalUserNumber = internalUserNumber;
	}
	
	public static int getInternalUserNumber() {
		return internalUserNumber;
	}
}
