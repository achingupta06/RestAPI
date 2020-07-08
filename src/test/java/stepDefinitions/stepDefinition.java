package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefinition extends Utils {
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
		
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	    
	}


	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		// constructor will be called with value of resource passed
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		response = res.when().post(resourceAPI.getResource());
		//		.then().spec(resspec).extract().response();
		else if(method.equalsIgnoreCase("GET"))
				response = res.when().get(resourceAPI.getResource());
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	    
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(getJsonPath(response, keyvalue),Expectedvalue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    
		// Request Spec
		
		place_id= getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_post_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(expectedName, actualName);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		}
	
}
