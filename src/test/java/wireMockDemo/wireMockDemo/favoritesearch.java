package wireMockDemo.wireMockDemo;

import org.junit.Assert;
import org.junit.Rule;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class favoritesearch {
  
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();
	
	@Test
	public void wiremock() {
	  
	  
	  	//WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(9999)); 
	  	//wireMockServer.start();
	  	//System.out.println("Wiremock Server is: " +wireMockServer);
	  
		RestAssured.baseURI = "http://localhost:9999";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/users/1/");
		String responseBody = response.getBody().asString();
		System.out.println("Response staus code is: " + response.getStatusCode());
		
		Assert.assertEquals("Checking status code = 200", 200, response.getStatusCode());
	
		
		//Assert.assertEquals("Checking status code = 400", 400, response.getStatusCode()); //to check the failure
		
		//wireMockServer.stop();

	}

  
  
  
  
}
