
package testScripts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import reports.Reports;
import supportLibraries.SupportLibraries;

public class TS1 {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		SupportLibraries.runManagerInitiate();
		Reports.generateReport();
	}

}
