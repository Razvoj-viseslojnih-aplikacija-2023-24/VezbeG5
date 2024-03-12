package rvabackendg5.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import rvabackendg5.models.Artikl;
import rvabackendg5.repository.ArtiklRepository;
import rvabackendg5.services.ArtiklService;

public class ArtiklServiceImpl implements ArtiklService {
	
	/*
	* Anotacija @Autowired se može primeniti nad varijablama instace, setter metodama i
	* konstruktorima. Označava da je neophodno injektovati zavisni objekat. Prilikom
	* pokretanja aplikacije IoC kontejner prolazi kroz kompletan kod tražeči anotacije
	* koje označavaju da je potrebno kreirati objekte. Upotrebom @Autowired anotacije
	* stavljeno je do znanja da je potrebno kreirati objekta klase koja će implementirati
	* repozitorijum AriklRepository i proslediti klasi ArtiklRestController referencu
	* na taj objekat. 
	*/
	
	@Autowired
	private ArtiklRepository repo;

	@Override
	public List<Artikl> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Artikl create(Artikl t) {
		return repo.save(t);
	}

	@Override
	public Optional<Artikl> update(Artikl t, int id) {
		if(existsById(id))
		{
			t.setId(id);
			return Optional.of(repo.save(t));
		}
		return Optional.empty();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<Artikl> getArtiklsByNaziv(String naziv) {
		return repo.findByNazivContainingIgnoreCase(naziv);
	}

}
