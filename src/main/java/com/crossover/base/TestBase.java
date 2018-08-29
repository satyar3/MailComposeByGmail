package com.crossover.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase
{
	protected static WebDriver driver;
	protected Properties properties;
	protected FileInputStream fs;
	
	public TestBase()
	{
		properties = new Properties();
		try
		{
			fs = new FileInputStream("src\\main\\java\\com\\crossover\\properties\\test.properties");
		}
		catch (FileNotFoundException e)
		{

			e.printStackTrace();
		}
		try
		{
			properties.load(fs);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void initialize()
	{
		System.setProperty("webdriver.chrome.driver","src\\main\\java\\com\\crossover\\exefiles\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://mail.google.com/");	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
