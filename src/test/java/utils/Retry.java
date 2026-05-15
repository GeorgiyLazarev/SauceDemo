package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int counter = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < MAX_RETRY_COUNT) {
            counter++;
            System.out.println("Retrying test: " + result.getName() + " (Attempt " + counter + ")");
            return true;
        }
        return false;
    }
}
