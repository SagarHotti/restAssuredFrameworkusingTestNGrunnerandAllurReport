import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import commonFunctionPackage.Utility_commonFunction;

public class driverclass {

	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
	
		ArrayList<String> testCaseRun = Utility_commonFunction.readdataexcel("testcases", "Testcasetoexecute");
		
		int count =testCaseRun.size();
		System.out.println(count);
		for (int i=1;i<count;i++)
		{
		  String testcasename=testCaseRun.get(i);
		  Class<?> testclassname=Class.forName("testclasspackage."+ testcasename);
		  Method executemethod=testclassname.getDeclaredMethod("execute");
		  executemethod.setAccessible(true);
		  Object instanceotestclass=testclassname.getDeclaredConstructor().newInstance();
		  executemethod.invoke(instanceotestclass);
		}	  	

	}

}
