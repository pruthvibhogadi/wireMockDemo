package wireMockDemo.wireMockDemo;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

class RequestMatchingTest {

	private WireMockServer wireMockServer;
	
	//Configuring wireMock for a default server and port.
	@BeforeEach
	void configureSystemUnderTest() {
		this.wireMockServer = new WireMockServer(options()
		// .dynamicPort()
		// .bindAddress("127.1.1.1")

		);
		this.wireMockServer.start();
	}

	//Closing the wireMock server after each test
	@AfterEach
	void stopWireMockServer() {
		this.wireMockServer.stop();
	}


	@Test
	@DisplayName("Sanity check for trackingFavorites")

	public void trackingAPITest() {

		System.out.println("Now, the execution starts.....");
		System.out.println("URL is: " + this.wireMockServer.baseUrl());
		System.out.println("Port is: " + this.wireMockServer.port());

		//Creating a Stub - Canned Response (Static Response)
		stubFor(get(urlEqualTo("/users/1")).willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withBody("{\n" + "  \"id\": 1,\n" + "  \"name\": \"Leanne Graham\",\n" + "  \"username\": \"Bret\",\n"
						+ "  \"email\": \"Sincere@april.biz\",\n" + "  \"address\": {\n"
						+ "    \"street\": \"Kulas Light\",\n" + "    \"suite\": \"Apt. 556\",\n"
						+ "    \"city\": \"Gwenborough\",\n" + "    \"zipcode\": \"92998-3874\",\n" + "    \"geo\": {\n"
						+ "      \"lat\": \"-37.3159\",\n" + "      \"lng\": \"81.1496\"\n" + "    }\n" + "  },\n"
						+ "  \"phone\": \"1-770-736-8031 x56442\",\n" + "  \"website\": \"hildegard.org\",\n"
						+ "  \"company\": {\n" + "    \"name\": \"Romaguera-Crona\",\n"
						+ "    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n"
						+ "    \"bs\": \"harness real-time e-markets\"\n" + "  }\n" + "}")));

		
		RestAssured.baseURI = this.wireMockServer.baseUrl();
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users/1");
		System.out.println("Response is: ...." + response.getBody().asString());
		Assert.assertEquals(200, response.statusCode());
		System.out.println("Assertion passed for status code = " + response.getStatusCode());
		
	}

}
