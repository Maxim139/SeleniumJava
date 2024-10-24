package MainCode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;




public class UpgradePlan {

        String requestBodyMasterPlan = "{\"plan_id\":23}";
        String requestBodyBasicPlan = "{\"plan_id\":17}";

        //for pre-prod
        String requestBodyPlatinumYearPlan = "{\"plan_id\":36}";
      //  String url = "https://preprod.dealsignal.com/api/v0/users/726/update_subscription"; // pre-prod fortest203@test.com
       // String url = "https://preprod.dealsignal.com/api/v0/users/727/update_subscription"; // pre-prod 204fortest@test.com
        String url = "https://preprod.dealsignal.com/api/v0/users/738/update_subscription"; // pre-prod fortestob66@test.com


        //for staging
        String requestBodyPlatinumMonthlyplan = "{\"plan_id\":22}";
        // String url = "https://stagingapp.dealsignal.com/api/v0/users/841/update_subscription"; //staging for fortest202@test.com
        
        String apiKey = "92X53fQjPaTLuxcbPoQdmQss"; //pre-prod api_key Rob
      //  String apiKey = "zeaSyaxtQg4DsRssZ29LdQss";   //pre-prod api_key 203fortest@test.com
        //String apiKey = "gMp91utGNYOQdS3lER8npgss"; // pre-prod api_key maksim@dealsignal.com



        
    public void upgradePlan() throws IOException, InterruptedException {

        URI uri = URI.create(url + "?api_key=" + apiKey);

        HttpClient client = HttpClient.newHttpClient();

        // // switch to Basic plan
        // HttpRequest requestToBasic = HttpRequest.newBuilder().uri(uri).header("Content-Type", "application/json;charset=UTF-8")
        //         .method("PATCH", BodyPublishers.ofString(requestBodyBasicPlan)).build(); //header("Content-Type", "application/json;charset=UTF-8").header("Authorization", "Basic cm9iQGRlYWxzaWduYWwuY29tOnRlY2h0ZWFtQDEyMw==").header("Content-Length", "14")

        // HttpResponse<String> httpResponseBasic = client.send(requestToBasic, HttpResponse.BodyHandlers.ofString());

        // String responseBodyBasic = httpResponseBasic.body();

        // System.out.println(responseBodyBasic);


        // switch to Platinum Yearly plan
        HttpRequest httpRequestToPlatinum = HttpRequest.newBuilder().uri(uri).header("Content-Type", "application/json;charset=UTF-8")
                .method("PATCH", BodyPublishers.ofString(requestBodyPlatinumYearPlan)).build(); 

        HttpResponse<String> httpResponsePlatinum = client.send(httpRequestToPlatinum, HttpResponse.BodyHandlers.ofString());

        String responseBodyPlatinum = httpResponsePlatinum.body();

        System.out.println(responseBodyPlatinum);



        // // switch to Master plan
        // HttpRequest requestToMaster = HttpRequest.newBuilder().uri(uri).header("Content-Type", "application/json")
        //         .method("PATCH", BodyPublishers.ofString(requestBodyMasterPlan)).build();

        // HttpResponse<String> response = client.send(requestToMaster, HttpResponse.BodyHandlers.ofString());

        // String responseBody = response.body();

        // System.out.println(responseBody);
    }
}
