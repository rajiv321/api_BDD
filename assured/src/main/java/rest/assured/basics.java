package rest.assured;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/*the package is not importable in the 
 * eclpse so copy the above import and 
 * base it add static and add * at the end 
 * to import all the given when and then from the RestAssured section
 * 
 */
import static io.restassured.RestAssured.*;

public class basics {

	public static void main(String[] args) {
		/*
		 * given ==> all the data in the api
		 * when ==> send the request ==> resource and https method 
		 * then ==> validate the response 
		 * 
		 * 
		 */
		RestAssured.baseURI = "https://www.impactguru.com/api/v1";
		/*Response response = (Response)RestAssured.*/
		
		/*login feature of the BDAPP*/
		String s = given().log().all()
				.formParam("email","Rajiv.jena@impactguru.com").formParam("password","Rajivjena4")
				.when().accept("application/json").post("/auth/adminlogin").then().extract().response().asPrettyString();
//		System.out.println(s.asPrettyString()+""+ s.getStatusCode());
//		System.out.println(response.getStatusCode());
//		System.out.println(response.asString());
		
		JsonPath path = new JsonPath(s);
		System.out.println(s);
//		superHeroes['active']
		String a = path.getString("data['access_token']");
//		System.out.println(a);
//		<----------------------------------- Code to get list of campaigns ---------------------------------------------------------->
		
		String  rajiv = given().log().all().param("page", 2).header("Access-token",a).header("App-Name","staff")
				.when().accept("application/json").get("/staff/campaigns").then().extract().response().asPrettyString();
		System.out.println(rajiv);
//		<-------------------------------------------------------------------------------------------------------------------->
		
		
		
	}


}
