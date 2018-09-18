package ru.rshbsl.komita.app;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.joda.time.LocalDate;
import ru.rshbsl.komita.schema.*;

import javax.xml.bind.JAXB;
import java.io.*;
import java.util.Date;
import java.util.List;

public class Converter {
    public static String convert(InputStream xls) throws IOException, InvalidFormatException {
        StringWriter bos = new StringWriter();

        Workbook sheets = WorkbookFactory.create(xls);

        Sheet sheetAt = sheets.getSheetAt(0);

        СведКлиент sv = new СведКлиент();
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
                    .withТелефон(getCellValueS(r, 5))
                    .withАдрРег(new АдресType()
                            .withКодОКСМ(getCellValueS(r, 6))
                            .withСтранаНаименование(getCellValueS(r, 7))
                            .withИндекс(getCellValueS(r, 8))
                            .withКодСубъектаПоОКАТО(getCellValueS(r, 9))
                            .withРайон(getCellValueS(r, 10))
                            .withПункт(getCellValueS(r, 11))
                            .withУлица(getCellValueS(r, 12))
                            .withДом(getCellValueS(r, 13))
                            .withКорп(getCellValueS(r, 14))
                            .withОф(getCellValueS(r, 15))

                    )
                    .withАдрПреб(new АдресType()
                            .withКодОКСМ(getCellValueS(r, 16))
                            .withСтранаНаименование(getCellValueS(r, 17))
                            .withИндекс(getCellValueS(r, 18))
                            .withКодСубъектаПоОКАТО(getCellValueS(r, 19))
                            .withРайон(getCellValueS(r, 20))
                            .withПункт(getCellValueS(r, 21))
                            .withУлица(getCellValueS(r, 22))
                            .withДом(getCellValueS(r, 23))
                            .withКорп(getCellValueS(r, 24))
                            .withОф(getCellValueS(r, 25))

                    )
                    .withКодОснМер(getCellValueS(r, 26))
                    .withДатаПеречня(getCellValueD(r, 27))
                    .withНомерПеречня(getCellValueS(r, 28))
                    .withНомерЗаписиПеречень(getCellValueS(r, 29))

                    .withДатаРешения(getCellValueD(r, 30))
                    .withНомерРешения(getCellValueS(r, 31))

                    .withДатаРезультат(getCellValueD(r, 32))
                    .withДатаНачалоОтн(getCellValueD(r, 33))
                    .withДатаЗаполнения(getCellValueD(r, 34))
                    .withИнаяИнф(getCellValueS(r, 35))
                    .withИнфСтепеньРиск(getCellValueS(r, 36))
                    .withПаспортВалид(getCellValueS(r, 37))
                    .withИнфЦельОтношения(getCellValueS(r, 38))
                    .withИнфХарактерОтношения(getCellValueS(r, 39))
                    .withИнфЦельФХД(getCellValueS(r, 40))
                    .withИнфРепутация(getCellValueS(r, 41))
//                    .with(getCellValueS(r, 42))
                    .withИнфФинансы(getCellValueS(r, 43))

                    .withФИОСотрудника(new ФИОType()
                            .withФам(getCellValueS(r, 44))
                            .withИмя(getCellValueS(r, 45))
                            .withОтч(getCellValueS(r, 46))
                    )
                    .withДолжностьСотрудника(getCellValueS(r, 47))
                    .withСтепеньРиска(getCellValueS(r, 48))

                    .withКлиентАктив(String.valueOf(getCellValueI(r, 0)))
                    .withДатаИдент(getCellValueD(r, 1))
                    .withТипКлиента(String.valueOf(getCellValueI(r, 2)))
                    .withПризнакРезидент(String.valueOf(getCellValueI(r, 3)))
                    .withКлиентКонтрагент(String.valueOf(getCellValueI(r, 4)))
                    .withСведОрг(
                            new СведОргType()
                                    .withСведенияФЛИП(
                                            new СведенияФЛ()
                                                    .withФИОФЛИП(
                                                            new ФИОType()
                                                                    .withИмя(getCellValueS(r, 50))
                                                                    .withФам(getCellValueS(r, 49))
                                                                    .withОтч(getCellValueS(r, 51))
                                                    )
                                                    .withИННФЛИП(getCellValueS(r, 52))
                                                    .withОКВЭДИП(getCellValueS(r, 53))
                                                    .withНаименРегОргана(getCellValueS(r, 54))
                                                    .withОГРНИП(getCellValueS(r, 55))
                                                    .withОКПО(getCellValueS(r, 56))
                                                    .withДатаРегИП(getCellValueD(r, 57))
                                                    .withКодОКСМ(String.valueOf(getCellValueI(r, 58)))
                                                    .withСтранаНаименование(String.valueOf(getCellValueS(r, 59)))
                                                    .withДатаРождения(getCellValueD(r, 60))
                                                    .withМестоРожд(new МестоРождType()
                                                            .withКодОКСМ(String.valueOf(getCellValueI(r, 61)))
                                                            .withКодСубъектаПоОКАТО(String.valueOf(getCellValueI(r, 62)))
                                                            .withРайон(getCellValueS(r, 63))
                                                            .withПункт(getCellValueS(r, 64))
                                                    )
                                                    .withВидГражданства(String.valueOf(getCellValueI(r, 65)))
                                                    .withСведДокУдЛичн(new СведДокУдЛичнType()
                                                            .withВидДокКод(String.valueOf(getCellValueI(r, 66)))
                                                            .withВидДокНаименование(getCellValueS(r, 67))
                                                            .withСерияДок(getCellValueS(r, 68))
                                                            .withНомДок(getCellValueS(r, 69))
                                                            .withДатВыдачиДок(getCellValueD(r, 70))
                                                            .withКемВыданДок(getCellValueS(r, 71))
                                                            .withКодПодр(getCellValueS(r, 72))
                                                            .withИноеНаименованиеДок(getCellValueS(r, 73))
                                                    )
                                                    .withСведМигрКарта(new СведМигрКартаType()
                                                            .withСерияДок(getCellValueS(r, 74))
                                                            .withНомДок(getCellValueS(r, 75))
                                                            .withДатаНачала(getCellValueD(r, 76))
                                                            .withДатаОкончания(getCellValueD(r, 77))
                                                    )
                                                    .withСведДокПраво(new СведДокПравоType()
                                                            .withВидДокКод(getCellValueS(r, 78))
                                                            .withСерияДок(getCellValueS(r, 79))
                                                            .withНомДок(getCellValueS(r, 80))
                                                            .withДатаНачала(getCellValueD(r, 81))
                                                            .withДатаОкончания(getCellValueD(r, 82))
                                                    )
                                            .withПризнакПринПубЛицо(getCellValueS(r, 83))
                                            .withПризнакРоссПубЛицо(getCellValueS(r, 84))
                                            .withПризнакИнострПубЛицо(getCellValueS(r, 85))
                                            .withИнойПризнак(getCellValueS(r, 86))
                                            .withРодство(getCellValueS(r, 87))
                                            .withДолжность(getCellValueS(r, 88))
                                            .withВидИдентификации(getCellValueS(r, 89))
                                            .withТипФЛЧастнаяПрактика(getCellValueS(r, 90))
                                            .withРегнНомер(getCellValueS(r, 91))
                                            .withСНИЛСФЛИП(getCellValueS(r, 92))
                                            .withОКВЭД2ИП(getCellValueS(r, 93))
                                            .withАдрРегИП(new Адрес2Type()
                                                    .withКодОКСМ("")
                                                    .withСтранаНаименование("")
                                            )


                                    )
                    )
            );
        }
        JAXB.marshal(sv, bos);
        return bos.toString();
    }

    private static String getCellValueS(Row r, int cell) {
        try {
            return r.getCell(cell).getStringCellValue();
        } catch (IllegalStateException ex) {
            return String.valueOf((int) r.getCell(cell).getNumericCellValue());
        }
    }

    private static Integer getCellValueI(Row r, int cell) {
        return (int) r.getCell(cell).getNumericCellValue();
    }

    private static LocalDate getCellValueD(Row r, int cell) {
        Date d = r.getCell(cell).getDateCellValue();
        if (d != null) {
            return new LocalDate(d.getTime());
        }
        return null;
    }

    private static boolean isEmpty(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BLANK:
                return true;
        }
        return false;
    }

}
