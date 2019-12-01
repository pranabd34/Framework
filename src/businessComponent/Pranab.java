package businessComponent;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import objectRepository.ObjectRepository;
import reports.Reports;
import supportLibraries.SupportLibraries;

public class Pranab {
	WebDriver driver=SupportLibraries.driver;
	public void test1() throws IOException{
		Properties properties=SupportLibraries.loadProperty();
		
		Reports.log("Step1 validation", "StepDescription", "Done");
		
		System.setProperty("webdriver.ie.driver", properties.getProperty("IEDriver"));
		WebDriver driver=new InternetExplorerDriver();
		
		driver.manage().window().maximize();
		driver.get("www.google.com");
		Reports.log("Google Launch Validation", "Google has Launched", "Pass");
		
		driver.quit();
		
//		try{
//		driver.findElement(ObjectRepository.ObRepTest).click();;
//		//Reports.log("Step1 validation", "StepDescription", "Pass");
//		}
//		catch(Exception e){
//			System.out.println("Getting Excetion in test1"+e);
//		}
		
	}
	
	public void test2() throws IOException{
		Reports.log("Step1 validation", "StepDescription", "Done");
	}
	
	public void test3() throws IOException{
		Reports.log("Step validation of test2", "StepDescription", "Done");

	}
	
	public void test4() throws IOException{
		Reports.log("Step validation of test2", "StepDescription", "Done");

	}
}
