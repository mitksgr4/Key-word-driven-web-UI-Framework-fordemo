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
		String LocatorName=null;
		String LocatorValue=null;
		
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
			String locatorcolvalue=sheet.getRow(i+1).getCell(k+1).toString().trim();
			if(!locatorcolvalue.equalsIgnoreCase("NA")) 
			{
				LocatorName=locatorcolvalue.split("=")[0].trim(); //id
				LocatorValue=locatorcolvalue.split("=")[1].trim(); //username
			}
			
			String action=sheet.getRow(i+1).getCell(k+2).toString().trim();
			String value=sheet.getRow(i+1).getCell(k+3).toString().trim();
			
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
				
			/*case "quit":
			driver.quit();
			break; */

			default:
				break;
			}
			
			switch (LocatorName) {
			case "id":
				 element=driver.findElement(By.id(LocatorValue));
				if(action.equalsIgnoreCase("sendkeys"))
				{
					element.clear();
					element.sendKeys(value);
				}
				else if(action.equalsIgnoreCase("click")){
					element.click();
				}
				LocatorName=null;
				break;
				
			case "LinkText":
				 element=driver.findElement(By.linkText(LocatorValue));
				 element.click();
				LocatorName=null;
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
