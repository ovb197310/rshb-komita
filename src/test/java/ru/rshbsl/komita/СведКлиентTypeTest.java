package ru.rshbsl.komita;

import org.junit.Test;

import javax.xml.bind.JAXB;

import static org.junit.Assert.*;

public class СведКлиентTypeTest {

    @Test
    public void xml() throws Exception {

        СведКлиентType x = new СведКлиентType();
        ИнфКлиентType c = new ИнфКлиентType();
        x.getИнфКлиент().add(c);

        c.withАдресType("Тип адреса");
        JAXB.marshal(x, System.out);
    }
}