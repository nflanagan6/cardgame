plugins {
	`java-library-conventions`
}

description = "JUnit Platform Suite API"

dependencies {
	api(platform(projects.junitBom))
	api(projects.junitPlatformCommons)

	compileOnlyApi(libs.apiguardian)

	osgiVerification(projects.junitPlatformCommons)
}
