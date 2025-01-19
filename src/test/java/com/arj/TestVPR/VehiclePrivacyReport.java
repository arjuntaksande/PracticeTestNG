package com.arj.TestVPR;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VehiclePrivacyReport {
     WebDriver driver;
     public String expectedVIN = "1N4AL3AP8DN471756";
     public String Identifiers = "yes";
     public String Biometrics = "yes";

     // Initialize WebDriver before each test method
     @BeforeMethod
     public void launchBrowser() {
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.get("https://vehicleprivacyreport.com/");
     }

     @Test
     public void verifyVIN() throws InterruptedException {
    	 Thread.sleep(5000);

         driver.findElement(By.xpath("//input[@name='input_1']")).sendKeys(expectedVIN);
         driver.findElement(By.xpath("//input[@type='submit']")).click();    
         WebElement actualVIN = driver.findElement(By.xpath("//*[@id=\"js-vin\"]"));
         String getactualVINText = actualVIN.getText();
         assertEquals(getactualVINText, expectedVIN, "VIN is Mismatched");
         driver.quit();
     }

     @Test
     public void verifyIdentifier() throws InterruptedException {
    	 Thread.sleep(5000);
    	 
         WebElement valueIdentifiers = driver.findElement(By.xpath("//*[@id=\"js-collectsRow\"]/div[1]/div/p"));
         String TextvalueIdentifiers = valueIdentifiers.getText();
         assertEquals(TextvalueIdentifiers, Identifiers, "Identifier is Mismatched");    
         System.out.println(valueIdentifiers.getText());
     }

     @Test
     public void verifyBiometrics() throws InterruptedException {
    	 Thread.sleep(5000);
    	 
         WebElement valueBiometrics = driver.findElement(By.xpath("//*[@id=\"js-collectsRow\"]/div[2]/div/p"));
         String TextvalueBiometrics = valueBiometrics.getText();
         assertEquals(TextvalueBiometrics, Biometrics, "Biometrics is Mismatched is not Yes");
         System.out.println(valueBiometrics.isDisplayed());
         System.out.println(valueBiometrics.getText());
     }

     // Optional: Clean up after tests (close the browser)
     @AfterMethod
     public void tearDown() {
         if (driver != null) {
             driver.quit();
         }
     }
}
