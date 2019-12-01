package supportLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataTables {
	 static int ColNum;
	 static String ExceValue;
	public static String getData(String SheetName, String ColumnName) throws IOException{
		//String fileName=SupportLibraries.properties.getProperty("Sharepath") + "datatables\\" + SupportLibraries.ScenarioName + ".xlsx";
		//System.out.println(fileName);
		File file=new File(SupportLibraries.properties.getProperty("Sharepath")+ "datatables\\" + SupportLibraries.ScenarioName + ".xlsx");
		FileInputStream fileInputStream= new FileInputStream(file);
		Workbook wBook= new XSSFWorkbook(fileInputStream);
		Sheet sheet=wBook.getSheet("SheetName");
		Row row1=sheet.getRow(0);
		for (int j=1;j<row1.getLastCellNum();j++){
		String ColName=row1.getCell(j).toString();
		if (ColName.equalsIgnoreCase(ColumnName)){
			ColNum=j;
			break;
		}
		
		}
		for(int i=1;i<sheet.getLastRowNum();i++){
			Row row=sheet.getRow(i);
			String TCname=row.getCell(0).toString();
			if (TCname.equalsIgnoreCase(SupportLibraries.TCName)){
				
				String ExcelValue=row.getCell(ColNum).toString();
			}
				
		}
		return ExceValue;
	}

}
