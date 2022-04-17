package utils;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public interface IExcelPoiParser {

    static Object[][] getData() {
        Map<String, String> valuesReadFromExcel = new HashedMap<>();

        FileInputStream fis;
        XSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(IPropertyReader.getPathForLanguageTestExcelFile());
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("LanguageTest")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
                Row firstRowWithColumnName = rows.next();
                Iterator<Cell> ce = firstRowWithColumnName.cellIterator();//row is collection of cells
                int iterator = 0;
                int languageColumnIndex = 0;
                int valueColumnIndex = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("language")) {
                        languageColumnIndex = iterator;
                    }
                    if (value.getStringCellValue().equalsIgnoreCase("value")) {
                        valueColumnIndex = iterator;
                    }
                    iterator++;
                }

                while (rows.hasNext()) {
                    Row row = rows.next();
                    Iterator<Cell> cells = row.cellIterator();
                    int counter = 0;
                    String languageCell = "";
                    String valueCell = "";
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if (languageColumnIndex == counter) {
                            languageCell = cell.getStringCellValue();
                        }

                        if (valueColumnIndex == counter) {
                            valueCell = cell.getStringCellValue();
                        }
                        counter++;
                        valuesReadFromExcel.put(languageCell, valueCell);
                    }
                }

            }
        }
        return
                valuesReadFromExcel.entrySet().stream()
                        .map(e -> new Object[]{e.getKey(), e.getValue()})
                        .toArray(Object[][]::new);
    }
}


