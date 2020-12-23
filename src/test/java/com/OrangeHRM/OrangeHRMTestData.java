package com.OrangeHRM;

import org.testng.annotations.DataProvider;

public class OrangeHRMTestData {

	
	@DataProvider(name = "LoginExcelData")
	public Object[][] Authentication() throws Exception{
		ReadDataFromExcel excel = new ReadDataFromExcel();
		String RelativePath = System.getProperty("user.dir");
		Object[][] testObjArray = excel.getExcelData(RelativePath+"\\TestData\\excel.xls","Sheet1");
		System.out.println(testObjArray);
		return testObjArray;

	}
	}
