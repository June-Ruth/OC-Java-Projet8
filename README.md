#Tour Guide

##Modification History

###3 - Migrate Gradle from Groovy to Kotlin Script
- Adapt syntax to look like Kts
- Updgrade Gradle to 7.0.2

###2 - Fix/ConcurrentModificationException
- Fix ConcurrentModificationException for 100 users with Arrays.copyOf()
- Re-write performance test to have each step in class test

###1 - Fix/NumberFormatException
- Add Locale US to tests to fix NumberFormatException
