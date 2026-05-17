package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println("======================================== STARTING TEST loginTest ========================================");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("======================================== FINISHED TEST loginTest Duration: 5s ========================================");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("======================================== FAILED TEST loginTest Duration: 3s ========================================");

        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        AllureUtils.takeScreenshot(driver);
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("======================================== SKIPPING TEST loginTest ========================================");
    }
}
