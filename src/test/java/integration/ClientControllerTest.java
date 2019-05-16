package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistic.task.dto.ClientDto;
import com.logistic.task.entity.Client;
import com.logistic.task.mapper.ClientMapper;
import com.logistic.task.service.ClientService;
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
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */

    public class ClientControllerTest extends BaseControllerTest {
    private Client client;
    private int clientId = MIN_VALUE;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private TestHelper testHelper;


    @Before
    public void setUp() throws Exception {
        client = testHelper.createClient();
        clientId =(int) client.getId();
    }

    @After
    public void tearDown() throws Exception {
        testHelper.deleteClient(client);
    }

    @Test
    public void getClients() throws Exception {
        when().
                get("/clients").
                then().
                statusCode(SC_OK);
    }

    @Test
    public void getClient() throws Exception {
        when().
                get("clients/{id}", clientId).
                then().
                statusCode(SC_OK).
                body("id", equalTo(clientId));
    }


    @Test
    public void getAddressByClientId() throws Exception {
        when().
                get("clients/{id}/address", clientId).
                then().
                statusCode(SC_OK);
    }

    @Test
    public void getClient_notFound() throws Exception {
        when().
                get("/clients/{id}", clientId + 1).
                then().
                statusCode(SC_NOT_FOUND);
    }

    @Test
    public void createClient() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/client.json");
        //jsonObject.put("addressId",testHelper.createAddress().getId());
        String expectedJson = jsonObject.toString();
        System.out.println(expectedJson);

        int newClientId =
                given().
                        contentType("application/json;charset=UTF-8").
                        body(expectedJson).
                        when().
                        post("/clients").
                        then().
                        extract().
                        path("id");

        Long lo = (long) newClientId;

        // check created data
        Client createdClient = clientService.findById(lo).get();
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(clientMapper.toDto(createdClient));

        System.out.println(expectedJson);
        System.out.println(actualJson);

        JSONAssert.assertEquals(expectedJson, actualJson, false);

        // delete
        testHelper.deleteClient(createdClient);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void updateClient() throws Exception {
        // update
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/client.json");
        //jsonObject.put("addressId", (int) testHelper.createAddress().getId());
        String expectedJson = jsonObject.toString();

        given().
                contentType("application/json;charset=UTF-8").
                body(expectedJson).
                when().
                put("/clients/{id}", clientId).
                then().
                statusCode(SC_ACCEPTED);

        // check updated data
        ClientDto clientDto = clientMapper.toDto(clientService.findById((long) clientId).get());
        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(clientDto);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }


   @Test
   public void deleteClient() throws Exception {
       when().
               delete("/clients/{id}", clientId).
               then().
               statusCode(SC_ACCEPTED);
   }

    @Test
    public void deleteClient_notFound() throws Exception {
        when().
                delete("/clients/{id}", clientId + 1).

                then().
                statusCode(SC_NOT_FOUND);
    }
}