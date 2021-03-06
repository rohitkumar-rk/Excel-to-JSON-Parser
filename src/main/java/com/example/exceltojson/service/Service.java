package com.example.exceltojson.service;

import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Service {
	
	
//	private static final String FILE_NAME = "G:/Internship/test.xlsx";
	
	public void convertExcelToJson(Workbook workbook) throws InvalidFormatException {
		
		//			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
		//			Workbook workbook = new XSSFWorkbook(file);
					Sheet userSheet = workbook.getSheetAt(0);
					int firstRowNum = userSheet.getFirstRowNum();
					int lastRowNo = userSheet.getLastRowNum();
					int noOfColumns = userSheet.getRow(0).getPhysicalNumberOfCells();
		
					
		//-----------------------    Getting Keys of JSON from first row of excel sheet  -----------
					
					
					
					String key[] = new String[noOfColumns];
					
					Row firstRow = userSheet.getRow(0);
					
					for(int col=0;col<noOfColumns;col++) {
						Cell cell = firstRow.getCell(col);
						key[col] = cell.getStringCellValue();
					}
					
					
					for(int i=0;i<noOfColumns;i++)
					System.out.println(key[i]);
		
					
		//---------------------------  Ends Here -----------------------------
					
		
		//-----------------------	Creating JSON   ---------------------------
					ObjectMapper mapper = new ObjectMapper();
					ArrayList<ObjectNode> users = new ArrayList<>();
		//			JsonNode node = mapper.createObjectNode();
					
		//			((ObjectNode) node).put("key","value");
		//			System.out.println(node);
					
					for(int row=1;row<=lastRowNo;row++) {
						JsonNode node = mapper.createObjectNode();
						Row currentRow = userSheet.getRow(row);
						for(int col=0;col<noOfColumns;col++) {
							Cell cell = currentRow.getCell(col);
							
		//					((ObjectNode) node).put(key[col],cell.getStringCellValue());
							
							if(cell.getCellTypeEnum() == CellType.STRING) {
								((ObjectNode) node).put(key[col],cell.getStringCellValue());
							} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
								((ObjectNode) node).put(key[col],cell.getNumericCellValue());
							}
							
		//					System.out.println(cell.getStringCellValue());
						}
						users.add((ObjectNode) node);
						
					}
					System.out.println(users);
		//			
		
		
	}
	

}
