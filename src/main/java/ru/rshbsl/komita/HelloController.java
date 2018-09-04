package ru.rshbsl.komita;

import org.joda.time.LocalDate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping(value = "/"/*, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public ResponseEntity<?> index() {
//    public СведКлиентType index() {
        СведКлиентType res = new СведКлиентType();
        res.getИнфКлиент().add(
                new ИнфКлиентType()
                        .withДатаЗаполнения(LocalDate.now())
                        .withСведОрг(
                                new СведОргType()
                                        .withСведенияФЛИП(
                                                new СведенияФЛ()
                                                        .withДатаРождения(new LocalDate(1973, 10, 29))
                                                        .withФИОФЛИП(
                                                                new ФИОType()
                                                                        .withИмя("Олег")
                                                                        .withОтч("Викторович")
                                                                        .withФам("Балицкий")
                                                        )
                                        )
                        )
        );
//        return res;
        return ResponseEntity.ok(res);
    }

}