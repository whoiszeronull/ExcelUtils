package com.hu.utils.excelutils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hu.utils.excelutils.pojo.ExcelMapping;
import com.hu.utils.excelutils.pojo.Mapping;

public class ExcelUtilsConfig {

	public static final String MAPPING_TYPE_EXCEL = "excel";

	private String configFile = "config/excelutils-config.xml";  
	
	private SAXReader reader = new SAXReader();

	public ExcelUtilsConfig() {
	}

	public ExcelUtilsConfig(String configFile) {
		this.configFile = configFile;
	}

	/**
	 * get the Mapping config object by using the default file location, classpath:
	 * config/dBaseUtilsConfig.xml.
	 * 
	 * @return
	 */
	public Mapping getMapping() {
		return this.getMapping(this.configFile);
	}

	/**
	 * ' get the Mapping config object based on the given configFile;
	 * 
	 * @param configFile
	 * @return
	 */
	public Mapping getMapping(String config) {
		Mapping m = new Mapping();
		Document doc;
		try {
			this.configFile = config;
			
//			doc = reader.read(this.configFile);
			doc = reader.read(this.getClass().getClassLoader().getResource(configFile).getPath());

			String mappingsDefaultAttr = this.getMappingsDefaultAttr(doc);
			Element mappingTypeElement = (Element) doc
					.selectSingleNode("//mapping-type[@id='" + mappingsDefaultAttr + "']");
			String mappingType = mappingTypeElement.attributeValue("type");

			// use the corresponding method for getting the mappings depending on the
			// mapping type;
			switch (mappingType) {
			// Excel mapping type handing:
			case ExcelUtilsConfig.MAPPING_TYPE_EXCEL:
				m = getExcelMapping(mappingTypeElement);
			default:
				;
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return m;
	}

	private Mapping getExcelMapping(Element e) {

		ExcelMapping em = new ExcelMapping();
		em.setMappingType(ExcelUtilsConfig.MAPPING_TYPE_EXCEL);

		em.setFile(e.attributeValue("file"));
		em.setSheetName(e.attributeValue("sheetName"));
		em.setStartRow(Integer.parseInt(e.attributeValue("startRow")));
		em.setEndRow(Integer.parseInt(e.attributeValue("endRow")));

		HashMap<String, String> cm = em.getColumnMappings();
		List elements = e.elements("mapping");
		Iterator it = elements.iterator();
		while (it.hasNext()) {
			Element me = (Element) it.next();
			cm.put(me.attributeValue("database-column"), me.attributeValue("excel-column"));
		}

		return em;
	}

	private String getMappingsDefaultAttr(Document doc) {

		try {
			String mappingsDefAtt = ((Element) doc.selectSingleNode("//mappings")).attributeValue("default");

			return mappingsDefAtt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

}
