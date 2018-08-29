package com.crossover.page;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crossover.base.TestBase;

public class GmailPage extends TestBase
{
	public void logInToGmail()
	{
		WebElement userElement = driver.findElement(By.id(properties.getProperty("usernamefield")));
		userElement.sendKeys(properties.getProperty("username"));
		driver.findElement(By.id(properties.getProperty("usernamenextbutton"))).click();

		WebElement passwordElement = driver.findElement(By.name(properties.getProperty("passwordfield")));
		passwordElement.sendKeys(properties.getProperty("password"));
		driver.findElement(By.id(properties.getProperty("passwordnextbutton"))).click();
	}

	public void clickOnCompose()
	{
		WebElement composeElement = driver.findElement(By.xpath(properties.getProperty("composebutton")));
		composeElement.click();
	}

	public void sendMail() throws IOException, InterruptedException
	{
		//Receipent
		driver.findElement(By.name("to")).sendKeys(String.format("%s@gmail.com", properties.getProperty("username")));
		
		//Subject
		driver.findElement(By.xpath(properties.getProperty("subjectelement"))).sendKeys(properties.getProperty("subjectDetails"));
		
		//Body
		driver.findElement(By.xpath(properties.getProperty("bodyelement"))).sendKeys(properties.getProperty("bodyDetails"));
		
		//Attachment
		driver.findElement(By.xpath(properties.getProperty("attachmentbutton"))).click();
		Thread.sleep(5000);
		
		//Calling AutoIT exe
		Runtime.getRuntime().exec("src\\main\\java\\com\\crossover\\exefiles\\dynamicfileupload.exe");
		Thread.sleep(5000);
		
		driver.findElement(By.xpath(properties.getProperty("sendbutton"))).click();
	}
	
	public void waitforNewMailAndClick(String subject)
	{
		new WebDriverWait(driver, 10).ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@class='bog']/*[@class='bqe' and text()='"+subject+"']")))).click();
	}
	
	public ArrayList<String> verifyEMail()
	{
		String subject = driver.findElement(By.xpath(properties.getProperty("subjectxpath"))).getText();
		String body = driver.findElement(By.xpath(properties.getProperty("bodyxpath"))).getText();
		String attachmentname = driver.findElement(By.xpath(properties.getProperty("attachmentxpath"))).getText();
		
		ArrayList<String> arr = new ArrayList<>();
		
		arr.add(subject);
		arr.add(body);
		arr.add(attachmentname);
		
		return arr;
	}
}
