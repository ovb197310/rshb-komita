package ru.rshbsl.komita;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.io.StringWriter;

public class СведКлиентTypeTest {

    @Test
    public void xml() throws Exception {

        СведКлиентType x = new СведКлиентType();
        ИнфКлиентType c = new ИнфКлиентType();
        x.getИнфКлиент().add(c);

        c.withАдресType("Тип адреса")
        .withДатаЗаполнения(LocalDate.now())
        ;
        StringWriter sw = new StringWriter();
        JAXB.marshal(x, sw);
        System.out.println(sw.toString());

        СведКлиентType unm = JAXB.unmarshal(new StringReader(sw.toString()), СведКлиентType.class);
        Assert.assertEquals(LocalDate.now(), unm.getИнфКлиент().get(0).getДатаЗаполнения());
        System.out.println(unm.getИнфКлиент().get(0).getДатаЗаполнения());

        System.out.println(unm);
    }
}