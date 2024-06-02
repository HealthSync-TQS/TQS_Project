package project.backend.functional.scheduleAppointment;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScheduleAppointmentSteps {

    private WebDriver driver = Hooks.driver;
    private JavascriptExecutor js = (JavascriptExecutor) Hooks.driver;

    @When("I go to {string}")
    public void i_go_to(String url) {
        driver.get(url);
    }

    @When("I insert Patient Number {string}")
    public void i_insert_patient_number(String patientNumber) {
        WebElement patientNumberElement = driver.findElement(By.id(":r0:"));
        patientNumberElement.sendKeys(patientNumber);
    }

    @When("I insert Patient Name {string}")
    public void i_insert_patient_name(String patientName) {
        WebElement patientNameElement = driver.findElement(By.id(":r1:"));
        patientNameElement.sendKeys(patientName);
    }

    @When("I insert Email {string}")
    public void i_insert_email(String email) {
        WebElement emailElement = driver.findElement(By.id(":r2:"));
        emailElement.sendKeys(email);
    }

    @When("I select HealthCare Unit {string}")
    public void i_select_healthcare_unit(String healthcareUnit) {
        WebElement healthcareUnitElement = driver
                .findElement(By.cssSelector("#mui-component-select-healthcareUnit > .notranslate"));
        healthcareUnitElement.click();
        WebElement healthcareUnitOption = driver.findElement(By.xpath("//li[text()='" + healthcareUnit + "']"));
        healthcareUnitOption.click();
    }

    @When("I select Specialty {string}")
    public void i_select_specialty(String specialty) {
        WebElement specialtyElement = driver
                .findElement(By.cssSelector("#mui-component-select-medicalSpeciality > .notranslate"));
        specialtyElement.click();
        WebElement specialtyOption = driver.findElement(By.xpath("//li[text()='" + specialty + "']"));
        specialtyOption.click();
    }

    @When("I click on the search times button")
    public void i_click_on_the_search_times_button() {
        WebElement searchButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".MuiButton-root")));
        
        js.executeScript("arguments[0].scrollIntoView(true);", searchButton);
    
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        js.executeScript("arguments[0].click();", searchButton);
    }

    @Then("I should go to {string}")
    public void i_should_go_to(String url) {
        System.out.println("Waiting for URL to be " + url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe(url));
        System.out.println("URL is now " + driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().equals(url));
    }

    @When("I choose date {string}")
    public void i_choose_date(String date) {
        System.out.println("Attempting to choose date: " + date);
    
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".MuiDayCalendar-root")));
        
        String day = date.split(" ")[0];
        
        String dateXPath = String.format("//button[text()='%s']", day);
        
        WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateXPath)));
        js.executeScript("arguments[0].click();", dateElement);
        
        System.out.println("Chose date: " + date);
    }

    @When("I choose slot {string}")
    public void i_choose_slot(String slot) {
        System.out.println("Attempting to choose slot: " + slot);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Aumenta o tempo de espera para 30 segundos
        try {
            WebElement slotElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h6[contains(text(), '" + slot + "')]")));
            js.executeScript("arguments[0].click();", slotElement);
            System.out.println("Chose slot: " + slot);
        } catch (Exception e) {
            System.out.println("Failed to choose slot: " + slot);
            e.printStackTrace();
            throw e;
        }
    }

    @When("I click on the schedule appointment button")
    public void i_click_on_the_schedule_appointment_button() {
        WebElement scheduleButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".MuiButton-root")));
        System.out.println("Attempting to click the schedule button");

        js.executeScript("arguments[0].scrollIntoView(true);", scheduleButton);
        js.executeScript("window.scrollBy(0, -100);");

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(scheduleButton));

        js.executeScript("arguments[0].click();", scheduleButton);
    }

    @Then("I should go to home {string}")
    public void i_should_go_to_home(String url) {
        System.out.println("Waiting for URL to be " + url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe(url));
        System.out.println("URL is now " + driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().equals(url));
    }
}
