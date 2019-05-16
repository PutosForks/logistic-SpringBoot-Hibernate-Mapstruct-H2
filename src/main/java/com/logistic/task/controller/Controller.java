package com.logistic.task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 08.05.2019
 */
@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/")
public class Controller {

    @GetMapping
    public String mainMapping() {
        return "get /clients - вывод всех клиетов сохраненных в базе"+ "\n"
                +"get /clients/5 - вывод всех клиета с ID 5 "+ "\n"
                +"post /clients/ - сохранение переданного Json в объект и в базу" +"\n"
                +"put /clients/ID - обновление переданого в Json обекта по ID" +"\n"
                +"delete /clients/ID - удаление клиента по ID"+
                "\n"+
                "all entitys - Client\n" +
                "     Address\n" +
                "   Crew\n" +
                "    Flight\n" +
                "    Person\n" +
                "    Shift\n" +
                "    Vehicle\n" +
                "\n"+"\n"+"\n"+"\n"+
                "all Request"+
                "\n"+"\n"+"\n"+"\n"+
                "/clients"+"\n"+
                "/address"+"\n"+
                "/crew"+"\n"+
                "/flight"+"\n"+
                "/person"+"\n"+
                "/shift"+"\n"+
                "/vehicle"+"\n";
    }
}
