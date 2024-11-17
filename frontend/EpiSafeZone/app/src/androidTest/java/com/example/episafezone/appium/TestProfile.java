package com.example.episafezone.appium;
// This sample code supports Appium Java client >=9
// https://github.com/appium/java-client
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class TestProfile {

    static AndroidDriver driver;

    @BeforeEach
    public void setUp() {
        BaseOptions options = new BaseOptions()
                .amend("appium:automationName", "UiAutomator2")
                .amend("appium:platformName", "Android")
                .amend("appium:platformVersion", "14")
                .amend("appium:deviceName", "emulator-5554")
                .amend("appium:app", "D:\\Documentos\\Universidad\\Asignaturas\\PIN\\episafezone-code\\frontend\\EpiSafeZone\\app\\build\\outputs\\apk\\debug\\app-debug.apk")
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(this.getUrl(), options);
    }

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void sampleTest() throws InterruptedException {
        //Select the child.
        WebElement el4 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Onofre\")"));
        el4.click();

        // Wait for 15 miliseconds, if network speed is low, increase the time.
        Thread.sleep(15);

        // Find the elements.
        WebElement el5 = driver.findElement(AppiumBy.id("com.example.episafezone:id/medicamentsRecycler"));
        WebElement e16 = driver.findElement(AppiumBy.id("com.example.episafezone:id/manifestRecycler"));

        // Find child elements.
        List<WebElement> childElementsMedication = el5.findElements(AppiumBy.className("android.view.ViewGroup"));
        List<WebElement> childElementsManifestation = e16.findElements(AppiumBy.className("android.view.ViewGroup"));

        // Check the number of child elements
        int numberOfChildElementsMedication = childElementsManifestation.size();
        System.out.println("Number of medications: " + numberOfChildElementsMedication);
        int numberOfChildElementsManifestation = childElementsMedication.size();
        System.out.println("Number of manifestations: " + numberOfChildElementsManifestation);

        // Assert that there is at least one child element
        assertTrue(numberOfChildElementsMedication > 0, "Medications not loaded.");
        assertTrue(numberOfChildElementsManifestation > 0, "Manifestations not loaded.");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}