package com.hu.utils.excelutils;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import com.hu.utils.excelutils.pojo.ExcelMapping;

public interface ExcelUtils {

	/**
	 * Save the data from excel file to the database based on the DBaseUtilsConfig.
	 * 
	 * @param dbuc
	 *            a data base util config object
	 * @return the quantity of rows which have been saved into the database.
	 */
	public int saveFromExcelToDB(ExcelUtilsConfig dbuc);

	/**
	 * save the data from excel file to the database by using defual config file
	 * which located in the classpath: config/dBaseUtisConfig.xml
	 * 
	 * @return
	 */
	public int saveFromExcelToDB();

	/**
	 * 
	 * @param em
	 * @return
	 */
	public  ArrayList<ArrayList<Object>> readExcelSectionData(ExcelMapping em);
	
	
	/**
	 * read one section data from given excelfile,sheetName, and the startRow
	 * number/endrow number, the excel columns needed are included in the
	 * collection: excelCols with column names, such as : A, B, C....
	 * 
	 * @param excelFile
	 * @param sheetName
	 * @param startRow
	 * @param endRow
	 * @param excelCols
	 *            the column names needed on the given excel sheet.
	 * @return
	 */
	public  ArrayList<ArrayList<Object>> readExcelSectionData(File excelFile, String sheetName, int startRow,
			int endRow, Set<String> excelCols);
}