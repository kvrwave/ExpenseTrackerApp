import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExport {

    public static void exportExpensesToExcel(List<Expense> expenses, String fileName) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Расходы");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Категория");
            headerRow.createCell(1).setCellValue("Сумма");
            headerRow.createCell(2).setCellValue("Дата");

            DataFormat dataFormat = workbook.createDataFormat();
            CellStyle amountCellStyle = workbook.createCellStyle();
            amountCellStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));

            CellStyle dateCellStyle = workbook.createCellStyle();
            short dateFormat = workbook.createDataFormat().getFormat("yyyy-mm-dd");
            dateCellStyle.setDataFormat(dateFormat);

            int rowNum = 1;
            for (Expense expense : expenses) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(expense.category());

                Cell amountCell = row.createCell(1);
                amountCell.setCellValue(expense.amount());
                amountCell.setCellStyle(amountCellStyle);

                Cell dateCell = row.createCell(2);
                dateCell.setCellValue(expense.date().toString());
                dateCell.setCellStyle(dateCellStyle);
            }

            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






