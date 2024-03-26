package rvabackendg5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import rvabackendg5.models.Artikl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArtiklControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Artikl>> response = template.exchange(
				"/artikl", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Artikl>>() {});
		
		List<Artikl> list = response.getBody();
		for(int i=0; i < list.size(); i++) {
			if(highestId <= list.get(i).getId()){
				highestId = list.get(i).getId() + 1;
			}
		}
	}
	
	void getHighestId() {
		createHighestId();
		highestId--;
	}
	
	@Test
	@Order(1)
	void TestGetAllArtikls() {
		ResponseEntity<List<Artikl>> response = template.exchange(
				"/artikl", HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Artikl>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Artikl> artikli = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!artikli.isEmpty());
	}
}
