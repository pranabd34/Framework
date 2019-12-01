package supportLibraries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.property.Property;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class SupportLibraries {
	public static Properties properties=null;
	public static String ScenarioName;
	public static String TCName;
	public static String MethodName;
	public static WebDriver driver=null;
	
	public static void runManagerInitiate() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		properties=loadProperty();
		File file=new File(properties.getProperty("Sharepath") + "datatables\\RunManager.xlsx");
		FileInputStream fileInputStream=new FileInputStream(file);
		Workbook wBook=new XSSFWorkbook(fileInputStream);
		Sheet sheet=wBook.getSheet("GeneralData");
		for (int j=1; j<sheet.getLastRowNum();j++)
		{
			Row row=sheet.getRow(j);
			for(int i=1; i<row.getLastCellNum(); i++)
			{
				ScenarioName=row.getCell(0).toString();
				TCName=row.getCell(i).toString();
				//System.out.println("Main Class" + ScenarioName + "  " + TCName);
				GetMethodInfo(ScenarioName,TCName);
				
			}
		}
		
		
	}
	
	public static Properties loadProperty() throws IOException{
		Properties prop=new Properties();
		BufferedReader buffReader=new BufferedReader(new FileReader("C:\\Users\\Pranab\\Desktop\\SeleniumFiles\\PranabFramework\\src\\GlobalSystem.property"));
		prop.load(buffReader);
		return prop;
	}
	
	public static void GetMethodInfo(String ScenarioName,String TCName) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		File file=new File(SupportLibraries.properties.getProperty("Sharepath")+ "datatables\\" + SupportLibraries.ScenarioName + ".xlsx");
		FileInputStream fileInputStream= new FileInputStream(file);
		Workbook wBook= new XSSFWorkbook(fileInputStream);
		Sheet sheet=wBook.getSheet("BusinessComponent");
		for(int i=1;i<sheet.getLastRowNum()+1;i++){
			Row row=sheet.getRow(i);
			if (TCName.equalsIgnoreCase(row.getCell(0).toString())){
				for(int j=1;j<row.getLastCellNum();j++){
					MethodName=row.getCell(j).toString();
					getMethod();
				}
			}
		}
	}
	
	public static void getMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		Method method=null;
		String ClassName="businessComponent." + ScenarioName;
		Class newclass= Class.forName(ClassName);
		Object obj=newclass.newInstance();
		method = obj.getClass().getMethod(MethodName);
		method.invoke(obj);
		//System.out.println(MethodName);
	}

}
