Feature: Handling New Windows - Test
# I Account - I Commit
# II Account - I Commit
# II Account - II Commit
# I Account - II Commit
# II Account - III Commit
# I Account - III Commit

@UI
Scenario Outline: Verify User Able To Switch To Citi-Bank Login Page Application

	Given I Select Chrome Browser To Launch "<URL>" For Test Execution
	Then I Verify CitiBank Home Page is Loaded Successfully
	When I Click On Login Button in Home Page
	Then I Verify Page is Loaded Successfully; in Login Page
	When I Close ChildWindow
	Then I Verify CitiBank Home Page is Loaded Successfully

@Staging
Examples:
| URL                                |
| https://www.online.citibank.co.in/ |