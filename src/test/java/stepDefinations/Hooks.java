package stepDefinations;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforescenario() throws IOException {
        stepDefination stepdef =  new stepDefination();
        if(stepdef.place_id == null){
            stepdef.add_place_payload_with("Agarwal","French","102/100, main");
            stepdef.user_calls_with_http_request("AddPlaceAPI","post");
            stepdef.verify_place_id_created_maps_to_using("Agarwal","getPlaceAPI");
        }

    }
}
