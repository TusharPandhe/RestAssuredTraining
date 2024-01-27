package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	// Verify the cookie

	// @Test
	void testCookies() {

		given()

				.when().get("https://google.com")

				.then().cookie("AEC", "ddafddfds fvdzvf sdfdf sddc ").log().all();
	}

	// Get all cookies

	@Test
	void testGetCookies() {

		Response rse = given()

				.when().get("https://google.com");

//		// get single cookie info
//
//		String cookie_value = rse.getCookie("AEC");
//		System.out.println("Value of cookie is ====>" + cookie_value);

		// Get all cookies info

		Map<String, String> cookies_value = rse.getCookies();

// Below statement is used to print all value of cookies
		// System.out.println(cookies_value.keySet());

		for (String k : cookies_value.keySet()) {

			String cookie_value_one_by_one = rse.getCookie(k);
			System.out.println(k + "               " + cookie_value_one_by_one);

		}
	}

}
