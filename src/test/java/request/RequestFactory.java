package request;

import client.RestClient;
import io.restassured.response.Response;

public class RequestFactory {
	
	RestClient restClient;
	
	public RequestFactory() {
		restClient = new RestClient();
	}
	
	public Response getAllProducts() {
		return restClient.sendGetRequest("/products");
	}
	
	public Response addProducts(Object requestPayload) {
		return restClient.sendPostRequest("/products", requestPayload);
	};
	
	public Response updateProducts(Object requestPayload) {
		return restClient.sendPutRequest("/products", requestPayload);
	};

}
