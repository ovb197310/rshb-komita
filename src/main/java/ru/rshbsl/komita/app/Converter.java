package ru.rshbsl.komita.app;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import ru.rshbsl.komita.schema.ИнфКлиентType;
import ru.rshbsl.komita.schema.СведКлиентType;

import javax.xml.bind.JAXB;
import java.io.*;
import java.util.List;

public class Converter {
    public static String convert(InputStream xls) throws IOException, InvalidFormatException {
        StringWriter bos = new StringWriter();

        Workbook sheets = WorkbookFactory.create(xls);

        Sheet sheetAt = sheets.getSheetAt(0);

        СведКлиентType sv = new СведКлиентType();
        List<ИнфКлиентType> ai = sv.getИнфКлиент();

        for (int row = 4; ; row++) {

            Row r = sheetAt.getRow(row);
            if (r == null) {
                break;
            }
            Cell cell = r.getCell(0);
            if (isEmpty(cell)) {
                break;
            }

            ai.add(new ИнфКлиентType()
            );
        }
        JAXB.marshal(sv, bos);
        return bos.toString();
    }

    private static boolean isEmpty(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BLANK:
                return true;
        }
        return false;
    }

}
