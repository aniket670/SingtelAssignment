package stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import implementation.CommonElements;
import org.junit.Assert;
import pages.ToDoPage;

public class ToDoStepDefinition {
    @Given("the user is on the application")
    public void the_user_is_on_the_application() {
        try {
            new ToDoPage().navigateToApplication();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Given("^the user input the todo as (.+)$")
    public void the_user_input_the_todo(String input) {
        new ToDoPage().createToDoRecord(input);
    }
    @Then("^the user verify the (.+) is added successfully to (.+)$")
    public void the_user_verify_the_record_is_added_successfully(String input, String tbName) {
          Assert.assertTrue( "Record not added", new ToDoPage().verifyRecordInTabs(input, tbName));
    }
    @Then("^the user verify the (.+) in (.+)$")
    public void the_user_verify_the_test123_in_all(String input, String tabName) {
        Assert.assertTrue("The record is not found in selected tab",
        new ToDoPage().verifyRecordInTabs(input, tabName));

    }
    @When("^the user tries to delete the (.+)$")
    public void theUserTriesToDeleteTheRecord(String input) {
        new ToDoPage().deleteToDoRecord(input);
    }

    @Then("^the user verify deleted (.+) not displayed in (.+)$")
    public void theUserVerifyTheRecordNotDisplayed(String input, String tbName) {
        Assert.assertFalse("The record still found",
                new ToDoPage().verifyRecordInTabs(input,tbName));
    }

    @When("^the user selects the (.+) as done$")
    public void theUserSelectsTheRecordAsDone(String toDoRecord) {
        new ToDoPage().selectRecToBeDone(toDoRecord);
    }

    @Then("^the user verify the clearCompleted button is visible$")
    public void theUserVerifyTheButtonIsVisible() {
        Assert.assertTrue("The button is not present",
                new ToDoPage().isClearCompletedPresent());
    }

    @When("^the user clicks on clearCompleted$")
    public void theUserClicksOnClearCompleted() {
        new ToDoPage().clickClearCompleted();
    }

    @And("^the user verify the clearCompleted button is hidden$")
    public void theUserVerifyTheClearCompletedButtonIsHidden() {
        Assert.assertFalse("The button is displayed",
                new ToDoPage().verifyClearCompIsHidden());
    }
}
