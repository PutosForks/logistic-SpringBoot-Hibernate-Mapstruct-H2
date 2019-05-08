package com.logistic.task.controller;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.logistic.task.LogisticApplication;
import com.logistic.task.entity.Address;
import com.logistic.task.entity.Client;
import com.logistic.task.service.ClientService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LogisticApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public abstract class BaseController {
    protected MockMvc mvc;
    @Autowired
    protected ClientService clientService;
    @Autowired
    WebApplicationContext webApplicationContext;




    protected void setUp()  {

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }


}