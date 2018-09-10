package ru.rshbsl.komita.app;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    @Test
    void convert() throws IOException, InvalidFormatException {
        String convert = Converter.convert(new FileInputStream("D:\\Project\\komita\\src\\doc\\Выгрузка операций 2.xlsx"));
        System.out.println(convert);
    }

}