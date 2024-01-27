package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderDemo {

	// Verify the cookie

	@Test
	void getHeaders() {

		given()

				.when().get("https://google.com")

				.then().header("Content-Type", "text/html; charset=ISO-8859-1").header("Content-Encoding", "gzip")
				.header("Server", "gws").log().all();
	}

	@Test(priority = 2)
	void testHeaders() {

		Response res = given()

				.when().get("https://google.com");

//		// get single header info
//
//		String header_value = res.header("Content-Type");
//		System.out.println("The value of Content-Type header is ===> " + header_value);

		// get all headers info

		Headers myHeaders = res.getHeaders();

		for (Header hd : myHeaders) {
			System.out.println(hd.getName() + "            " + hd.getValue());

		}

	}

}
