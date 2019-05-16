package com.logistic.task.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistic.task.dto.FlightDto;
import com.logistic.task.entity.Flight;
import com.logistic.task.mapper.FlightMapper;
import com.logistic.task.service.FlightService;
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

public class FlightControllerTest extends BaseControllerTest {
    private Flight flight;
    private int flightId = MIN_VALUE;
    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightMapper flightMapper;
    @Autowired
    private TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
        flight = testHelper.createFlight();
        flightId =(int) flight.getId();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(flightMapper.toDto(flight)));
    }

    @After
    public void tearDown() throws Exception {
        testHelper.deleteFlight(flight);
    }

    @Test
    public void getFlight() throws Exception {
        when().
                get("/flight").
                then().
                statusCode(SC_OK);
    }

    @Test
    public void getFlights() throws Exception {
        when().
                get("flight/{id}", flightId).
                then().
                statusCode(SC_OK).
                body("id", equalTo(flightId));
    }


    @Test
    public void getFlight_notFound() throws Exception {
        when().
                get("/flight/{id}", flightId + 1).
                then().
                statusCode(SC_NOT_FOUND);
    }

    @Test
    public void createFlight() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/flight.json");
        String expectedJson = jsonObject.toString();
        System.out.println(expectedJson);

        int newClientId =
                given().
                        contentType("application/json;charset=UTF-8").
                        body(expectedJson).
                        when().
                        post("/flight").
                        then().
                        extract().
                        path("id");

        Long lo = (long) newClientId;

        // check created data
        Flight createdFlight = flightService.findById(lo).get();
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(flightMapper.toDto(createdFlight));

        JSONAssert.assertEquals(expectedJson, actualJson, false);

        // delete
        testHelper.deleteFlight(createdFlight);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void updateFlight() throws Exception {
        // update
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/flight.json");
        String expectedJson = jsonObject.toString();

        given().
                contentType("application/json;charset=UTF-8").
                body(expectedJson).
                when().
                put("/flight/{id}", flightId).
                then().
                statusCode(SC_ACCEPTED);

        // check updated data
        FlightDto flightDto = flightMapper.toDto(flightService.findById((long) flightId).get());
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(flightDto);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }


    @Test
    public void deleteFlight() throws Exception {
        when().
                delete("/flight/{id}", flightId).
                then().
                statusCode(SC_ACCEPTED);
    }

    @Test
    public void deleteFlight_notFound() throws Exception {
        when().
                delete("/flight/{id}", flightId + 1).

                then().
                statusCode(SC_NOT_FOUND);
    }
}