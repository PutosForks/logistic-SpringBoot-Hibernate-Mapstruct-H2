package com.logistic.task.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistic.task.dto.CrewDto;
import com.logistic.task.entity.Crew;
import com.logistic.task.mapper.CrewMapper;
import com.logistic.task.service.CrewService;
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
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 15.05.2019
 */

public class CrewControllerTest extends BaseControllerTest{

    private Crew crew;
    private int crewId = MIN_VALUE;
    @Autowired
    private CrewService crewService;
    @Autowired
    private CrewMapper crewMapper;
    @Autowired
    private TestHelper testHelper;


    @Before
    public void setUp() throws Exception {
        crew = testHelper.createCrew();
        crewId =(int) crew.getId();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println( mapper.writeValueAsString(crewMapper.toDto(crew)));
    }

    @After
    public void tearDown() throws Exception {
        testHelper.deleteCrew(crew);
    }

    @Test
    public void getCrews() throws Exception {
        when().
                get("/crew").
                then().
                statusCode(SC_OK);
    }

    @Test
    public void getCrew() throws Exception {
        when().
                get("crew/{id}", crewId).
                then().
                statusCode(SC_OK).
                body("id", equalTo(crewId));
    }


    @Test
    public void getCrew_notFound() throws Exception {
        when().
                get("/crew/{id}", crewId + 1).
                then().
                statusCode(SC_NOT_FOUND);
    }

    @Test
    public void createCrew() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/crew.json");
        String expectedJson = jsonObject.toString();
        System.out.println(expectedJson);

        int newClientId =
                given().
                        contentType("application/json;charset=UTF-8").
                        body(expectedJson).
                        when().
                        post("/crew").
                        then().
                        extract().
                        path("id");

        Long lo = (long) newClientId;

        // check created data
        Crew createdCrew = crewService.findById(lo).get();
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(crewMapper.toDto(createdCrew));
        JSONAssert.assertEquals(expectedJson, actualJson, false);

        // delete
        testHelper.deleteCrew(createdCrew);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void updateCrew() throws Exception {
        // update
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/crew.json");
        //jsonObject.put("addressId", (int) testHelper.createAddress().getId());
        String expectedJson = jsonObject.toString();

        given().
                contentType("application/json;charset=UTF-8").
                body(expectedJson).
                when().
                put("/crew/{id}", crewId).
                then().
                statusCode(SC_ACCEPTED);

        // check updated data
        CrewDto crewDto = crewMapper.toDto(crewService.findById((long) crewId).get());
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(crewDto);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }


    @Test
    public void deleteCrew() throws Exception {
        when().
                delete("/crew/{id}", crewId).
                then().
                statusCode(SC_ACCEPTED);
    }

    @Test
    public void deleteCrew_notFound() throws Exception {
        when().
                delete("/crew/{id}", crewId + 1).

                then().
                statusCode(SC_NOT_FOUND);
    }

}