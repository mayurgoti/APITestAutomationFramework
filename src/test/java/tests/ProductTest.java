package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;

public class ProductTest extends BaseTest {
	
	@Test
	public void verifyGetRequest() {
		
		extentReport.createTestCase("Verify Get Product");
		
		Response response = requestFactory.getAllProducts();
		
		extentReport.addLog(Status.INFO, response.asPrettyString());
		response.then().statusCode(200);
	}
	
	@Test
	public void verifyAddProduct() {
		extentReport.createTestCase("Verify Add Product");
		String requestPayload = "{\n"
				+ "  \"name\": \"Samsumng Mobile \",\n"
				+ "  \"type\": \"Mobile\",\n"
				+ "  \"price\": 10000,\n"
				+ "  \"shipping\": 100,\n"
				+ "  \"upc\": \"string\",\n"
				+ "  \"description\": \"string\",\n"
				+ "  \"manufacturer\": \"string\",\n"
				+ "  \"model\": \"string\",\n"
				+ "  \"url\": \"string\",\n"
				+ "  \"image\": \"string\"\n"
				+ "}";
		requestFactory.addProducts(requestPayload)
		.then().log().all().statusCode(201);	
		
	}
	
	@Test
	public void verifyAddProductWithMapPayload() {
		
		extentReport.createTestCase("Verify Add Product with Payload as Map");
		
		Map<String, Object> requestPayload = new HashMap<String, Object>();
		requestPayload.put("name", "Samsung Mobile");
		requestPayload.put("type", "Mobile");
		requestPayload.put("price", 1000);
		requestPayload.put("shipping", 10);
		requestPayload.put("upc", "Samsung Mobile");
		
		requestPayload.put("description", "Samsung Mobile");
		requestPayload.put("manufacturer", "Samsung Mobile");
		requestPayload.put("model", "Samsung Galaxy Note");
		requestPayload.put("url", "Samsung Mobile");
		requestPayload.put("image", "Samsung Mobile");
		
		requestFactory.addProducts(requestPayload)
		.then().log().all().statusCode(201);	
		
	}
	
	@Ignore
	@Test
	public void verifyEndToEndCallFlow() {
		//1 send post request to create a product
		//2 send a Get request to verify if the product is added
		//3 send a Put request to edit the product
		//4 send Delete request to delete product
		//5 Get request to verify product is deleted
		
		Map<String, Object> requestPayload = new HashMap<String, Object>();
		requestPayload.put("name", "Apple Mobile");
		requestPayload.put("type", "Mobile");
		requestPayload.put("price", 1000);
		requestPayload.put("shipping", 10);
		requestPayload.put("upc", "Samsung Mobile");
		
		requestPayload.put("description", "Samsung Mobile");
		requestPayload.put("manufacturer", "Samsung Mobile");
		requestPayload.put("model", "Samsung Galaxy Note");
		requestPayload.put("url", "Samsung Mobile");
		requestPayload.put("image", "Samsung Mobile");
		
		Response response = requestFactory.addProducts(requestPayload);
		
				response.then().log().all().statusCode(201);	
	}
}
