Feature: Irish Life 

Scenario: Verify Contact form
	Given that I am on the Irish Life website homepage
	When I click on the Contact link in then top navigation
	Then a new page loads which contains a contact form
	
Scenario: Verify 
	Given that I am on the Irish Life website homepage
	When I click on the Find out more link
	Then I see a form which contains the text I want to cover