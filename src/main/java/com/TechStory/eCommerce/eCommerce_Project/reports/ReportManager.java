package com.TechStory.eCommerce.eCommerce_Project.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportManager {

    private ExtentReports extentReports;
    private ExtentTest test;

    public ReportManager(String reportPath) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    public void startTest(String testName) {
        test = extentReports.createTest(testName);
    }

    public void log(String message) {
        test.info(message);
    }

    public void pass(String message) {
        test.pass(message);
    }

    public void fail(String message) {
        test.fail(message);
    }

    public void endTest() {
        extentReports.flush();
    }
}
