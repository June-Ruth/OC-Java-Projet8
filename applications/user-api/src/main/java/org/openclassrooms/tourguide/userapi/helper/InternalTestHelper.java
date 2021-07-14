package org.openclassrooms.tourguide.userapi.helper;

import org.springframework.context.annotation.Profile;

@Profile("test")
public class InternalTestHelper {

	/**
	 * Expected internal users number.
	 * Set to 100,000 for testing //TODO
	 */
	private static int internalUserNumber = 3;

	public static void setInternalUserNumber(int internalUserNumber) {
		InternalTestHelper.internalUserNumber = internalUserNumber;
	}

	public static int getInternalUserNumber() {
		return internalUserNumber;
	}
}
