package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

/*
Different ways to create post request body
1)Post request body using HashMap
2)Post request body creation using org.json
3)Post request body creation using POJO class
4) Post request using external json file
*/

public class DiffWaysToCreateJsonBody {

	String id;

	// 1)Post request body using HashMap

	// @Test(priority = 1)
	public void testPostUsingHashMap() {

		HashMap data = new HashMap();
		data.put("name", "Anish");
		data.put("location", "Nashik");
		data.put("phone", "1546523");

		String CourseArr[] = { "C", "C++" };

		data.put("cources", CourseArr);

		id = given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/students").then().statusCode(201).body("name", equalTo("Anish"))
				.body("location", equalTo("Nashik")).body("phone", equalTo("1546523")).body("cources[0]", equalTo("C"))
				.body("cources[1]", equalTo("C++")).header("Content-Type", "application/json; charset=utf-8").log()
				.all().extract().path("id");

	}

	// @Test(priority = 2, dependsOnMethods = "testPostUsingHashMap")
	void testDelete() {

		given()

				.when().delete("http://localhost:3000/students/" + id)

				.then().statusCode(200).log().all();
	}

	// 2)Post request body creation using org.json

	// @Test(priority = 3)
	public void testPostUsingJsonLibrary() {

		JSONObject data = new JSONObject();
		data.put("name", "Manish");
		data.put("location", "Nagpur");
		data.put("phone", "1546523");

		String CourseArr[] = { "Java", "C++" };

		data.put("cources", CourseArr);

		id = given().contentType("application/json").body(data.toString())

				.when().post("http://localhost:3000/students").then().statusCode(201).body("name", equalTo("Manish"))
				.body("location", equalTo("Nagpur")).body("phone", equalTo("1546523"))
				.body("cources[0]", equalTo("Java")).body("cources[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all().extract().path("id");

	}

	// @Test(priority = 4, dependsOnMethods = "testPostUsingJsonLibrary")
	void testDeleteJsonLibrary() {

		given()

				.when().delete("http://localhost:3000/students/" + id)

				.then().statusCode(200).log().all();
	}

// 3)Post request body creation using POJO class

	// @Test(priority = 5)
	public void testPostUsingPOJO() {

		Pojo_PostRequsest data = new Pojo_PostRequsest();

		data.setName("Manish");
		data.setLocation("Nagpur");
		data.setPhone("1546523");
		String CourseArr[] = { "Java", "C++" };
		data.setCources(CourseArr);

		id = given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/students").then().statusCode(201).body("name", equalTo("Manish"))
				.body("location", equalTo("Nagpur")).body("phone", equalTo("1546523"))
				.body("cources[0]", equalTo("Java")).body("cources[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all().extract().path("id");

	}

	// @Test(priority = 6, dependsOnMethods = "testPostUsingPOJO")
	void testDeletePOJO() {

		given()

				.when().delete("http://localhost:3000/students/" + id)

				.then().statusCode(200).log().all();
	}

//4) Post request using external json file

	@Test(priority = 7)
	public void testPostUsingExternalJson() throws FileNotFoundException {

		File f = new File("E:\\Polestar\\RestAssuredTraining\\Body2.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);

		id = given().contentType("application/json").body(data.toString())

				.when().post("http://localhost:3000/students").then().statusCode(201).body("name", equalTo("Manish"))
				.body("location", equalTo("Nagpur")).body("phone", equalTo("1546523"))
				.body("cources[0]", equalTo("Java")).body("cources[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all().extract().path("id");

	}

	@Test(priority = 8, dependsOnMethods = "testPostUsingExternalJson")
	void testDeleteExternalJson() {

		given()

				.when().delete("http://localhost:3000/students/" + id)

				.then().statusCode(200).log().all();
	}

}
