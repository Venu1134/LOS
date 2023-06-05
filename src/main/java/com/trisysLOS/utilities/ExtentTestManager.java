package com.trisysLOS.utilities;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentReporter.generateExtentReporter();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getPriority());
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getPriority(), test);
        return test;
    }
}
