#Tour Guide

##Modification History

###4 - Improve architecture using MVC Pattern
- Create interface to respect SOLID principles and adapt code for them
- Clean model
- Isolate test methods from services
- Separate controllers depending on REST conventions
- Make Tracker implements Runnable and use ScheduledExecutorService
- Implement CompletableFuture and Executor with fixed thread pool

###3 - Migrate Gradle from Groovy to Kotlin Script
- Adapt syntax to look like Kts
- Upgrade Gradle to 7.0.2
- Upgrade to Spring 2.4.5 & JUnit 5

###2 - Fix/ConcurrentModificationException
- Fix ConcurrentModificationException for 100 users with Arrays.copyOf()
- Re-write performance test to have each step in class test

###1 - Fix/NumberFormatException
- Add Locale US to tests to fix NumberFormatException
