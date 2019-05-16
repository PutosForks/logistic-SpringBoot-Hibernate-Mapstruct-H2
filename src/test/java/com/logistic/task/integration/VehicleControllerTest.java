package com.logistic.task.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistic.task.dto.VehicleDto;
import com.logistic.task.entity.Vehicle;
import com.logistic.task.mapper.VehicleMapper;
import com.logistic.task.service.VehicleService;
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

public class VehicleControllerTest  extends BaseControllerTest {
    private Vehicle vehicle;
    private int vehicleId = MIN_VALUE;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
        vehicle = testHelper.createVehicle();
        vehicleId = (int) vehicle.getId();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(vehicleMapper.toDto(vehicle)));
    }

    @After
    public void tearDown() throws Exception {
        testHelper.deleteVehicle(vehicle);
    }

    @Test
    public void getShift() throws Exception {
        when().
                get("/vehicle").
                then().
                statusCode(SC_OK);
    }

    @Test
    public void getShifts() throws Exception {
        when().
                get("vehicle/{id}", vehicleId).
                then().
                statusCode(SC_OK).
                body("id", equalTo(vehicleId));
    }


    @Test
    public void getShift_notFound() throws Exception {
        when().
                get("/vehicle/{id}", vehicleId + 1).
                then().
                statusCode(SC_NOT_FOUND);
    }

    @Test
    public void createShift() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/vehicle.json");
        String expectedJson = jsonObject.toString();
        System.out.println(expectedJson);

        int newClientId =
                given().
                        contentType("application/json;charset=UTF-8").
                        body(expectedJson).
                        when().
                        post("/vehicle").
                        then().
                        extract().
                        path("id");

        Long lo = (long) newClientId;

        // check created data
        Vehicle createdVehicle = vehicleService.findById(lo).get();
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(vehicleMapper.toDto(createdVehicle));

        JSONAssert.assertEquals(expectedJson, actualJson, false);

        // delete
        testHelper.deleteVehicle(createdVehicle);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void updateShift() throws Exception {
        // update
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/vehicle.json");
        String expectedJson = jsonObject.toString();

        given().
                contentType("application/json;charset=UTF-8").
                body(expectedJson).
                when().
                put("/vehicle/{id}", vehicleId).
                then().
                statusCode(SC_ACCEPTED);

        // check updated data
        VehicleDto vehicleDto = vehicleMapper.toDto(vehicleService.findById((long) vehicleId).get());
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(vehicleDto);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }


    @Test
    public void deleteShift() throws Exception {
        when().
                delete("/vehicle/{id}", vehicleId).
                then().
                statusCode(SC_ACCEPTED);
    }

    @Test
    public void deleteShift_notFound() throws Exception {
        when().
                delete("/vehicle/{id}", vehicleId + 1).

                then().
                statusCode(SC_NOT_FOUND);
    }

}