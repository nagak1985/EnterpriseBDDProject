Feature: Enterprise Rental Application

@UI
Scenario Outline: Verify User Able To Search Car Rentals Using Enterprise Car Rental Application

	Given I Select Chrome Browser To Launch "<URL>" to test Enterprise Car Rental Application
	Then I Verify Enterprise Home Page is Loaded Successfully
	When I Set Text "<PickUpPlace>" in Pick Up Field; in Enterprise Application Home Page
	And I Set "<PickUpDate>" from PickUp Date in Date Selection Field; in Enterprise Application Home Page
	And I Set "<ReturnDate>" from Return Date in Date Selection Field; in Enterprise Application Home Page
	And I Select Vehicle Selection from Vehicle Class Dropdown; in Enterprise Application Home Page
	And I Click On Search Button; in Enterprise Application Home Page
	Then I Verify Search Results Displayed Successfully for Enterprise Application Home Page

@Staging
Examples:
| URL 																		| PickUpPlace                           | PickUpDate | ReturnDate |
| https://www.enterprise.com/en/home.html | Buffalo Niagara International Airport | 11/18/2021 | 11/19/2021 |