package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browserName)
	{
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","D:\\Driver\\chromedriver.exe");
			if(prop.getProperty("headless").equals("yes"))
			{
				//headless mode:
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver=new ChromeDriver(options);
			}
			else
			{
				driver=new ChromeDriver();
			}
		}
		
		if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","D:\\Driver\\geckodriver.exe");
			
			
				driver=new FirefoxDriver();
			
		}
		return driver;
	}
	
	public Properties init_properties()
	{
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("C:\\Users\\mitks\\eclipse-workspace\\KeyWordDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	

}
