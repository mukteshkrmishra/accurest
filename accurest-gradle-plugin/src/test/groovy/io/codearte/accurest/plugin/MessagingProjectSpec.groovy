package io.codearte.accurest.plugin

import spock.lang.Stepwise

@Stepwise
class MessagingProjectSpec extends AccurestIntegrationSpec {

	def setup() {
		setupForProject("functionalTest/messagingProject")
		runTasksSuccessfully('clean')   //delete accidental output when previously importing SimpleBoot into Idea to tweak it
	}

	def "should pass basic flow for Spock"() {
		given:
			assert fileExists('build.gradle')
		expect:
			runTasksSuccessfully('check', "publishToMavenLocal")
			jarContainsAccurestContracts('build/libs')
	}

	def "should pass basic flow for JUnit"() {
		given:
			runTasksSuccessfully('clean')
			assert fileExists('build.gradle')
		expect:
			switchToJunitTestFramework('io.codearte.accurest.samples.book.MessagingBaseSpec', 'io.codearte.accurest.samples.book.MessagingBaseTest')
			runTasksSuccessfully('check', "publishToMavenLocal")
			jarContainsAccurestContracts('build/libs')
	}

}
