package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import resources.APIresources;
import resources.TestDatabuild;
import resources.utils;


import java.io.IOException;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class stepDefination extends utils {

    RequestSpecification req;
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    static String place_id;

    TestDatabuild data = new TestDatabuild();

    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        res=given().spec(requestspecification())
                .body(data.addPlacePayload(name, language, address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        // Write code here that turns the phrase above into concrete actions
         APIresources Endpoint = APIresources.valueOf(resource);
        // System.out.println("-----------------------" + Endpoint.getResource());
        //resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("post")){
            response = res.when().post(Endpoint.getResource());
        }
        else if(method.equalsIgnoreCase("get")){
            response=res.when().get(Endpoint.getResource());
        }
        else if(method.equalsIgnoreCase("delete")){
            response = res.when().delete(Endpoint.getResource());
        }
        else if(method.equalsIgnoreCase("put")){
            response = res.when().put(Endpoint.getResource());
        }

    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(getJsonPath(response,key),value);

    }
    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        place_id=getJsonPath(response,"place_id");
        res=given().spec(requestspecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource,"GET");
        String actualName=getJsonPath(response,"name");
        assertEquals(actualName,expectedName);
    }
    @Given("Delete place payload")
    public void delete_place_payload() throws IOException {
      res = given().spec(requestspecification()).body(data.deleteplace(place_id));
    }
    @Given("update Place Payload")
    public void update_place_payload() throws IOException {
        res = given().spec(requestspecification()).body(data.Updateplace(place_id));

    }


}
