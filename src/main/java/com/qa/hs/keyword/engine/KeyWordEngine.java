package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.Base;

public class KeyWordEngine {

	public WebDriver driver;
	public Properties prop;
	
	public static Workbook book;
	public static Sheet sheet;
	
	public Base base;
	
	WebElement element;
	
	public final String SCENARIO_SHEET_PATH="D:\\selenium\\login.xlsx";
	
	public void startExecution(String SheetName)
	{
		//String locatorType=null;
		//String locatorValue=null;
		
		FileInputStream file=null;
		try {
			file= new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			book=WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet=book.getSheet(SheetName);
		int k=0;
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			try {
			String locatorType=sheet.getRow(i+1).getCell(k+1).toString().trim();
			String locatorValue=sheet.getRow(i+1).getCell(k+2).toString().trim();
			String action=sheet.getRow(i+1).getCell(k+3).toString().trim();
			String value=sheet.getRow(i+1).getCell(k+4).toString().trim();
			
			switch (action) {
			case "open browser":
				base=new Base();
				base.init_properties();
				if(value.isEmpty() || value.equals("NA"))
				{
					driver=base.init_driver(prop.getProperty("browser"));
				}
				else
				{
					driver=base.init_driver(value);
				}
				break;
				
			case "enter url":
				if(value.isEmpty() || value.equals("NA"))
				{
					driver.get(prop.getProperty("url"));
				}
				else
				{
					driver.get(value);
				}
				break;
				
			case "quit":
			driver.quit();
			break; 

			default:
				break;
			}
			
			switch (locatorType) {
			case "id":
				 element=driver.findElement(By.id(locatorValue));
				if(action.equalsIgnoreCase("sendkeys"))
				{
					element.clear();
					element.sendKeys(value);
				}
				else if(action.equalsIgnoreCase("click")){
					element.click();
				}
				else if(action.equalsIgnoreCase("isDisplayed"))
				{
					element.isDisplayed();
				}
				else if(action.equalsIgnoreCase("getText"))
				{
					String elementText=element.getText();
					Thread.sleep(2000);
					System.out.println("text of element :"+elementText);
				}
				locatorType=null;
				break;
				
			case "name":
				 element=driver.findElement(By.name(locatorValue));
				if(action.equalsIgnoreCase("sendkeys"))
				{
					element.clear();
					element.sendKeys(value);
				}
				else if(action.equalsIgnoreCase("click")){
					element.click();
				}
				else if(action.equalsIgnoreCase("isDisplayed"))
				{
					element.isDisplayed();
				}
				else if(action.equalsIgnoreCase("getText"))
				{
					String elementText=element.getText();
					System.out.println("text of element :"+elementText);
				}
				locatorType=null;
				break;
				
			case "xpath":
			element=driver.findElement(By.xpath(locatorValue));
			if(action.equalsIgnoreCase("sendkeys"))
			{
				element.clear();
				element.sendKeys(value);
			}
			else if(action.equalsIgnoreCase("click")){
				element.click();
			}
			else if(action.equalsIgnoreCase("isDisplayed"))
			{
				element.isDisplayed();
			}
			else if(action.equalsIgnoreCase("getText"))
			{
				String elementText=element.getText();
				System.out.println("text of element :"+elementText);
			}
			locatorType=null;
			break;
			
			case "cssSelector":
				element=driver.findElement(By.cssSelector(locatorValue));
				if(action.equalsIgnoreCase("sendkeys"))
				{
					element.clear();
					element.sendKeys(value);
				}
				else if(action.equalsIgnoreCase("click")){
					element.click();
				}
				else if(action.equalsIgnoreCase("isDisplayed"))
				{
					element.isDisplayed();
				}
				else if(action.equalsIgnoreCase("getText"))
				{
					String elementText=element.getText();
					System.out.println("text of element :"+elementText);
				}
				locatorType=null;
				break;
				
			case "LinkText":
				 element=driver.findElement(By.linkText(locatorValue));
				 element.click();
				 locatorType=null;
				 break;
				
			case "partialLinkText":
				 element=driver.findElement(By.partialLinkText(locatorValue));
				 element.click();
				 locatorType=null;
				 break;

			default:
				break;
			}
			}
			catch (Exception e) {
				
			}
			
			
			
			
		}
		
	}
}
