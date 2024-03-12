package rvabackendg5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvabackendg5.models.Dobavljac;
import rvabackendg5.models.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer>{

	List<Porudzbina> findByPlacenoEquals (boolean placeno);
	List<Porudzbina> findByDobavljac (Dobavljac dobavljac);
}
