package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//write a code that will give place_id
		//execute this only if place_id is null
		stepDefinition m = new stepDefinition();
		if(stepDefinition.place_id==null)
			{
			m.add_place_payload_with("Achin", "French", "Asia");
			m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Achin", "getPlaceAPI");
			}
		
	}
	

}
