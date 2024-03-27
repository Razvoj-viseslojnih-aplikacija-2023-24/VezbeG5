package rvabackendg5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.http.HttpEntity;
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
	
	@Test
	@Order(2)
	void testGetArtiklById() {
		int id = 1;
		ResponseEntity<Artikl> response = template.exchange(
				"/artikl/id/" + id, HttpMethod.GET, null, Artikl.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertEquals(id, response.getBody().getId());
	
	}
	
	@Test
	@Order(3)
	void testGetArtiklsByNaziv() {
		String naziv = "Moja";
		ResponseEntity<List<Artikl>> response = template.exchange(
				"/artikl/naziv/" + naziv, HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Artikl>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Artikl> artikli = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(artikli.get(0));
		for(Artikl a : artikli) {
			assertTrue(a.getNaziv().contains(naziv));

			// U slucaju brojcane vrednosti
			// assertTrue(a.getVrednost() < predefinisanaVrednost)
			// ILI
			// assertTrue(a.getVrednost() > predefinisanaVrednost)
			
			//U slucaju boolean vrednosti
			// assertTrue(a.getBooleanVrednost());
		}
	}
	
	@Test
	@Order(4)
	void testCreateArtikl() {
		Artikl artikl = new Artikl();
		artikl.setNaziv("POST naziv");
		artikl.setProizvodjac("POST proizvodjac");
		
		HttpEntity<Artikl> entity = new HttpEntity<Artikl>(artikl);
		createHighestId();
		
		ResponseEntity<Artikl> response = template.exchange(
				"/artikl", HttpMethod.POST, entity, Artikl.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(201, statusCode);
		assertEquals("/artikl/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(artikl.getNaziv(), response.getBody().getNaziv());
		assertEquals(artikl.getProizvodjac(), response.getBody().getProizvodjac());	
	}
	

	@Test
	@Order(5)
	void TestUpdateArtikl() {
		Artikl artikl = new Artikl();
		artikl.setNaziv("PUT naziv");
		artikl.setProizvodjac("PUT proizvodjac");
		
		HttpEntity<Artikl> entity = new HttpEntity<Artikl>(artikl);
		getHighestId();
		
		ResponseEntity<Artikl> response = template.exchange(
				"/artikl/id/" + highestId, HttpMethod.PUT, entity, Artikl.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody() instanceof Artikl);
		assertEquals(artikl.getNaziv(), response.getBody().getNaziv());
		assertEquals(artikl.getProizvodjac(), response.getBody().getProizvodjac());	
	
	}
	
	@Test
	@Order(6)
	void TestDeleteArtiklById() {
		getHighestId();
		ResponseEntity<String> response = template.exchange(
				"/artikl/id/" + highestId, HttpMethod.DELETE, null, String.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("has been deleted."));
				
	}
}
