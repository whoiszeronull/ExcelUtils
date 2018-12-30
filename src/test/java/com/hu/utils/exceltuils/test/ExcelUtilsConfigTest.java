package com.hu.utils.exceltuils.test;

import java.io.File;
import java.util.HashMap;

import org.junit.Test;

import com.hu.utils.excelutils.ExcelUtilsConfig;
import com.hu.utils.excelutils.pojo.ExcelMapping;

public class ExcelUtilsConfigTest {
	
	ExcelUtilsConfig dbuc = new ExcelUtilsConfig();
	
	@Test
	public void getExcelMappingsTest() {
		ExcelMapping excelMapping = (ExcelMapping) dbuc.getMapping();
		
		System.out.println(excelMapping.toString());
		System.out.println(new File(excelMapping.getFile()).getAbsolutePath());
		HashMap<String,String> columnMappings = excelMapping.getColumnMappings();
		System.out.println(columnMappings);
		
		
	}

}
