package reports;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Spliterator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.steadystate.css.util.Output;

import businessComponent.Pranab;
import supportLibraries.SupportLibraries;

public class Reports {
	public static ArrayList<String> ScrShotArrList=new ArrayList<String>();
	public static WebDriver driver=SupportLibraries.driver;
	
	public static void log(String StepName, String StepDescription, String Status) throws IOException{
		if (Status.equalsIgnoreCase("Pass") || Status.equalsIgnoreCase("Fail")){
			
			SimpleDateFormat tmpstmp=new SimpleDateFormat("MMDDYYYY_HHMMSS");	
			Properties property=SupportLibraries.loadProperty();
			
			TakesScreenshot scrshot=((TakesScreenshot)driver);
			String scrShotName=null;
			if (SupportLibraries.MethodName.isEmpty()){
				scrShotName=tmpstmp+".png";
			}
			else{
				scrShotName=SupportLibraries.MethodName+tmpstmp+".png";
			}
			File srcFile=scrshot.getScreenshotAs(OutputType.FILE);
			File destFile=new File(property.getProperty("ScreenshotPath")+scrShotName);
			FileUtils.copyFile(srcFile, destFile);

			ScrShotArrList.add(SupportLibraries.TCName+","+ SupportLibraries.MethodName+ "," + StepName + "," + StepDescription +"," + Status+","+scrShotName);
	}
		
		ScrShotArrList.add(SupportLibraries.TCName+","+ SupportLibraries.MethodName+ "," + StepName + "," + StepDescription +"," + Status+","+" ");
	}
	
	public static void generateReport() throws IOException{
		Properties properties=SupportLibraries.loadProperty();
		SimpleDateFormat tmpstmp=new SimpleDateFormat("MMDDYYYY_HHMMSS");
		File Reportfile=new File (properties.getProperty("ScreenshotPath")+"\\Execution Report_"+tmpstmp+"\\");
		if (Reportfile.exists()){}
		else{
			Reportfile.mkdirs();
		}
		PrintWriter printWriter=new PrintWriter(properties.getProperty("ScreenshotPath")+"\\Execution Report_"+tmpstmp+"\\"+"Execution Report.txt");
		for (String i:ScrShotArrList){
			String eachScrShot[]=i.split(",");
			for (int j=0;j<=eachScrShot.length-1;j++){
				printWriter.print(eachScrShot[j]);
			}
			printWriter.println();
			
		}
		printWriter.close();;
		
		
		
	}
	
}
