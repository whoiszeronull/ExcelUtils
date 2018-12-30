package com.hu.utils.excelutils.pojo;

public class ExcelMapping extends Mapping {

	private String file; // the excel file locaiton;
	private String sheetName; // the excel sheet name to be used
	private int startRow; // the start row number for reading the data;
	private int endRow; // the end row number for reading the data;

	public ExcelMapping() {
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	@Override
	public String toString() {
		return "ExcelMapping [file=" + file + ", sheetName=" + sheetName + ", startRow=" + startRow + ", endRow="
				+ endRow + "]";
	}



}
