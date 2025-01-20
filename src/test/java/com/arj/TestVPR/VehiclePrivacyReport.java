package com.arj.TestVPR;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VehiclePrivacyReport {
    WebDriver driver;
    public String expectedVIN = "1N4AL3AP8DN471756";
    public String Identifiers = "YES";
    public String Biometrics = "YES";

    @BeforeTest
    public void setUpEnvironment() {
        WebDriverManager.chromedriver().setup();
        System.out.println("WebDriver Manager Setup Completed");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Starting Vehicle Privacy Report Tests");
    }

    @BeforeMethod
    public void launchBrowser() {
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
    }

    @Test
    public void verifyIdentifier() throws InterruptedException {
        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@name='input_1']")).sendKeys(expectedVIN);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        WebElement actualVIN = driver.findElement(By.xpath("//*[@id=\"js-vin\"]"));
        String getactualVINText = actualVIN.getText();
        assertEquals(getactualVINText, expectedVIN, "VIN is Mismatched");
        
        WebElement valueIdentifiers = driver.findElement(By.xpath("//*[@id=\"js-collectsRow\"]/div[1]/div/p"));
        String TextvalueIdentifiers = valueIdentifiers.getText();
        assertEquals(TextvalueIdentifiers, Identifiers, "Identifier is Mismatched");
        System.out.println("TextvalueIdentifiers "+TextvalueIdentifiers);
    }

    @Test
    public void verifyBiometrics() throws InterruptedException {
        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@name='input_1']")).sendKeys(expectedVIN);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        WebElement actualVIN = driver.findElement(By.xpath("//*[@id=\"js-vin\"]"));
        String getactualVINText = actualVIN.getText();
        System.out.println("getactualVINText "+getactualVINText);
        assertEquals(getactualVINText, expectedVIN, "VIN is Mismatched");

        WebElement valueBiometrics = driver.findElement(By.xpath("//*[@id=\"js-collectsRow\"]/div[2]/div/p"));
        String TextvalueBiometrics = valueBiometrics.getText();
        System.out.println("TextvalueBiometrics "+TextvalueBiometrics);     
        if (TextvalueBiometrics.equalsIgnoreCase("YES")) {
            System.out.println("Biometrics verification passed: TextvalueBiometrics is YES.");
        } else if (TextvalueBiometrics.equalsIgnoreCase("SILENT")) {
            System.out.println("Biometrics verification note: TextvalueBiometrics is SILENT. Expected behavior might vary.");
        } else {
            System.out.println("Biometrics verification failed. Received: " + TextvalueBiometrics);
            Assert.fail("Biometrics is Mismatched. Expected: 'YES' or 'SILENT', but received: " + TextvalueBiometrics);
        }     
        System.out.println(valueBiometrics.isDisplayed());
        System.out.println(valueBiometrics.getText());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Completed Vehicle Privacy Report Tests");
    }
}
