package com.crossover.e2e;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.crossover.base.TestBase;
import com.crossover.page.GmailPage;

public class GMailTest extends TestBase
{
	GmailPage gmail;

	public GMailTest()
	{
		super();
	}

	@BeforeTest
	public void setUp() throws Exception
	{
		initialize();
		gmail = new GmailPage();
		
		gmail.logInToGmail();
		gmail.clickOnCompose();
	}

	@Test(priority = 1)
	public void testSendEmail() throws IOException, InterruptedException 
	{
		gmail.sendMail();
	}
	
	@Test(priority = 2, dependsOnMethods = "testSendEmail")
	public void verifyMail() throws IOException, InterruptedException 
	{
		gmail.waitforNewMailAndClick(properties.getProperty("subjectDetails"));
		ArrayList<String> arr = gmail.verifyEMail();
		Assert.assertEquals(arr.get(0), properties.getProperty("subjectDetails"),"Subject line mismatch");
		Assert.assertEquals(arr.get(1), properties.getProperty("bodyDetails"),"Email Body mismatch");
		Assert.assertEquals(arr.get(2), properties.getProperty("attachmentname"),"Attachment Name mismatch");
	}

	@AfterTest
	public void tearDown() 
	{
		driver.quit();
	}
}
