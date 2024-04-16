package rvabackendg5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvabackendg5.models.Artikl;
import rvabackendg5.models.Porudzbina;
import rvabackendg5.models.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Integer>{

	List<StavkaPorudzbine> findByCenaLessThanOrderByCena(double cena);
	List<StavkaPorudzbine> findByArtikl(Artikl artikl);
	List<StavkaPorudzbine> findByPorudzbina(Porudzbina porudzbina);
}
