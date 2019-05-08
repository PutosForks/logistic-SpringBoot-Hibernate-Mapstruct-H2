package com.logistic.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.logistic.task.entity.Address;
import com.logistic.task.entity.Client;
import com.logistic.task.mapper.ClientMapper;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */

    public class ClientControllerTest extends BaseController{
        @Autowired
        private ClientController clientController;


    @Override
    @Before
    public void setUp()   {
        super.setUp();

    }

    @Test
    public void clientControllerInit(){
        assertThat(clientController).isNotNull();
    }


    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("get /clients - вывод всех клиетов сохраненных в базе"+ "\n"
                        +"get /clients/5 - вывод всех клиета с ID 5 "+ "\n"
                        +"post /clients/ - сохранение переданного Json в объект и в базу" +"\n"
                        +"put /clients/ID - обновление переданого в Json обекта по ID" +"\n"
                        +"delete /clients/ID - удаление клиента по ID")));
    }


    @Test
    public void getClientsListIfEmptyDB() throws Exception {
        String uri = "/clients";
        MvcResult mvcResult = mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Client[] clients = super.mapFromJson(content, Client[].class);
        assertTrue(clients.length <=0);

    }
    @Test
    public void createClient() throws Exception {
        String uri = "/clients";
        Client client = new Client();
        client.setId(569);
        client.setName("Ginger");
        client.setAddress(new Address("address"));
        client.setPhoneNumber("+3-000-000");
        String inputJson =  mapToJson(client);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();

        String actualJson = "{" +
                "\"id\":569," +
                "\"address\":" +
                "{" +
                "\"id\":0," +
                "\"address\":\"address\"" +
                "}," +
                "\"name\":\"Ginger\"," +
                "\"phoneNumber\":\"+3-000-000\"" +
                "}";
        assertEquals(content, actualJson);
    }
    @Test
    public void updateClient() throws Exception {

        Client client = new Client();
        client.setName("Ginger");
        client.setAddress(new Address("address"));
        client.setPhoneNumber("+3-000-000");
        clientService.save(client);
        long id = client.getId();
        long idAddress = client.getAddress().getId();

        String uri = "/clients/"+id;
        client.setName("Lemon");
        String inputJson =  mapToJson(client);
        System.out.println(inputJson.toString());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString();
        String actualJson = "{" +
                "\"id\":"+id+"," +
                "\"address\":" +
                "{" +
                "\"id\":"+idAddress+"," +
                "\"address\":\"address\"" +
                "}," +
                "\"name\":\"Lemon\"," +
                "\"phoneNumber\":\"+3-000-000\"" +
                "}";
        assertEquals(content, actualJson);

        clientService.deleteById(id);
    }
    @Test
    public void deleteClientIfClientNotExists() throws Exception {
        String uri = "/clients/85";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
    }
}