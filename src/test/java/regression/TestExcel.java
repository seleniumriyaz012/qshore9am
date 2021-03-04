package regression;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestExcel {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis=new FileInputStream("src\\test\\resources\\testdata\\excel\\LoginCredentials.xlsx");
		
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		
		XSSFSheet ws=wb.getSheet("Sheet1");
		
		System.out.println(ws.getLastRowNum());
		
		System.out.println(ws.getRow(0).getPhysicalNumberOfCells());
		
		for(int i=0;i<ws.getLastRowNum();i++)
		{
			
			for(int j=0;j<ws.getRow(0).getPhysicalNumberOfCells();j++)
			{
			System.out.print(ws.getRow(i+1).getCell(j).getStringCellValue()+"\t");
			
			}
			System.out.println();
			
			
		}
	}

}
