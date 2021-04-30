package com.selenium.pom.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class XLSReader {

	String filePath;
	int sheetIndex;
	public FileInputStream inputStream;
	public FileOutputStream outputStream;
	public HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cell;

	public XLSReader(String filePath) {
		this.filePath = filePath;
	}

	public XLSReader(String filePath, int sheetIndex) {
		this.filePath = filePath;
		this.sheetIndex = sheetIndex;
	}

	public int getRowCount(String sheetName) throws IOException {
		inputStream = new FileInputStream(filePath);
		workbook = new HSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
		int noOfRows = sheet.getLastRowNum();
		workbook.close();
		inputStream.close();
		return noOfRows;
	}

	public Object[][] getSheetData(String sheetName) throws IOException {
		inputStream = new FileInputStream(filePath);
		workbook = new HSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
		int lastRowNum = getRowCount(sheetName);
		int lastCellNum = getCellCount(sheetName, 0);
		Object[][] dataObject = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			Map<Object, Object> dataMap = new HashMap<Object, Object>();
			for (int j = 0; j < lastCellNum; j++) {
				String columnName = getCellData(sheetName, 0, j);
				String columnValue = getCellData(sheetName, i + 1, j);
				dataMap.put(columnName, columnValue);
			}
			dataObject[i][0] = dataMap;
		}
		return dataObject;
	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {
		inputStream = new FileInputStream(filePath);
		workbook = new HSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int noOfCells = row.getLastCellNum();
		workbook.close();
		inputStream.close();
		return noOfCells;
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		inputStream = new FileInputStream(filePath);
		workbook = new HSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			// converts to String object(data type)
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		inputStream.close();
		return data;
	}

	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File xlsFilePath = new File(filePath);
		isFileExists(xlsFilePath);

		inputStream = new FileInputStream(filePath);
		workbook = new HSSFWorkbook(inputStream);

		// getSheetIndex returns -1 if sheet not present or index value
		if (workbook.getSheetIndex(sheetName) == -1)
			// creates a new sheet with specified name
			workbook.createSheet(sheetName);

		// active sheet
		sheet = workbook.getSheet(sheetName);

		if (sheet.getRow(rowNum) == null)
			sheet.createRow(rowNum);
		row = sheet.getRow(rowNum);

		cell = row.createCell(colNum);
		cell.setCellValue(data);
		outputStream = new FileOutputStream(filePath);
		workbook.write(outputStream);
		workbook.close();
		inputStream.close();
		outputStream.close();
	}

	private void isFileExists(File xlsFilePath) throws IOException {
		if (!xlsFilePath.exists()) {
			workbook = new HSSFWorkbook();
			outputStream = new FileOutputStream(filePath);
			workbook.write(outputStream);
		}

	}

}
