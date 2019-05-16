package com.logistic.task.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistic.task.dto.ShiftDto;
import com.logistic.task.entity.Shift;
import com.logistic.task.mapper.ShiftMapper;
import com.logistic.task.service.ShiftService;
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

public class ShiftControllerTest  extends BaseControllerTest {
    private Shift shift;
    private int shiftId = MIN_VALUE;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private ShiftMapper shiftMapper;
    @Autowired
    private TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
        shift = testHelper.createShift();
        shiftId =(int) shift.getId();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(shiftMapper.toDto(shift)));
    }

    @After
    public void tearDown() throws Exception {
        testHelper.deleteShift(shift);
    }

    @Test
    public void getShift() throws Exception {
        when().
                get("/shift").
                then().
                statusCode(SC_OK);
    }

    @Test
    public void getShifts() throws Exception {
        when().
                get("shift/{id}", shiftId).
                then().
                statusCode(SC_OK).
                body("id", equalTo(shiftId));
    }


    @Test
    public void getShift_notFound() throws Exception {
        when().
                get("/shift/{id}", shiftId + 1).
                then().
                statusCode(SC_NOT_FOUND);
    }

    @Test
    public void createShift() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/shift.json");
        String expectedJson = jsonObject.toString();
        System.out.println(expectedJson);

        int newClientId =
                given().
                        contentType("application/json;charset=UTF-8").
                        body(expectedJson).
                        when().
                        post("/shift").
                        then().
                        extract().
                        path("id");

        Long lo = (long) newClientId;

        // check created data
        Shift createdShift = shiftService.findById(lo).get();
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(shiftMapper.toDto(createdShift));

        JSONAssert.assertEquals(expectedJson, actualJson, false);

        // delete
        testHelper.deleteShift(createdShift);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void updateShift() throws Exception {
        // update
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/shift.json");
        String expectedJson = jsonObject.toString();

        given().
                contentType("application/json;charset=UTF-8").
                body(expectedJson).
                when().
                put("/shift/{id}", shiftId).
                then().
                statusCode(SC_ACCEPTED);

        // check updated data
        ShiftDto shiftDto = shiftMapper.toDto(shiftService.findById((long) shiftId).get());
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(shiftDto);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }


    @Test
    public void deleteShift() throws Exception {
        when().
                delete("/shift/{id}", shiftId).
                then().
                statusCode(SC_ACCEPTED);
    }

    @Test
    public void deleteShift_notFound() throws Exception {
        when().
                delete("/shift/{id}", shiftId + 1).

                then().
                statusCode(SC_NOT_FOUND);
    }

}