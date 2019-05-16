package com.logistic.task.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistic.task.dto.AddressDto;
import com.logistic.task.entity.Address;
import com.logistic.task.mapper.AddressMapper;
import com.logistic.task.service.AddressService;
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

public class AddressControllerTest extends BaseControllerTest {
    private Address address;
    private int addressId = MIN_VALUE;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
        address = testHelper.createAddress();
        addressId =(int) address.getId();
    }

    @After
    public void tearDown() throws Exception {
        testHelper.deleteAddress(address);
    }

    @Test
    public void getAddresses() throws Exception {
        when().
                get("/address").
                then().
                statusCode(SC_OK);
    }

    @Test
    public void getAddress() throws Exception {
        when().
                get("address/{id}", addressId).
                then().
                statusCode(SC_OK).
                body("id", equalTo(addressId));
    }


    @Test
    public void getAddress_notFound() throws Exception {
        when().
                get("/clients/{id}", addressId + 1).
                then().
                statusCode(SC_NOT_FOUND);
    }

    @Test
    public void createAddress() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/address.json");
        String expectedJson = jsonObject.toString();
        System.out.println(expectedJson);

        int newClientId =
                given().
                        contentType("application/json;charset=UTF-8").
                        body(expectedJson).
                        when().
                        post("/address").
                        then().
                        extract().
                        path("id");

        Long lo = (long) newClientId;

        // check created data
        Address createdAddress = addressService.findById(lo).get();
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(addressMapper.toDto(createdAddress));

        System.out.println(expectedJson);
        System.out.println(actualJson);

        JSONAssert.assertEquals(expectedJson, actualJson, false);

        // delete
        testHelper.deleteAddress(createdAddress);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void updateAddress() throws Exception {
        // update
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/address.json");
        //jsonObject.put("addressId", (int) testHelper.createAddress().getId());
        String expectedJson = jsonObject.toString();

        given().
                contentType("application/json;charset=UTF-8").
                body(expectedJson).
                when().
                put("/address/{id}", addressId).
                then().
                statusCode(SC_ACCEPTED);

        // check updated data
       AddressDto addressDto = addressMapper.toDto(addressService.findById((long) addressId).get());
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(addressDto);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }


    @Test
    public void deleteAddress() throws Exception {
        when().
                delete("/address/{id}", addressId).
                then().
                statusCode(SC_ACCEPTED);
    }

    @Test
    public void deleteAddress_notFound() throws Exception {
        when().
                delete("/address/{id}", addressId + 1).

                then().
                statusCode(SC_NOT_FOUND);
    }
}