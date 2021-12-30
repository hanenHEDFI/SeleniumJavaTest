package utiles;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*
 * @author hanen.hedfi
 */

public class dataProvider {
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	
	/*
	 * *************Get Data
	 */
	public static Object[][] Data(String excelPath, String sheetName) throws Throwable {

		workbook = new XSSFWorkbook(excelPath);
		sheet = workbook.getSheet(sheetName);
		int rowCount = getRowCount();
		int colCount = getColCount();

		Object data[][] = new Object[rowCount - 1][colCount];
		for (int i = 1; i < rowCount; i++) {

			for (int j = 0; j < colCount; j++) {

				data[i - 1][j] = getCellDataString(i, j);

			}

		}
		return data;
	}

	/*
	 * *************Get Row Count
	 */
	public static int getRowCount() {

		return sheet.getPhysicalNumberOfRows();
	}

	/*
	 * *************Get Column Count
	 */
	public static int getColCount() {

		return sheet.getRow(0).getPhysicalNumberOfCells();
	}

	/*
	 * *************Get Cell Data String
	 */
	public static String getCellDataString(int row, int col) throws Exception {

		String strdata = null;

		if (sheet.getRow(row).getCell(col).getCellType() == CellType.STRING) {
			strdata = sheet.getRow(row).getCell(col).getStringCellValue();
		}

		else if (sheet.getRow(row).getCell(col).getCellType() == CellType.NUMERIC) {
			sheet.getRow(row).getCell(col).setCellType(CellType.STRING);
			strdata = sheet.getRow(row).getCell(col).getStringCellValue();
		}
		return strdata;
	}
}
