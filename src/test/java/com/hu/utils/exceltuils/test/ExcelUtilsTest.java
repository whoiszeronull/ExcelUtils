package com.hu.utils.exceltuils.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import com.hu.utils.excelutils.ExcelUtils;
import com.hu.utils.excelutils.ExcelUtilsConfig;
import com.hu.utils.excelutils.ExcelUtilsImpl;
import com.hu.utils.excelutils.pojo.ExcelMapping;

public class ExcelUtilsTest {
	
	ExcelUtilsConfig euc = new ExcelUtilsConfig();
	ExcelUtils eu = new ExcelUtilsImpl();
	
	@Test
	public void readExcelToDatabaseTest() {
		
		ExcelMapping m =(ExcelMapping) this.euc.getMapping();
		
		System.out.println(m.toString());
		System.out.println(m.getExcelColumnNames().toString());
		
		File file =new File( m.getFile());
		
		ArrayList<ArrayList<Object>> datas = eu.readExcelSectionData(file, m.getSheetName(), m.getStartRow(), m.getEndRow(), m.getExcelColumnNames());
		
		Iterator<ArrayList<Object>> it = datas.iterator();
		
		while(it.hasNext()) {
			ArrayList<Object> next = it.next();
			System.out.println(next.toString());
		}
	}

}
