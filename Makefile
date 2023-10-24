startTest:
	./gradlew bootRun
startDev:
	PROFILE=dev ./gradlew bootRun
debug:
	./gradlew bootRun --scan
