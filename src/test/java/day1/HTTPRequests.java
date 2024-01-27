package day1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPRequests {

	int id;

	@Test(priority = 1)
	public void getRequstes() {
		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();

	}

	@Test(priority = 2)
	void creatUser() {

		HashMap data = new HashMap();
		data.put("name", "Tushar1");
		data.put("job", "Tester1");

		id = given().contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");

		// .then().statusCode(201).log().all();

	}

	@Test(priority = 3, dependsOnMethods = { "creatUser" })
	void updateUSer() {

		HashMap data1 = new HashMap();
		data1.put("name", "Anirved1");
		data1.put("job", "Singer1");

		given().contentType("application/json").body(data1)

				.when().put("https://reqres.in/api/users/" + id)

				.then().statusCode(200).log().all();

	}

	@Test(priority = 4)
	void deleteUser() {

		given()

				.when().delete("https://reqres.in/api/users/" + id)

				.then().statusCode(204).log().all();
	}

}
