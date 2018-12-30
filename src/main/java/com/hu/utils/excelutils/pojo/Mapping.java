package com.hu.utils.excelutils.pojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Mapping {
	
	//the mapping type, such as Excel mapping in the confi xml file.
	private String mappingType;
	
	// a map that contains key as database column, and value as excel(or...) column;
	private HashMap<String, String> columnMappings = new HashMap<String, String>();
	
	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}

	public HashMap<String, String> getColumnMappings() {
		return columnMappings;
	}

	public void setColumnMappings(HashMap<String, String> columnMappings) {
		this.columnMappings = columnMappings;
	}
	
	public Set<String> getExcelColumnNames() {
		return new HashSet<String>(this.columnMappings.values());
	}
	
	public Set<String> getDatabaseColumnNames() {
		return this.columnMappings.keySet();
	}

}
