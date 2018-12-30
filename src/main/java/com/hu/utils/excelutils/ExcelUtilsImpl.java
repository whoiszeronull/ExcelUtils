package com.hu.utils.excelutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellAddress;

import com.hu.utils.excelutils.pojo.ExcelMapping;

public class ExcelUtilsImpl implements ExcelUtils {

	@Override
	public int saveFromExcelToDB(ExcelUtilsConfig dbuc) {
		ExcelMapping em = (ExcelMapping) dbuc.getMapping();
		ArrayList<ArrayList<Object>> rows = this.readExcelSectionData(em);
		System.out.println(rows.toString());
		return 0;
	}

	@Override
	public int saveFromExcelToDB() {
		return this.saveFromExcelToDB(new ExcelUtilsConfig());
	}

	@Override
	public ArrayList<ArrayList<Object>> readExcelSectionData(ExcelMapping em) {
		File excelFile = new File(em.getFile());
		String sheetName = em.getMappingType();
		int startRow = em.getStartRow();
		int endRow = em.getEndRow();
		return readExcelSectionData(excelFile, sheetName, startRow, endRow, em.getExcelColumnNames());
	}

	@Override
	public ArrayList<ArrayList<Object>> readExcelSectionData(File excelFile, String sheetName, int startRow, int endRow,
			Set<String> excelCols) {

		ArrayList<ArrayList<Object>> rows = new ArrayList<>();

		int max = Math.max(startRow, endRow);
		int min = Math.min(startRow, endRow);

		try (Workbook wb = WorkbookFactory.create(excelFile);) {

			Sheet sheet;
			sheet = wb.getSheet(sheetName);

			//���û���ҵ���Ӧ��excel sheet�Ļ�����Ĭ�϶�ȡ��һ��sheet.
			if (sheet == null) {
				sheet = wb.getSheetAt(0);
			}

				for (int i = min; i <= max; i++) {

					Row row = sheet.getRow(i);

					// create a arrayList to hold this row's each column data.
					ArrayList<Object> rowCells = new ArrayList<>();

					Iterator<String> it = excelCols.iterator();

					while (it.hasNext()) {
						String columnName = it.next();

						CellAddress ca = new CellAddress(columnName + i);

						Cell cell = row.getCell(ca.getColumn());

						Object cellValue = null;

						if (cell != null) {

							CellType cellType = cell.getCellType();

							// convert the cellValue into string depends on the cellType.
							switch (cellType) {
							case BLANK:
								cellValue = "";
								break;

							case BOOLEAN:
								cellValue = cell.getBooleanCellValue();
								break;

							case ERROR:
								cellValue = cell.getErrorCellValue();
								break;

							case FORMULA:
								cellValue = cell.getCellFormula();
								break;

							case NUMERIC:
								cellValue = cell.getNumericCellValue();
								break;

							case STRING:
								cellValue = cell.getStringCellValue();
								break;

							// This one is pending for processing the cell values.
							case _NONE:
								cellValue = cell.getStringCellValue();
								break;

							default:
								cellValue = cell.getStringCellValue();
								break;
							}
						}
						rowCells.add(cellValue);
					}

					rows.add(rowCells);

				}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

}
