package rvabackendg5.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rvabackendg5.models.Artikl;
import rvabackendg5.services.ArtiklService;

@RestController
public class ArtiklController {

	@Autowired
	private ArtiklService service;
	
    /*
     * HTTP GET je jedna od HTTP metoda koja je analogna opciji READ iz CRUD
     * operacija. Anotacija @GetMapping se koristi kako bi se mapirao HTTP GET
     * zahtev. Predstavlja skraćenu verziju metode @RequestMapping(method =
     * RequestMethod.GET) U konkretnom slučaju HTTP GET zahtevi (a to je npr.
     * svako učitavanje stranice u browser-u) upućeni na adresu
     * localhost:8083/artikl biće prosleđeni ovoj metodi.
     *
     * Poziv metode artiklRepository.findAll() će vratiti kolekciju koja sadrži
     * sve artikala koji će potom u browseru biti prikazani u JSON formatu
     */
	
	@GetMapping("/artikl")
	public List<Artikl> getAllArtikls(){
		return service.getAll();
	}
	
	@GetMapping("/artikl/id/{id}")
	public ResponseEntity<?> getArtiklById(@PathVariable int id){
		Optional<Artikl> artikl = service.findById(id);
		if(artikl.isPresent()) {
			return ResponseEntity.ok(artikl.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID:" + 
		id + " does not exist.");
	}
	
	@GetMapping("/artikl/naziv/{naziv}")
	public ResponseEntity<?> getArtiklsByNaziv(@PathVariable String naziv){
		List<Artikl> artikls = service.getArtiklsByNaziv(naziv);
		if(artikls.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources"
					+ " with Naziv: " + naziv + " could not be found.");
		}
		return ResponseEntity.ok(artikls);
	}
	
	@PostMapping("/artikl")
	public ResponseEntity<?> createArtikl(@RequestBody Artikl artikl){
		if(service.existsById(artikl.getId())) {
			return ResponseEntity.status(409).body("Resource with" +
		" inserted values already exists.");
		} 
		Artikl savedArtikl = service.create(artikl);
		URI uri = URI.create("/artikl/" + savedArtikl.getId());
		return ResponseEntity.created(uri).body(savedArtikl);
	}
	
	@PutMapping("/artikl/id/{id}")
	public ResponseEntity<?> updateArtikl(@RequestBody Artikl artikl, @PathVariable int id){
		Optional<Artikl> updatedArtikl = service.update(artikl, id);
		if(updatedArtikl.isPresent()) {
			return ResponseEntity.ok(updatedArtikl);
		} 
		return ResponseEntity.status(404).body("Resource with requested ID: " +
		+ id + " cannont be updated as it doesn't exist.");
	}
	
	@DeleteMapping("/artikl/id/{id}")
	public ResponseEntity<?> deletedArtikl(@PathVariable int id) {
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id +
					"has been deleted.");
		}
		return ResponseEntity.status(404).body("Resourse with requested ID: " +
		" cannont be deleted as it doesn't exist.");
				
	}
	
}
