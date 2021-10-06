package com.pratesting.Utils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.pratesting.Utils.ExtentReportManager;
import com.pratesting.base.BaseUI;

public class ListenerUtils extends TestListenerAdapter {
	public static ExtentReports extent;
	public static ExtentTest logger;

	/********** Getting extent report instance on start of test class ***********/
	public void onStart(ITestContext testContext) {
		extent = ExtentReportManager.getReportInstance();
	}

	/********** Creating test in extent report on start of each test ***********/
	public void onTestStart(ITestResult result) {
		logger = extent.createTest(result.getName());
		BaseUI.logger = logger;
		
	}

	/********** Marking test as passed in extent report ***********/
	public void onTestSuccess(ITestResult result) {
		logger.log(Status.PASS,
				MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		logger.log(Status.PASS, "Testcase passed");	
	}

	/********** Marking test as failed in extent report ***********/
	public void onTestFailure(ITestResult result) {
		logger.log(Status.FAIL,
				MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		logger.log(Status.FAIL, result.getThrowable());
		
	}

	/********** Marking test as skipped in extent report ***********/
	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP,
				MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	/********** Flushing the extent report at the end of test class ***********/
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
