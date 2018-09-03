package ru.ovb.rsh;

import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.InputStream;

public class Load {
    @Test
    public void load() throws Exception {
        InputStream xlsx = this.getClass().getResourceAsStream("/Тест.xlsx");
        Workbook sheets = WorkbookFactory.create(xlsx);

        System.out.println(sheets.getNumberOfSheets());
        Sheet sheetAt = sheets.getSheetAt(0);

        for (int row=0; row<3; row++) {
            Row r = sheetAt.getRow(row);
            for (int c=0; c<3; c++){
                Cell cell = r.getCell(c);
                try {
                    System.out.println(cell.getStringCellValue());
                }catch (Exception ex){
                    System.out.println("["+ex.getClass()+"] "+ex.getMessage());
                }
                try {
                    System.out.println(cell.getNumericCellValue());
                }catch (Exception ex){
                    System.out.println("["+ex.getClass()+"] "+ex.getMessage());
                }
                try {
                    System.out.println(cell.getDateCellValue());
                }catch (Exception ex){
                    System.out.println("["+ex.getClass()+"] "+ex.getMessage());
                }
                System.out.println("==========");
            }
        }

        sheets.close();
    }
}
