package com.logistic.task.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistic.task.dto.PersonDto;
import com.logistic.task.entity.Person;
import com.logistic.task.mapper.PersonMapper;
import com.logistic.task.service.PersonService;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static java.lang.Integer.MIN_VALUE;
import static javax.servlet.http.HttpServletResponse.*;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 16.05.2019
 */

public class PersonControllerTest extends BaseControllerTest {
    private Person person;
    private int personId = MIN_VALUE;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
        person = testHelper.createPerson();
        personId =(int) person.getId();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(personMapper.toDto(person)));
    }

    @After
    public void tearDown() throws Exception {
        testHelper.deletePerson(person);
    }

    @Test
    public void getPerson() throws Exception {
        when().
                get("/person").
                then().
                statusCode(SC_OK);
    }

    @Test
    public void getFlights() throws Exception {
        when().
                get("person/{id}", personId).
                then().
                statusCode(SC_OK).
                body("id", equalTo(personId));
    }


    @Test
    public void getPerson_notFound() throws Exception {
        when().
                get("/person/{id}", personId + 1).
                then().
                statusCode(SC_NOT_FOUND);
    }

    @Test
    public void createPerson() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/person.json");
        String expectedJson = jsonObject.toString();
        System.out.println(expectedJson);

        int newClientId =
                given().
                        contentType("application/json;charset=UTF-8").
                        body(expectedJson).
                        when().
                        post("/person").
                        then().
                        extract().
                        path("id");

        Long lo = (long) newClientId;

        // check created data
        Person createdPerson = personService.findById(lo).get();
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(personMapper.toDto(createdPerson));

        JSONAssert.assertEquals(expectedJson, actualJson, false);

        // delete
        testHelper.deletePerson(createdPerson);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void updatePerson() throws Exception {
        // update
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/person.json");
        String expectedJson = jsonObject.toString();

        given().
                contentType("application/json;charset=UTF-8").
                body(expectedJson).
                when().
                put("/person/{id}", personId).
                then().
                statusCode(SC_ACCEPTED);

        // check updated data
        PersonDto personDto = personMapper.toDto(personService.findById((long) personId).get());
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(personDto);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void deletePerson() throws Exception {
        when().
                delete("/person/{id}", personId).
                then().
                statusCode(SC_ACCEPTED);
    }

    @Test
    public void deletePerson_notFound() throws Exception {
        when().
                delete("/flight/{id}", personId + 1).

                then().
                statusCode(SC_NOT_FOUND);
    }
}