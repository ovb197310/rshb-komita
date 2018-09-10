package ru.rshbsl.komita.formatter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateFormatter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter DTF = DateTimeFormat.forPattern("dd/MM/yyyy");

    public static String print(LocalDate value) {
        if (value == null) {
            return "";
        }

        return DTF.print(value);
    }

    public static LocalDate parse(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        return DTF.parseLocalDate(value);
    }


    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return print(v);
    }
}
