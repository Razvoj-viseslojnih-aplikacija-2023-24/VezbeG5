package rvabackendg5.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rvabackendg5.models.Dobavljac;
import rvabackendg5.models.Porudzbina;
import rvabackendg5.repository.PorudzbinaRepository;
import rvabackendg5.services.PorudzbinaService;

@Component
public class PorudzbinaServiceImpl implements PorudzbinaService {

	@Autowired
	private PorudzbinaRepository repo;
	
	@Override
	public List<Porudzbina> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Optional<Porudzbina> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public Porudzbina create(Porudzbina t) {
		return repo.save(t);
	}

	@Override
	public Optional<Porudzbina> update(Porudzbina t, int id) {
		if(existsById(id)) {
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
	public List<Porudzbina> getProudzbinasByPlaceno(boolean placeno) {
		return repo.findByPlacenoEquals(placeno);
	}

	@Override
	public List<Porudzbina> getForeignKey(Dobavljac dobavljac) {
		return repo.findByDobavljac(dobavljac);
	}

}
