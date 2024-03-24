package rvabackendg5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rvabackendg5.models.Porudzbina;
import rvabackendg5.models.StavkaPorudzbine;
import rvabackendg5.services.PorudzbinaService;
import rvabackendg5.services.StavkaPorudzbineService;

@RestController
public class StavkaPorudzbineController {
	
	@Autowired
	private StavkaPorudzbineService service;
	
	@Autowired
	private PorudzbinaService porudzbinaService;
	
	@GetMapping("/stavka-porudzbine")
	public List<StavkaPorudzbine> getAllStavkaPorudzbines() {
		return service.getAll();
	}
	
	@GetMapping("stavkeZaPorudzbinu/{foreignKey}")
	public ResponseEntity<?> getStavkeZaPorudzbinu(@PathVariable int foreignKey){
		Optional<Porudzbina> porudzbina = porudzbinaService.findById(foreignKey);
		if(porudzbina.isPresent())
		{
			List<StavkaPorudzbine> stavke = service.getByForeignKey(porudzbina.get());
			if(stavke.isEmpty()) {
				return ResponseEntity.status(404).body("Resources with foreign key: " +
			foreignKey + " do not exist!");
						
			} else {
				return ResponseEntity.ok(stavke);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}

}
